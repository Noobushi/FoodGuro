import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../classes/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
  }

  public create(order: any): Observable<Order> {
    return this.http.post<Order>(`${this.host}/api/order/create`, order, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    }
    );
  }

}
