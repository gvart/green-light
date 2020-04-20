import { Component, OnInit } from '@angular/core';
import { EventService } from '@gl/services/event.service';
import { EventShortResponse } from '@gl/domain/event';

@Component({
  selector: 'app-event-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  events: EventShortResponse[];
  error: any;

  constructor(private service: EventService) {}

  ngOnInit(): void {
    this.fetchEvents();
  }

  fetchEvents() {
    this.error = null;
    this.service.findAll().subscribe(
      it => {
        this.events = it;
      },
      error => (this.error = error)
    );
  }
}
