import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../food';
import { FoodImages } from '../food-images';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
  }


  public create(foodForm: any): Observable<Food> {
    return this.http.post<Food>(`${this.host}/api/food/create`, foodForm);
  }

  public delete(foodForm: any): Observable<Food> {
    return this.http.post<Food>(`${this.host}/api/food/delete`, { name: foodForm });
  }

  public edit(foodForm: any): Observable<Food> {
    return this.http.post<Food>(`${this.host}/api/food/edit`, foodForm);
  }

  public getAllImages(foodName: String): Observable<FoodImages[]> {
    return this.http.get<FoodImages[]>(`${this.host}/api/food/allImages?foodName=${foodName}`)
  }


}
