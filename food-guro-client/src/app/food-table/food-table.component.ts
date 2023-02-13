import { Component, Input } from '@angular/core';
import { FoodCategory } from '../classes/food-category';
import { FoodService } from '../service/food-service';
import { NotifierService } from 'angular-notifier';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { Food } from '../classes/food';

@Component({
  selector: 'app-food-table',
  templateUrl: './food-table.component.html',
  styleUrls: ['./food-table.component.css']
})
export class FoodTableComponent {
  @Input() categories!: FoodCategory[];
  @Input() filterCategories!: FoodCategory[];

  constructor(private foodService: FoodService, private notifierService: NotifierService, private modalService: NgbModal, private router: Router) {

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
    console.log(form);
    const modalRef = this.modalService.open(form);
    console.log(modalRef);
    console.log(modalRef.componentInstance.name);
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
      description: nameForm.description,
      image: nameForm.image
    };
    this.foodService.edit(food).subscribe(x => {
      this.notifierService.notify("success", `Food ${x.name} updated successfully!`);
      this.modalService.dismissAll();
      this.reloadCurrentRoute();
    });
  }

  searchByName(name: string) {
    let cloneCategory: FoodCategory[] = [];
    this.categories.forEach(val => cloneCategory.push(Object.assign({}, val)));;

    this.filterCategories = (name) ? cloneCategory.map(function (cloneCategory) {
      cloneCategory.foods = cloneCategory.foods.filter(x1 => x1.name.toLocaleLowerCase().includes(name.toLowerCase()));
      return cloneCategory;
    }) : this.categories;
  };

}
