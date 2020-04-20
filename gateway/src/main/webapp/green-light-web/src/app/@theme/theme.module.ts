import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  FooterComponent,
  HeaderComponent,
  LayoutComponent,
} from './components';
import { CapitalizePipe, PluralPipe, RoundPipe, TimingPipe } from './pipes';
import {
  ClrIconModule,
  ClrNavigationModule,
  ClrVerticalNavModule,
} from '@clr/angular';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { RouterModule } from '@angular/router';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { RequestSpinnerComponent } from './components/request-spinner/request-spinner.component';
import { AuthModule } from '../auth/auth.module';

const BASE_MODULES = [CommonModule, ClrVerticalNavModule, ClrNavigationModule];

const NB_MODULES = [ClrIconModule, RouterModule];

const COMPONENTS = [
  HeaderComponent,
  FooterComponent,
  LayoutComponent,
  SideBarComponent,
  RequestSpinnerComponent,
];

const PIPES = [
  CapitalizePipe,
  PluralPipe,
  RoundPipe,
  TimingPipe,
  DateFormatPipe,
];

@NgModule({
  imports: [...BASE_MODULES, ...NB_MODULES, AuthModule],
  exports: [...BASE_MODULES, ...NB_MODULES, ...COMPONENTS, ...PIPES],
  declarations: [...COMPONENTS, ...PIPES],
})
export class ThemeModule {}
