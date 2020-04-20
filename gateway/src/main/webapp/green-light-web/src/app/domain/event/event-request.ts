import { DateService } from '../../services/date.service';
import { EventLocation } from './event-location';

export class EventRequest {
  title: string;
  typeId: number;
  content: string;
  peopleRequired: number;
  startsAt: string;
  geoLocation: EventLocation;
  items: number[];

  constructor(
    title: string = '',
    content: string = '',
    startsAt: Date = null,
    lat: number = null,
    lng: number = null,
    street: string = null,
    peopleRequired: number = null
  ) {
    this.title = title;
    this.typeId = null;
    this.content = content;
    this.startsAt = DateService.formatAppDate(startsAt);
    this.peopleRequired = peopleRequired;
    this.geoLocation = { lat, lng, street };
  }
}
