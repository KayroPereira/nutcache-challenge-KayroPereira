import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

interface Team{
  value: number;
  description : string;
}

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  
  @Input() indexSelect: string = "";
  @Output() selectedOption = new EventEmitter<string>();

  teams: Team[] = [
    {value: 1, description: "Mobile"},
    {value: 2, description: "Frontend"},
    {value: 3, description: "Backend"}
  ];

  optionSelected = 0;
  team = {} as Team;

  constructor() { }

  ngOnInit(): void {
    this.optionSelected = this.getIndexTeam(this.indexSelect);
  }

  defaultSelection(){
    this.optionSelected = -1;
  }

  getIndexTeam(description: string) : number{
    for(let i = 0; i < this.teams.length; i++){
      if(this.teams[i].description === description){
        return this.teams[i].value;
      }
    }
    return -1;
  }

  getDescriptionTeam(value: number) : string{
    for(let i = 0; i < this.teams.length; i++){
      if(this.teams[i].value === value){
        return this.teams[i].description;
      }
    }
    return "";
  }

  sendEvent(value : number){
    this.team.value = value;
    this.team.description = this.getDescriptionTeam(value);
    this.selectedOption.emit(this.team.description);
  }
}
