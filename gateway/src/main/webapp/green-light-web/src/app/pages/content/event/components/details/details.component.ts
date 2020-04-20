import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '@gl/services/event.service';
import { EventDetailsResponse } from '@gl/domain/event';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { LocationMetadata } from '@gl/components/gl-google-maps/location-metadata';
import { EventItemService } from '@gl/services/event-item.service';
import { EventItemResponse } from '@gl/domain/event';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-event-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
})
export class DetailsComponent implements OnInit {
  event: EventDetailsResponse;
  isReady = false;
  trustedHtml: SafeHtml;
  eventItems: EventItemResponse[];
  private eventId: number;

  constructor(
    activatedRoute: ActivatedRoute,
    private eventService: EventService,
    private eventItemService: EventItemService,
    private sanitizer: DomSanitizer
  ) {
    activatedRoute.paramMap.subscribe(
      paramMap => (this.eventId = +paramMap.get('id'))
    );
  }

  ngOnInit(): void {
    this.fetchEventData();
  }

  private fetchEventData() {
    forkJoin([
      this.eventService.findOne(this.eventId),
      this.eventItemService.findAll(),
    ]).subscribe(results => {
      this.eventItems = results[1].filter(
        it => results[0].items.indexOf(it.id) !== -1
      );
      this.event = results[0];
      this.trustedHtml = this.sanitizer.bypassSecurityTrustHtml(
        unescape(results[0].content)
      );
      this.isReady = true;
    });
  }

  getEventLocation(): LocationMetadata {
    return new LocationMetadata(
      new google.maps.LatLng(
        this.event.geoLocation.lat,
        this.event.geoLocation.lng
      ),
      this.event.geoLocation.street
    );
  }
}
