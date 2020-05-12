import { Injectable } from '@angular/core';
import { UserProfile } from '../_dtos/user/UserProfile';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  TOKEN_KEY = 'auth-token';

  constructor() { }

  signOut() {
    localStorage.clear();
  }

  public saveToken(token: string) {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN_KEY);
  }

}
