import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from './settings.component';
import { SettingsRoutingModule } from './settings-routing.module';
import { SettingsDetailsComponent } from './components/settings-details/settings-details.component';
import { ThemeModule } from '../../../@theme/theme.module';

@NgModule({
  declarations: [SettingsComponent, SettingsDetailsComponent],
  imports: [CommonModule, SettingsRoutingModule, ThemeModule],
})
export class SettingsModule {}
