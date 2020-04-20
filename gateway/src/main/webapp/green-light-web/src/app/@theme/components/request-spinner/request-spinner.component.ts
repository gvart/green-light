import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-request-spinner',
  templateUrl: './request-spinner.component.html',
  styleUrls: ['./request-spinner.component.scss'],
})
export class RequestSpinnerComponent {
  @Input() waitFor: any;
  @Input() error: any;
  @Input() defaultErrorMessage = true;
  @Output() retry: EventEmitter<any> = new EventEmitter<any>();

  defaultMessage =
    'Ooops something is going wrong, please contact system administrator.';

  constructor() {}

  retry() {
    this.retry.emit();
  }

  show() {
    return !this.waitFor;
  }
}
