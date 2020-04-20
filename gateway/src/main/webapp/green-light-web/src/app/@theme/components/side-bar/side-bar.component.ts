import { Component, OnInit } from '@angular/core';
import { MENU } from './menu';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss'],
})
export class SideBarComponent implements OnInit {
  public menuItems = MENU;

  constructor() {}

  ngOnInit(): void {}
}
