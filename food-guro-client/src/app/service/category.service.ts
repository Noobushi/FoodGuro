import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../food';
import { FoodCategory } from '../food-category';
import { FoodImages } from '../food-images';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
  }

  public create(categoryForm: any): Observable<FoodCategory> {
    return this.http.post<FoodCategory>(`${this.host}/api/foodCategory/create`, { name: categoryForm });
  }

  public delete(categoryForm: any): Observable<FoodCategory> {
    return this.http.post<FoodCategory>(`${this.host}/api/foodCategory/delete`, { name: categoryForm });
  }

  public getAll(): Observable<FoodCategory[]> {
    return this.http.get<FoodCategory[]>(`${this.host}/api/foodCategory/all`);
  }

  public getFoodsInCategory(foodCategory: String): Observable<Food[]> {
    return this.http.get<Food[]>(`${this.host}/api/foodCategory/allFoods?category=${foodCategory}`);
  }

  public getAllImages(categoryId: number): Observable<FoodImages[]> {
    return this.http.get<FoodImages[]>(`${this.host}/api/foodCategory/allImages?categoryId=${categoryId}`)
  }
}
