import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ClrCheckboxModule,
  ClrInputModule,
  ClrModalModule,
  ClrPasswordModule,
} from '@clr/angular';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    ClrModalModule,
    ClrInputModule,
    ClrPasswordModule,
    ClrCheckboxModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
})
export class AuthModule {}
