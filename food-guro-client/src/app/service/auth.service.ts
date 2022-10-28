import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public baseURL = "http://localhost:8080";
  isLoginSubject;
  userSubject;
  constructor(private http: HttpClient) {
    this.isLoginSubject = new BehaviorSubject<boolean>(this.isAuthenticated());
    this.userSubject = new BehaviorSubject<User | null>(this.isUser());
  }

  public isAuthenticated(): boolean {
    let token: string | null = this.getToken();
    if (token !== null && token !== undefined) {
      return true;
    }
    return false;
  }

  public isUser(): User | null {
    let user = this.getUser();
    if (user != null && user != undefined) {
      return user;
    }
    else {
      return null;
    }
  }

  login(loginForm: any): Observable<HttpResponse<User>> {
    return this.http.post<User>(`${this.baseURL}/api/login`, loginForm, { observe: 'response' });
  }
  saveUser(user: User) {
    localStorage.setItem("user", JSON.stringify(user));
  }

  saveJwtToken(token: string) {
    localStorage.setItem("token", token);
  }
  getUser(): User {
    const userString = localStorage.getItem("user");
    return JSON.parse(userString!);
  }

  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    this.isLoginSubject.next(false);
    this.userSubject.next(null);
  }
  getToken(): string {
    return localStorage.getItem("token") as string;
  }
  public isLoggedIn(): Observable<boolean> {
    return this.isLoginSubject.asObservable();
  }
  public getUserSubject(): Observable<User | null> {
    return this.userSubject.asObservable();
  }
}