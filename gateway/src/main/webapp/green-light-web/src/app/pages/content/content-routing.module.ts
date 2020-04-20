import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentComponent } from './content.component';
import { AuthGuard } from '@gl/auth/service/auth-guard.service';

const routes: Routes = [
  {
    path: '',
    component: ContentComponent,
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./home/home.module').then(m => m.HomeModule),
      },
      {
        canActivate: [AuthGuard],
        data: {
          roles: ['USER', 'ADMIN'],
        },
        path: 'event',
        loadChildren: () =>
          import('./event/event.module').then(m => m.EventModule),
      },
      {
        canActivate: [AuthGuard],
        data: {
          roles: ['USER', 'ADMIN'],
        },
        path: 'settings',
        loadChildren: () =>
          import('./settings/settings.module').then(m => m.SettingsModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ContentRoutingModule {}
