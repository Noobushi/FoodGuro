import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
   }

  public register(userForm:any): Observable<User>{
    return this.http.post<User>(`${this.host}/api/users/register`, userForm);
  }
}
