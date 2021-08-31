import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Person } from '../models/person';
import { SharedData } from '../shared-data';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  url = SharedData.API_ENDPOINT+"person";

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  savePerson(person: Person): Observable<Person> {
    return this.httpClient.post<Person>(this.url, JSON.stringify(person), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  getPerson(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  updatePerson(person: Person): Observable<Person> {
    return this.httpClient.put<Person>(this.url + '/' + person.id, JSON.stringify(person), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  deletePerson(person: Person) {
    console.log('id: ' + person.id);
    return this.httpClient.delete<Person>(this.url + '/' + person.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Error occurred on client side
      errorMessage = error.error.message;
    } else {
      // Error occurred on server side
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
