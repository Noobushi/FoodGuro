import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons,NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CategoryService } from '../service/category.service';
import { NotifierService } from 'angular-notifier';
import { FoodService } from '../service/food-service';
import { FoodCategory } from '../food-category';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit{
  public categories!: FoodCategory[];
  public filterCategories! :FoodCategory[];
  closeResult = '';

  enteredSearchValue: string='';

  constructor(private modalService: NgbModal, private categoryService: CategoryService, private notifierService: NotifierService, private foodService:FoodService) {}

  ngOnInit(): void {
    this.categoryService.getAll().subscribe(x => {
      this.categories = x;
      this.filterCategories = this.categories;
    });
    
  }
  

  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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

  createCategory(nameForm:any){
    console.log(nameForm);
    this.categoryService.create(nameForm).subscribe(x=> this.notifierService.notify("success", `Category ${x.name} created successfully!`));
  }

  deleteCategory(nameForm:any){
    console.log(nameForm);
    this.categoryService.delete(nameForm).subscribe(x=> this.notifierService.notify("success", `Category ${x.name} deleted successfully!`));
  }

   createFood(nameForm:any){
    console.log(nameForm);
    this.foodService.create(nameForm).subscribe(x=> this.notifierService.notify("success", `Food ${x.name} created successfully!`));
  }

  // eventFromChild(dsad : string){
  //   this.categoryService.getAll().subscribe(x => this.categories = x);
  // }
}
