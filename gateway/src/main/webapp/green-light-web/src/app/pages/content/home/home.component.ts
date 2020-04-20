import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private authService: KeycloakService) {}

  ngOnInit(): void {}

  onSignIn() {
    this.authService.login();
  }

  onSignUp() {
    this.authService.register();
  }
}
