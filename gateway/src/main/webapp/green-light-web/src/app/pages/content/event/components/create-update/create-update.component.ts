import { Component, OnInit, Renderer2, ViewChild } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DateService } from '@gl/services/date.service';

import { Router } from '@angular/router';
import { EventTypeService } from '@gl/services/event-type.service';
import { ClrWizard } from '@clr/angular';
import { GoogleMapsLocationPickerComponent } from '@gl/components/gl-google-maps/google-maps-location-picker/google-maps-location-picker.component';
import { Location } from '@angular/common';
import { EventItemService } from '@gl/services/event-item.service';
import { LocationMetadata } from '@gl/components/gl-google-maps/location-metadata';
import { EventService } from '@gl/services/event.service';
import {
  EventItemResponse,
  EventRequest,
  EventTypeResponse,
} from '@gl/domain/event';
import { EventParticipantService } from '@gl/services/event-participant.service';
import { AuthService } from '@gl/auth/service/auth.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-event-create-update',
  templateUrl: './create-update.component.html',
  styleUrls: ['./create-update.component.scss'],
})
export class CreateUpdateComponent implements OnInit {
  @ViewChild('locationPicker')
  locationPickerModal: GoogleMapsLocationPickerComponent;
  @ViewChild('wizard') wizard: ClrWizard;
  Editor = ClassicEditor;
  form: FormGroup;
  locationMetadata: LocationMetadata;
  selectedEventItems: number[] = [];

  eventRequest: EventRequest;

  eventTypes: EventTypeResponse[];
  eventItems: EventItemResponse[];

  constructor(
    private dateService: DateService,
    private authService: AuthService,
    private eventService: EventService,
    private eventItemService: EventItemService,
    private eventTypeService: EventTypeService,
    private eventParticipantService: EventParticipantService,
    private formBuilder: FormBuilder,
    private location: Location,
    private renderer: Renderer2,
    private router: Router,
    private notifierService: NotifierService
  ) {}

  ngOnInit(): void {
    this.eventRequest = new EventRequest();
    this.locationMetadata = new LocationMetadata(
      new google.maps.LatLng(
        this.eventRequest?.geoLocation?.lat,
        this.eventRequest?.geoLocation?.lng
      ),
      this.eventRequest?.geoLocation?.street
    );
    this.getEventType();
    this.getEventItems();
    this.initForm();
  }

  private getEventType() {
    const subscription = this.eventTypeService.findAll().subscribe(it => {
      this.eventTypes = it;
      subscription.unsubscribe();
    });
  }

  private initForm() {
    this.form = this.formBuilder.group({
      title: [this.eventRequest.title, [Validators.required]],
      type: [this.eventRequest.typeId, [Validators.required]],
      peopleRequired: [
        this.eventRequest.peopleRequired,
        [Validators.required, Validators.min(1)],
      ],
      startsAt: [this.eventRequest.startsAt, [Validators.required]],
      street: [this.eventRequest.geoLocation.street, [Validators.required]],
    });
    this.form.controls.street.disable();
  }

  public getMinDate() {
    return this.dateService.formatInputDate(new Date());
  }

  public setLocation(event: LocationMetadata) {
    this.eventRequest.geoLocation = {
      lat: event.location.lat(),
      lng: event.location.lng(),
      street: event.street,
    };
    this.form.controls.street.setValue(event.street);
  }

  navigateBack() {
    this.location.back();
  }

  public submitForm() {
    if (this.form.valid) {
      this.eventRequest.title = this.form.controls.title.value;
      this.eventRequest.startsAt =
        this.form.controls.startsAt.value.toString() + ' 13:00';
      this.eventRequest.peopleRequired = this.form.controls.peopleRequired.value;
      this.eventRequest.typeId = +this.form.controls.type.value;
      this.eventRequest.items = this.selectedEventItems;

      this.eventService.create(this.eventRequest).subscribe(
        response => {
          this.eventParticipantService
            .createForCurrentUser(response.id)
            .subscribe();

          this.notifierService.notify('default', 'Event created successful.');
          this.router.navigate([`/event/${response.id}`]);
        },
        error => {
          this.notifierService.notify('error', error.message);
        }
      );
    }
  }

  isFormValid() {
    return this.form.valid;
  }

  isLocationPicked() {
    return (
      this.eventRequest.geoLocation?.street !== '' &&
      this.eventRequest.geoLocation?.lat &&
      this.eventRequest.geoLocation?.lng
    );
  }

  isContentNonEmpty() {
    return this.eventRequest.content !== '';
  }

  private getEventItems() {
    this.eventItemService.findAll().subscribe(it => {
      this.eventItems = it;
    });
  }

  onEventItemClicked(card: any, itemId: number) {
    const index = this.selectedEventItems.indexOf(itemId);
    if (index !== -1) {
      this.selectedEventItems.splice(index, 1);
      this.renderer.removeClass(card, 'event-selected');
    } else {
      this.selectedEventItems.push(itemId);
      this.renderer.addClass(card, 'event-selected');
    }
  }
}
