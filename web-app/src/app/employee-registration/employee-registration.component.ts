import { Component, OnInit, ViewChild } from '@angular/core';
import { PersonService } from '../services/person.service';
import { Person } from '../models/person';
import { DatePipe } from '@angular/common';
import { Gender } from '../models/gender';
import { TeamComponent } from './team/team.component';
import { GenderComponent } from './gender/gender.component';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-employee-registration',
  templateUrl: './employee-registration.component.html',
  styleUrls: ['./employee-registration.component.css'],
  providers:[DatePipe]
})
export class EmployeeRegistrationComponent implements OnInit  {
  person = {} as Person;
  persons: Person [] = [];

  selectedTeam = "null";
  selectedGender = -1;

  maxDate: Date;
  date: Date;

  constructor(private personService: PersonService, private datePipe: DatePipe) {
    this.maxDate = new Date();
    this.date = new Date();
    this.getPersons();
  }

  ngOnInit(): void {
    this.clear();
  }

  @ViewChild(TeamComponent, {static: false})
  teamComponent = {} as TeamComponent;

  @ViewChild(GenderComponent, {static: false})
  genderComponent = {} as GenderComponent;

  defaultSelectGender() {
    this.genderComponent.defaultSelection();
  }

  defaultSelectTeam() {
    this.teamComponent.defaultSelection();
  }

  addEventDatePicker(type: string, event: MatDatepickerInputEvent<Date>) {
    this.person.birthDate = this.datePipe.transform(event.value, 'dd-MM-yyyy')+"";
  }

  savePerson(person: Person) {

    if (person.id !== -1 && person.id !== undefined) {
      this.personService.updatePerson(person).subscribe(() => {
        this.clear();
        this.getPersons();
      });
    } else {

      person.startDate = this.datePipe.transform(Date.now(), 'dd-MM-yyyy')+"";
          
      this.personService.savePerson(person).subscribe(() => {
          this.clear();
          this.getPersons();
        });
      }
  }

  getPersons() {
    this.personService.getPerson().subscribe((persons: Person[]) => {
      this.persons = persons;
    });
  }

  deletePerson(person: Person){
    this.personService.deletePerson(person).subscribe(() => {
      this.getPersons();
    });
  }

  selectedOptionGender(gender : Gender){
    this.person.gender = gender;
  }

  selectedOptionTeam(team : string){
    this.person.team = team;
    console.log(this.person.team);
  }

  clear(){
    this.person.id = -1;
    this.person.name = "";
    this.person.email = "";
    this.person.cpf = "";
    this.person.birthDate = this.datePipe.transform(Date.now(), 'dd-MM-yyyy')+"";
    this.person.startDate = "";
    this.person.team = "";
    this.person.gender = {id:0, description:""};

    this.date = new Date();
    this.selectedTeam = "null";
    this.selectedGender = -1;

    this.defaultSelectTeam();
    this.defaultSelectGender();
  }
}