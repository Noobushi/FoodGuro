import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';
import { FoodService } from '../service/food-service';
@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {

public categories$!: Observable<FoodCategory[]>;

  constructor(private foodService: FoodService,private notifierService:NotifierService) { }

  ngOnInit(): void {
    this.categories$ = this.foodService.getFoods();
    
  }
  
  onClick() { 
     this.notifierService.notify("success", `User  registered successfully!`);
  }

}
