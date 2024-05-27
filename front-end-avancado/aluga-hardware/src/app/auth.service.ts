import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn():Observable<boolean>{
    return this.loggedIn.asObservable();
  }

  login(username:string, password:string):boolean{
    if(username === 'user' && password === 'password'){
      this.loggedIn.next(true);
      return true;
    }
    return false;
  }

  logout(){
    this.loggedIn.next(false);
  }
}
