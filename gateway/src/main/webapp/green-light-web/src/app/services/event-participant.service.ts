import { Injectable } from '@angular/core';
import { AbstractHttpService } from './abstract-http.service';
import { EventParticipant, EventParticipantRequest } from '../domain/event';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { AuthService } from '@gl/auth/service/auth.service';
import { flatMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class EventParticipantService extends AbstractHttpService<
  EventParticipant,
  EventParticipant,
  EventParticipantRequest,
  number
> {
  constructor(http: HttpClient, private authService: AuthService) {
    super(http);
  }

  protected baseUrl(): string {
    return `${environment.serverUrl}/api/v1/event/participants`;
  }

  createForCurrentUser(eventId: number): Observable<EventParticipant> {
    return this.authService.getCurrentUser().pipe(
      flatMap(user =>
        super.create({
          userId: user.id,
          eventId,
        })
      )
    );
  }
}
