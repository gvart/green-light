import { Component, Input } from '@angular/core';
import { LocationMetadata } from '../location-metadata';

@Component({
  selector: 'app-google-maps-popup-info',
  templateUrl: './google-maps-popup-info.component.html',
  styleUrls: ['./google-maps-popup-info.component.scss'],
})
export class GoogleMapsPopupInfoComponent {
  @Input() locationMetadata: LocationMetadata;
  isModalOpen = false;

  public openModal() {
    this.isModalOpen = true;
  }
}
