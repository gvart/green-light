import { Component, Input } from '@angular/core';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-like-button',
  templateUrl: './like-button.component.html',
  styleUrls: ['./like-button.component.scss'],
})
export class LikeButtonComponent {
  @Input() eventId: number;
  @Input() isLiked: boolean;
  @Input() likes: number;

  constructor(private eventService: EventService) {}

  public triggerEventLike() {
    this.eventService.triggerEventLike(this.eventId).subscribe(() => {
      this.isLiked = !this.isLiked;
      if (this.isLiked) {
        this.likes++;
      } else {
        this.likes--;
      }
    });
  }
}
