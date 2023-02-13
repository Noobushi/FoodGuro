import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private host;

  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080'
  }

  public register(userForm: any): Observable<User> {
    return this.http.post<User>(`${this.host}/api/users/register`, userForm);
  }

  public delete(userForm: any): Observable<User> {
    return this.http.post<User>(`${this.host}/api/users/delete`, { username: userForm });
  }

  public edit(userForm: any): Observable<User> {
    return this.http.post<User>(`${this.host}/api/users/edit`, userForm);
  }

  public getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.host}/api/users/all`);
  }
}
