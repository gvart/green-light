import { Component, OnInit } from '@angular/core';
import { AuthService } from '@gl/auth/service/auth.service';
import { KeycloakUser } from '@gl/auth/keycloak-user';

@Component({
  selector: 'app-settings-details',
  templateUrl: './settings-details.component.html',
  styleUrls: ['./settings-details.component.scss'],
})
export class SettingsDetailsComponent implements OnInit {
  user: KeycloakUser;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(it => {
      this.user = it;
    });
  }
}
