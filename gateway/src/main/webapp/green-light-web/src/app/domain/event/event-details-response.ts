import { EventLocation } from './event-location';
import { EventTypeResponse } from './event-type';

export class EventDetailsResponse {
  id: number;
  title: string;
  content: string;
  type: string;
  peopleRequired: number;
  createdAt: string;
  startsAt: string;
  finishedAt: string;
  nickName: string;
  userId: string;
  geoLocation: EventLocation;
  items: number[];
  isLiked: boolean;
  likes: number;
}

export class EventShortResponse {
  id: number;
  title: string;
  status: EventTypeResponse;
  type: EventTypeResponse;
  geoLocation: EventLocation;
  createdAt: string;
  userId: string;
  userName: string;
  peopleRequired: number;
  isLiked: boolean;
  likes: number;
}
