import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
   }

  public getFoods(): Observable<FoodCategory[]>{
    return this.http.get<FoodCategory[]>(`${this.host}/api/foodCategory/all`)
  }

}
