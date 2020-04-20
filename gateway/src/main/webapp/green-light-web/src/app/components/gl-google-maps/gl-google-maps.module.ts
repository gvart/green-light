import { NgModule } from '@angular/core';
import { GoogleMapsModule } from '@angular/google-maps';
import { ClrInputModule, ClrModalModule } from '@clr/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GoogleMapsLocationPickerComponent } from './google-maps-location-picker/google-maps-location-picker.component';
import { GoogleMapsPopupInfoComponent } from './google-maps-popup-info/google-maps-popup-info.component';

@NgModule({
  declarations: [
    GoogleMapsLocationPickerComponent,
    GoogleMapsPopupInfoComponent,
  ],
  imports: [
    GoogleMapsModule,
    ClrModalModule,
    CommonModule,
    ClrInputModule,
    FormsModule,
  ],
  exports: [GoogleMapsLocationPickerComponent, GoogleMapsPopupInfoComponent],
})
export class GlGoogleMapsModule {}
