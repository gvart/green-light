import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { from, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { KeycloakUser } from '../keycloak-user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private keycloakService: KeycloakService) {}

  getCurrentUser(): Observable<KeycloakUser> {
    return from(this.keycloakService.getKeycloakInstance().loadUserInfo()).pipe(
      map<any, KeycloakUser>(it => {
        return {
          email: it.email,
          emailVerified: it.email_verified,
          familyName: it.family_name,
          givenName: it.given_name,
          name: it.name,
          preferredUsername: it.preferred_username,
          id: it.sub,
        };
      })
    );
  }
}
