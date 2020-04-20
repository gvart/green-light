import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { GoogleMap } from '@angular/google-maps';
import { LocationMetadata } from '../location-metadata';

@Component({
  selector: 'app-google-maps-location-picker',
  templateUrl: './google-maps-location-picker.component.html',
  styleUrls: ['./google-maps-location-picker.component.scss'],
})
export class GoogleMapsLocationPickerComponent implements OnInit {
  private readonly geocoder = new google.maps.Geocoder();

  private readonly DEFAULT_POSITION: google.maps.LatLngLiteral = {
    lat: 47.024248,
    lng: 47.024248,
  };

  @Input() editable = true;
  @Input() locationMetadata: LocationMetadata;
  @Output() locationSubmitted = new EventEmitter<LocationMetadata>();
  @ViewChild('map') map: GoogleMap;

  center: google.maps.LatLngLiteral;

  options: google.maps.MapOptions = {
    zoomControl: true,
    scrollwheel: true,
    disableDoubleClickZoom: true,
    maxZoom: 18,
    minZoom: 8,
  };

  ngOnInit(): void {
    this.initCurrentLocation();
  }

  private initCurrentLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
        },
        positionError => {
          this.center = this.DEFAULT_POSITION;
        }
      );
    }
  }

  async setLocation(event: google.maps.MouseEvent) {
    this.locationMetadata.location = event.latLng;
    this.locationMetadata.street = await this.extractStreetName();
    this.locationSubmitted.emit(this.locationMetadata);
  }

  async extractStreetName(): Promise<string> {
    return new Promise<string>(resolve => {
      this.geocoder.geocode(
        { location: this.locationMetadata.location },
        (results, status) => {
          resolve(results[0].formatted_address);
        }
      );
    });
  }
}
