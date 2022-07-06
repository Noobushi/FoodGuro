import { Component, Input, OnInit } from '@angular/core';
import { Foods, foods } from '../foods';
@Component({
  selector: 'app-food-alerts',
  templateUrl: './food-alerts.component.html',
  styleUrls: ['./food-alerts.component.css']
})
export class FoodAlertsComponent implements OnInit {

  @Input() food!:Foods;

  constructor() { }

  ngOnInit(): void {
  }

}
