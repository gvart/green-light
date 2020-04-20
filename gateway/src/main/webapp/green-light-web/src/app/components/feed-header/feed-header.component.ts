import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-feed-header',
  templateUrl: './feed-header.component.html',
  styleUrls: ['./feed-header.component.scss'],
})
export class FeedHeaderComponent implements OnInit {
  @Input() userId: string;
  @Input() userName: string;
  @Input() createdAt: string;
  avatarUrl =
    'https://demos.creative-tim.com/argon-design-system-angular/assets/img/theme/team-4-800x800.jpg';
  constructor() {}

  ngOnInit(): void {}
}
