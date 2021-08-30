import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Gender } from '../models/gender';
import { SharedData } from '../shared-data';

@Injectable({
  providedIn: 'root'
})

export class GenderService {

  url = SharedData.API_ENDPOINT+"gender";

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  getGender(): Observable<Gender[]> {
    return this.httpClient.get<Gender[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
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
