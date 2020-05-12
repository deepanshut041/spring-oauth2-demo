import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { UserProfile } from '../_dtos/user/UserProfile';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  profile: UserProfile

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.fetchProfile().subscribe((profile) =>{
       this.profile = profile
    })
  }

  signOut(): void {
    this.userService.logout()
  }

}
