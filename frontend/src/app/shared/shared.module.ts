import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [],
  imports: [
    CommonModule, MatCardModule, MatInputModule, MatDividerModule, MatProgressSpinnerModule
  ],
  exports: [
    CommonModule, MatCardModule, MatInputModule, MatDividerModule, MatProgressSpinnerModule
  ],
})
export class SharedModule { }
