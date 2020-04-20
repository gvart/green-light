export class EventParticipant {
  id: number;
  eventId: number;
  userId: string;
  userName: string;
}

export class EventParticipantRequest {
  eventId: number;
  userId: string;

  constructor(eventId: number, userId: string) {
    this.eventId = eventId;
    this.userId = userId;
  }
}
