import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Person } from '../../models/person';
import { Gender } from '../../models/gender';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-list-person',
  templateUrl: './list-person.component.html',
  styleUrls: ['./list-person.component.css']
})
export class ListPersonComponent implements OnInit {
  @Input() person: Person = {id:0, name:"", email:"", cpf:"", birthDate:"", startDate:"", team:"", gender:{id:0, description:""}};
  @Output() updatePerson = new EventEmitter<Person>();
  @Output() deletePerson = new EventEmitter<Person>();

  isEditablePerson: boolean = false;
  date = new Date(this.person.birthDate);
  maxDate: Date;

  constructor(private datePipe: DatePipe) {
    this.maxDate = new Date();
  }

  ngOnInit(): void { }

  addEventDatePicker(type: string, event: MatDatepickerInputEvent<Date>) {
    this.person.birthDate = this.datePipe.transform(event.value, 'dd-MM-yyyy')+"";
  }

  selectedOptionGender(gender : Gender){
    this.person.gender = gender;
  }

  selectedOptionTeam(team : string){
    this.person.team = team;
  }

  dateFormat(date : string) : string{
    let temp = date.split('-');
    return(`${temp[1]}-${temp[0]}-${temp[2]}`);
  }

  edit(){

    this.isEditablePerson = true;
    this.date = new Date(this.dateFormat(this.person.birthDate));
  }

  update(){
    this.updatePerson.emit(this.person);
  }

  delete(){
    this.deletePerson.emit(this.person);
  }

  cancel(){
    this.isEditablePerson = false;
  }
}
