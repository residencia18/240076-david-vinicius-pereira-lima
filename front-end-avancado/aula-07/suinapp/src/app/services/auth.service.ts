import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { User } from '../models/user.model'
import { HttpClient } from '@angular/common/http';
import { config } from '../app.config';

interface AuthResponseData {
  idToken: string;
  email: string;
  refreshToken: string;
  expiresIn: string;
  localId: string;
  registered?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private identityURL = 'https://identitytoolkit.googleapis.com/v1/accounts:';
  private projectAPIKey = config.projectAPIKey; 
  user: BehaviorSubject<User> = new BehaviorSubject<User>(new User('', '', '', new Date()));

  constructor(private http: HttpClient) { }

  cadastrarUser(email: string, senha: string) {
    return this.http.post<AuthResponseData>(this.identityURL + 'signUp?key=' + this.projectAPIKey, {
      email: email,
      password: senha,
      returnSecureToken: true
    }).pipe(
      tap(resData => {
        const expirationDate = new Date(new Date().getTime() + +resData.expiresIn * 1000);
        const user = new User(resData.email, resData.localId, resData.idToken, expirationDate);
        this.user.next(user);
        localStorage.setItem('userData', JSON.stringify(user));
      }));
  }

  login(email: string, senha: string) {
    return this.http.post<AuthResponseData>(this.identityURL + 'signInWithPassword?key=' + this.projectAPIKey, {
      email: email,
      password: senha,
      returnSecureToken: true
    }).pipe(
      tap(resData => {
        const expirationDate = new Date(new Date().getTime() + +resData.expiresIn * 1000);
        const userToLogin = new User(resData.email, resData.localId, resData.idToken, expirationDate);
        this.user.next(userToLogin);
        localStorage.setItem('userData', JSON.stringify(userToLogin));
      }));
  }

  autoLogin() {
    const userData: {
      email: string;
      id: string;
      _token: string;
      _tokenExpirationDate: string;
    } = JSON.parse(localStorage.getItem('userData') as string);
    if (!userData) {
      return;
    }
    const loadedUser = new User(userData.email, userData.id, userData._token, new Date(userData._tokenExpirationDate));
    if (loadedUser.token) {
      this.user.next(loadedUser);
    }
  }

  logout() {
    this.user.next(new User('', '', '', new Date()));
    localStorage.removeItem('userData');
  }

  isAuthenticated() {
    return this.user.value.token != null;
  }
}