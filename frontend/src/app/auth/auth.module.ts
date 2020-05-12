import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AuthRoutingModule } from './auth-routing.module';

import { AuthService } from '../_services/auth.service';

import { AuthComponent } from './auth.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { TokenComponent } from './token/token.component';
import { UserService } from '../_services/user.service';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [AuthComponent, SignupComponent, SigninComponent, TokenComponent],
  imports: [
    CommonModule, AuthRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule,
    SharedModule,
  ],
  providers: [
    AuthService
  ]
})
export class AuthModule { }
