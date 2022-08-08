import { Component, EventEmitter, Input, OnInit, Output, PipeTransform } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FoodCategory } from '../food-category';
import { FoodService } from '../service/food-service';
import { NotifierService } from 'angular-notifier';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from '../food';
import { AdminMenuComponent } from '../admin-menu/admin-menu.component';
import { group } from '@angular/animations';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';




@Component({
  selector: 'app-food-table',
  templateUrl: './food-table.component.html',
  styleUrls: ['./food-table.component.css']
})
export class FoodTableComponent  {
  @Input() categories!: FoodCategory[];
  @Input() filterCategories!: FoodCategory[];
   
  constructor(private foodService: FoodService, private notifierService: NotifierService, private modalService: NgbModal, private router: Router, private activatedRoute: ActivatedRoute) {
      
  }
  

  private reloadCurrentRoute() {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/adminMenu']);
    });
  }

  deleteFood(name: string) {
    this.foodService.delete(name).subscribe(x => {
      this.notifierService.notify("success", `Food ${x.name} deleted successfully!`);
      this.modalService.dismissAll();
      this.reloadCurrentRoute();
    });
  
  }

  openDelete(form: any, foodName: string) {
    const modalRef = this.modalService.open(form);
    modalRef.componentInstance.name = foodName;
  }

  openEdit(form: any, food: Food) {
    const modalRef = this.modalService.open(form);
    modalRef.componentInstance.food = food;

  }

  editFood(nameForm: Food, id: number) {
    const food: Food = {
      id: id,
      name: nameForm.name,
      foodCategory: nameForm.foodCategory,
      price: nameForm.price,
      description: nameForm.description
    };
    this.foodService.edit(food).subscribe(x => {
      this.notifierService.notify("success", `Food ${x.name} updated successfully!`);
      this.modalService.dismissAll();
      this.reloadCurrentRoute();
    });
  }
searchByName(name:string) {
  let cloneCategory: FoodCategory[] = []; 
  this.categories.forEach(val => cloneCategory.push(Object.assign({}, val)));  ;

  this.filterCategories = (name) ? cloneCategory.map(function(cloneCategory) {
    cloneCategory.foods = cloneCategory.foods.filter(x1 => x1.name.toLocaleLowerCase().includes(name.toLowerCase()));
    return cloneCategory;
  }) : this.categories;

};
}
