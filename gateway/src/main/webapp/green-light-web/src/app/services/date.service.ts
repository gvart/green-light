import { Injectable } from '@angular/core';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root',
})
export class DateService {
  constructor() {}

  public static INPUT_FORMAT = 'YYYY-MM-DD';
  public static APPLICATION_FORMAT = 'DD/YY/YYYY';

  public static formatAppDate(date: Date): string {
    return moment(date).format(DateService.APPLICATION_FORMAT);
  }

  public formatInputDate(date: Date): string {
    return moment(date).format(DateService.INPUT_FORMAT);
  }
}
