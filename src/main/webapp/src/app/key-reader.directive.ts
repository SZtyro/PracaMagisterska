import { Directive, HostListener } from '@angular/core';
import { ReaderService } from './reader.service';

@Directive({
  selector: '[KeyReader]',
})
export class KeyReaderDirective {
  lastEvent;

  //Czass po któym nie liczy zmian ( uzytkownik przestal pisac)
  registeringTime = 2000;

  @HostListener('keyup', ['$event']) onClick(e: any) {
    console.log(e);
    if (this.lastEvent) {
      let pair = this.lastEvent.key + e.key;
      let i = this.service.arr.findIndex((e) => e.pair == pair);
      let time = e.timeStamp - this.lastEvent.timeStamp;
      if (time < this.registeringTime) {
        if (i > -1) this.service.arr[i].times.push(time);
        else
          this.service.arr.push({
            pair: pair,
            times: [time],
          });
      }else
        this.lastEvent = null;

    }
    this.lastEvent = e;

    console.log(this.service.arr);
    this.service.refreshChart();
  }

  constructor(private service: ReaderService) {}
}
