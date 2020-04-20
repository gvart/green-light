import { Injectable } from '@angular/core';
import { AbstractHttpService } from './abstract-http.service';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import {
  EventTypeRequest,
  EventTypeResponse,
} from '../domain/event/event-type';

@Injectable({
  providedIn: 'root',
})
export class EventTypeService extends AbstractHttpService<
  EventTypeResponse,
  EventTypeResponse,
  EventTypeRequest,
  number
> {
  constructor(http: HttpClient) {
    super(http);
  }

  protected baseUrl(): string {
    return `${environment.serverUrl}/api/v1/event/types`;
  }
}
