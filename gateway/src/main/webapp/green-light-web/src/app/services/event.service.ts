import { Injectable } from '@angular/core';
import { AbstractHttpService } from './abstract-http.service';
import { environment } from '../../environments/environment';
import {
  EventRequest,
  EventDetailsResponse,
  EventShortResponse,
} from '../domain/event';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EventService extends AbstractHttpService<
  EventDetailsResponse,
  EventShortResponse,
  EventRequest,
  number
> {
  constructor(http: HttpClient) {
    super(http);
  }

  protected baseUrl(): string {
    return `${environment.serverUrl}/api/v1/event/events`;
  }

  findAll(): Observable<EventShortResponse[]> {
    return this.http.get<EventShortResponse[]>(`${this.baseUrl()}?type=short`);
  }

  triggerEventLike(id: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl()}/${id}/like`, null);
  }
}
