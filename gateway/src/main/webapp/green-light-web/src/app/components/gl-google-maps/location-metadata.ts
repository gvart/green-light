export class LocationMetadata {
  location: google.maps.LatLng;
  street: string;

  constructor(location: google.maps.LatLng, street: string) {
    this.location = location;
    this.street = street;
  }
}
