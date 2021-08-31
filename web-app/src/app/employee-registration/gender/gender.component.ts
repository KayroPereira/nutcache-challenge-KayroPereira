import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GenderService } from '../../services/gender.service';
import { Gender } from '../../models/gender';

@Component({
  selector: 'app-gender',
  templateUrl: './gender.component.html',
  styleUrls: ['./gender.component.css']
})
export class GenderComponent implements OnInit {
  
  @Input() indexSelect: number = 0;
  @Output() selectedOption = new EventEmitter<Gender>();

  optionSelected = 0;
  genders: Gender [] = [];

  constructor(private genderService: GenderService) { }

  ngOnInit(): void {
    this.getGenders();
    this.optionSelected = this.indexSelect;
  }
  
  defaultSelection(){
    this.optionSelected = -1;
  }

  getGenders() {
    this.genderService.getGender().subscribe((genders: Gender[]) => {
      this.genders = genders;
    });
  }

  getObjectGender(value: number) : Gender{
    let gender = {} as Gender;
    for(let i = 0; i < this.genders.length; i++){
      if(this.genders[i].id === value){
        gender.id = value;
        gender.description = this.genders[i].description;
        return gender;
      }
    }
    return gender;
  }

  sendEvent(value : number){
    this.selectedOption.emit(this.getObjectGender(value));
  }
}
