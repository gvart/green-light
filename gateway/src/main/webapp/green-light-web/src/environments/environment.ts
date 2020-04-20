import { KeycloakConfig } from 'keycloak-angular';

// Add here your keycloak setup infos
const keycloakConfig: KeycloakConfig = {
  url: 'https://greenlight.local:8443/auth',
  realm: 'GreenLight',
  clientId: 'webapp',
};

export const environment = {
  production: false,
  serverUrl: 'http://localhost:8080',
  keycloakConfig,
};
