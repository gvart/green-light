import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleMapsLocationPickerComponent } from './google-maps-location-picker.component';

describe('GoogleMapsLocationPickerComponent', () => {
  let component: GoogleMapsLocationPickerComponent;
  let fixture: ComponentFixture<GoogleMapsLocationPickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GoogleMapsLocationPickerComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleMapsLocationPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
