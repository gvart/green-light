import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  @Output() singInClicked = new EventEmitter();
  @Output() singUpClicked = new EventEmitter();
  @Output() logOutClicked = new EventEmitter();

  onSingIn = () => this.singInClicked.emit();

  onSingUp = () => this.singUpClicked.emit();

  logOut = () => this.logOutClicked.emit();
}
