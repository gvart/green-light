import { Pipe, PipeTransform } from '@angular/core';
import { DateService } from '../../services/date.service';
import * as moment from 'moment';

@Pipe({ name: 'dateFormat' })
export class DateFormatPipe implements PipeTransform {
  transform(date: string): string {
    return moment(date).format(DateService.APPLICATION_FORMAT);
  }
}
