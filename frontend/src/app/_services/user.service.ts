import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { TokenStorageService } from './token-storage.service';
import { UserProfile } from '../_dtos/user/UserProfile';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient, private storage:TokenStorageService) {
  }

  fetchProfile(): Observable<UserProfile>{
    return this.httpClient.get(`${environment.DOMAIN}/api/users/me`, this.httpOptions) as Observable<UserProfile>
  }

  logout(): void{
    this.storage.signOut()
    window.location.reload();
  }
}
