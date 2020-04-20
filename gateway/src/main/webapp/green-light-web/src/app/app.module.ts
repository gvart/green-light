import { BrowserModule } from '@angular/platform-browser';
import {
  ApplicationRef,
  DoBootstrap,
  LOCALE_ID,
  NgModule,
} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClarityModule } from '@clr/angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { reducers } from './store/app.state';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { registerLocaleData } from '@angular/common';
import localeMd from '@angular/common/locales/en-GB';
import { NotifierModule } from 'angular-notifier';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';

registerLocaleData(localeMd);

const keycloakService = new KeycloakService();

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClarityModule,
    BrowserAnimationsModule,
    HttpClientModule,
    StoreModule.forRoot(reducers, {}),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({
      maxAge: 25, // Retains last 25 states
      logOnly: environment.production, // Restrict extension to log-only mode
    }),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production,
    }),
    NotifierModule,
    KeycloakAngularModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'en-GB' },
    {
      provide: KeycloakService,
      useValue: keycloakService,
    },
  ],
  entryComponents: [AppComponent],
})
export class AppModule implements DoBootstrap {
  ngDoBootstrap(appRef: ApplicationRef) {
    const { keycloakConfig } = environment;

    keycloakService
      .init({ config: keycloakConfig })
      .then(() => {
        console.log('[ngDoBootstrap] bootstrap app');

        appRef.bootstrap(AppComponent);
      })
      .catch(error =>
        console.error('[ngDoBootstrap] init Keycloak failed', error)
      );
  }
}
