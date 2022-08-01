import { Component, Input, PipeTransform } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FoodCategory } from '../food-category';
import { FoodService } from '../service/food-service';
import { NotifierService } from 'angular-notifier';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';





@Component({
  selector: 'app-food-table',
  templateUrl: './food-table.component.html',
  styleUrls: ['./food-table.component.css']
})
export class FoodTableComponent {
  @Input() categories!: Observable<FoodCategory[]>
  constructor(private foodService: FoodService, private notifierService: NotifierService, private modalService: NgbModal, private router: Router, private activatedRoute: ActivatedRoute) {

  }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  deleteFood(name: string) {
    this.foodService.delete(name).subscribe(x => this.notifierService.notify("success", `Food ${x.name} deleted successfully!`));
    this.modalService.dismissAll();
    this.reloadCurrentRoute();

  }

  open(form: any, foodName: string) {
    const modalRef = this.modalService.open(form);
    modalRef.componentInstance.name = foodName;

  }
}
