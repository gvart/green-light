import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  isAuthenticated: boolean;

  @Output() singInClicked = new EventEmitter();
  @Output() singUpClicked = new EventEmitter();
  @Output() logOutClicked = new EventEmitter();

  constructor(private authService: KeycloakService) {}

  onSingIn = () => this.singInClicked.emit();

  onSingUp = () => this.singUpClicked.emit();

  logOut = () => this.logOutClicked.emit();

  async ngOnInit() {
    this.isAuthenticated = await this.authService.isLoggedIn();
  }
}
