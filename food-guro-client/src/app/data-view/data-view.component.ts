import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { ProductService } from '../service/product.service';
import {SelectItem} from 'primeng/api';
import { Product } from '../product';

@Component({
  selector: 'app-data-view',
  templateUrl: './data-view.component.html',
  styleUrls: ['./data-view.component.scss']
})
export class DataViewComponent implements OnInit {

  products!: Product[];

    sortOptions!: SelectItem[];

    sortOrder!: number;

    sortField!: string;

    constructor(private productService: ProductService, private primengConfig: PrimeNGConfig) { }

    ngOnInit() {
        this.productService.getProducts().then(data => this.products = data);

        this.sortOptions = [
            {label: 'Price High to Low', value: '!price'},
            {label: 'Price Low to High', value: 'price'}
        ];

        this.primengConfig.ripple = true;
    }
    
    onSortChange(event:any) {
        let value = event.value;

        if (value.indexOf('!') === 0) {
            this.sortOrder = -1;
            this.sortField = value.substring(1, value.length);
        }
        else {
            this.sortOrder = 1;
            this.sortField = value;
        }
    }

}
