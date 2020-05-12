import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import { UserRoutingModule } from './user-routing.module';
import { SharedModule } from '../shared/shared.module';
import { UserService } from '../_services/user.service';

@NgModule({
  declarations: [UserComponent],
  imports: [CommonModule, UserRoutingModule, SharedModule,],
  providers: [UserService,]
})
export class UserModule { }
