import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/_services/auth.service';
import { Router } from '@angular/router';
import { SignUpRequest } from 'src/app/_dtos/auth/SignUpRequest';
import { ApiResponse } from 'src/app/_dtos/common/ApiResponse';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  loading: Boolean = false
  signUpFrom: FormGroup

  constructor(private _authService: AuthService, private fb: FormBuilder, private router: Router) {
    this.signUpFrom = this.fb.group({
      email: [],
      password: [],
      name: [],
      cpassword: []
    })
  }

  ngOnInit(): void {

  }

  register() {
    if (this.signUpFrom.valid) {
      let data = this.signUpFrom.value
      this.loading = true
      this._authService.register(new SignUpRequest(data['name'], data['email'], data['password'])).subscribe(
        (response: ApiResponse) => {
          this.loading = false
          console.log(response)
        }, (err: any) => {
          this.loading = false
          console.log(err.error.message)
        }
      )
    }
  }

}
