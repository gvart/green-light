import { Injectable } from '@angular/core';
import { AbstractHttpService } from './abstract-http.service';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import {
  EventTypeRequest,
  EventTypeResponse,
} from '../domain/event/event-type';
import {
  EventItemRequest,
  EventItemResponse,
} from '../domain/event/event-item';

@Injectable({
  providedIn: 'root',
})
export class EventItemService extends AbstractHttpService<
  EventItemRequest,
  EventItemResponse,
  EventTypeRequest,
  number
> {
  constructor(http: HttpClient) {
    super(http);
  }

  protected baseUrl(): string {
    return `${environment.serverUrl}/api/v1/event/items`;
  }
}
