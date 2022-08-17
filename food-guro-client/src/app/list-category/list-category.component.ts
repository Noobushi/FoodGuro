import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';
import { CategoryService } from '../service/category.service';
import { FoodService } from '../service/food-service';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {

public categories$!: Observable<FoodCategory[]>;
  constructor(private foodService: FoodService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories$ = this.foodService.getFoods();
  }
  
  onClick() {
    
  }

}
