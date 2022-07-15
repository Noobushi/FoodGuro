import { Component, Input, OnInit } from '@angular/core';
import { Food} from '../food';
@Component({
  selector: 'app-food-alerts',
  templateUrl: './food-alerts.component.html',
  styleUrls: ['./food-alerts.component.css']
})
export class FoodAlertsComponent implements OnInit {

  @Input() food!:Food;

  constructor() { }

  ngOnInit(): void {
  }

}
