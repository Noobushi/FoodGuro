import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CategoryService } from '../service/category.service';
import { NotifierService } from 'angular-notifier';
import { FoodService } from '../service/food-service';
import { FoodCategory } from '../food-category';
import { User } from '../user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {
  public categories!: FoodCategory[];
  public filterCategories!: FoodCategory[];
  public users!: User[];
  public filterUsers!: User[];

  closeResult = '';

  constructor(private modalService: NgbModal, private categoryService: CategoryService, private notifierService: NotifierService, private foodService: FoodService, private userService: UserService) { }

  ngOnInit(): void {
    this.categoryService.getAll().subscribe(x => {
      this.categories = x;
      this.filterCategories = this.categories;
    });
    this.userService.getAll().subscribe(x => {
      this.users = x;
      this.filterUsers = this.users;
    });
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  createCategory(nameForm: any) {
    this.categoryService.create(nameForm).subscribe(foodCategory => this.notifierService.notify("success", `Category ${foodCategory.name} created successfully!`));
  }

  deleteCategory(nameForm: any) {
    this.categoryService.delete(nameForm).subscribe(foodCategory => this.notifierService.notify("success", `Category ${foodCategory.name} deleted successfully!`));
  }

  createFood(nameForm: any) {
    this.foodService.create(nameForm).subscribe(food => this.notifierService.notify("success", `Food ${food.name} created successfully!`));
  }

}
