import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-token',
  templateUrl: './token.component.html',
  styleUrls: ['./token.component.scss']
})
export class TokenComponent implements OnInit {
  
  token: string
  redirect = "/profile"

  constructor(private route: ActivatedRoute, private authService: AuthService, private router: Router) {
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
      this.authService.setToken(this.token)
    });
  }

  ngOnInit(): void {
    this.router.navigateByUrl(this.redirect)
  }

}
