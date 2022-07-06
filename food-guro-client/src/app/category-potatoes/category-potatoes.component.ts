import { Component, OnInit } from '@angular/core';
import { foods, getFoodName } from '../foods';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './category-potatoes.component.html',
  styleUrls: ['./category-potatoes.component.css']
})
export class CategoryPotatoeComponent implements OnInit {

  foods = foods;

  constructor() { }

  ngOnInit(): void {
  }

  share(id:number){
    navigator.clipboard.writeText(getFoodName(id));
    window.alert('The food link has been copied!');
  }
  
}
