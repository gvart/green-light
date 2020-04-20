import { NgModule } from '@angular/core';
import { CreateUpdateComponent } from './components/create-update/create-update.component';
import { EventRoutingModule } from './event-routing.module';
import { EventComponent } from './event.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { CommonModule } from '@angular/common';
import {
  ClrCommonFormsModule,
  ClrDatepickerModule,
  ClrIconModule,
  ClrInputModule,
  ClrSelectModule,
  ClrWizardModule,
} from '@clr/angular';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListComponent } from './components/list/list.component';
import { DetailsComponent } from './components/details/details.component';
import { ThemeModule } from '@gl/theme/theme.module';
import { FeedHeaderComponent } from '@gl/components/feed-header/feed-header.component';
import { LikeButtonComponent } from '@gl/components/like-button/like-button.component';
import { GlGoogleMapsModule } from '@gl/components/gl-google-maps/gl-google-maps.module';

const COMPONENTS = [
  CreateUpdateComponent,
  DetailsComponent,
  EventComponent,
  FeedHeaderComponent,
  LikeButtonComponent,
  ListComponent,
];

const FRAMEWORK_MODULES = [CommonModule, ReactiveFormsModule, FormsModule];
const CLR_MODULES = [
  ClrSelectModule,
  ClrCommonFormsModule,
  ClrInputModule,
  ClrIconModule,
  ClrDatepickerModule,
  ClrWizardModule,
];

@NgModule({
  declarations: [...COMPONENTS],
  imports: [
    ...FRAMEWORK_MODULES,
    ...CLR_MODULES,
    EventRoutingModule,
    CKEditorModule,
    ThemeModule,
    GlGoogleMapsModule,
  ],
})
export class EventModule {}
