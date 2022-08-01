import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
   }

   public create(categoryForm:any): Observable<FoodCategory>{
    return this.http.post<FoodCategory>(`${this.host}/api/foodCategory/create`, { name : categoryForm });
   }

   public delete(categoryForm:any) : Observable<FoodCategory>{
    return this.http.post<FoodCategory>(`${this.host}/api/foodCategory/delete`, {name : categoryForm});
   }

   public getAll() : Observable<FoodCategory[]>{
    return this.http.get<FoodCategory[]>(`${this.host}/api/foodCategory/all`);
   }
}
