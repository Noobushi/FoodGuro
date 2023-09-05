import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../classes/order';
import { Food } from '../classes/food';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080';
  }

  public create(order: Order, username: string): Observable<Order> {
    return this.http.post<Order>(`${this.host}/api/order/create?username=${username}`, order);
  }

  public retrieve(orderId: any): Observable<Food[]> {
    return this.http.get<Food[]>(`${this.host}/api/order/all?orderId=${orderId}`)
  }

}
