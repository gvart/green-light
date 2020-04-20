import { NgModule } from '@angular/core';
import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { PagesRoutingModule } from './pages-routing.module';
import { AuthGuard } from '../auth/service/auth-guard.service';
import { AuthModule } from '../auth/auth.module';

@NgModule({
  declarations: [PagesComponent],
  imports: [PagesRoutingModule, AuthModule, ThemeModule],
  providers: [AuthGuard],
})
export class PagesModule {}
