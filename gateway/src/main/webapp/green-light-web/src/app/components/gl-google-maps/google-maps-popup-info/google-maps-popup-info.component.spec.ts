import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleMapsPopupInfoComponent } from './google-maps-popup-info.component';

describe('GoogleMapsPopupInfoComponent', () => {
  let component: GoogleMapsPopupInfoComponent;
  let fixture: ComponentFixture<GoogleMapsPopupInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GoogleMapsPopupInfoComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleMapsPopupInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
