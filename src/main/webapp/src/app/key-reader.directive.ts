import {Directive, HostBinding, HostListener} from '@angular/core';
import {ReaderService} from './reader.service';
import {BiometryService} from "./services/biometry.service";

@Directive({
  selector: '[KeyReader]',
})
export class KeyReaderDirective {
  lastEvent;

  //Czass po któym nie liczy zmian ( uzytkownik przestal pisac)
  registeringTime = 2000;
  //Znaki wyciągnięte spod kontroli
  excluded: string[] = [' ', 'Backspace']

  //Dodanie klasy
  @HostBinding('class')
  elementClass = 'reader-area';

  @HostListener('keyup', ['$event']) onClick(e: any) {

    if (!this.excluded.includes(e.key)) {
      console.log(e);
      if (this.lastEvent) {
        let pair = this.lastEvent.key + e.key;
        let i = this.service.arr.findIndex((e) => e.pair == pair);
        let time: number = e.timeStamp - this.lastEvent.timeStamp;
        if (time < this.registeringTime) {
          if (i > -1) this.service.arr[i].times.push(time.toFixed(2));
          else
            this.service.arr.push({
              pair: pair,
              times: [time.toFixed(2)],
            });
        } else
          this.lastEvent = null;

      }
      this.lastEvent = e;

      console.log(this.service.arr);
      this.service.refreshChart();

      this.biometry.subject.next();
    }
  }

  constructor(
    private service: ReaderService,
    private biometry: BiometryService) {
  }
}
