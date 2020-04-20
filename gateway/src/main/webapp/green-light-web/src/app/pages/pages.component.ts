import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pages',
  template: `
    <app-layout (logOutClicked)="logout()"> </app-layout>
  `,
})
export class PagesComponent {
  constructor(private authService: KeycloakService, private router: Router) {}

  logout() {
    this.authService.logout().then(() => {
      this.router.navigate(['/']);
    });
  }
}
