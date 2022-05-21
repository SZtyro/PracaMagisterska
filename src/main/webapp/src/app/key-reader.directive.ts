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
      let i = this.r.arr.findIndex((e) => e.pair == pair);
      let time = e.timeStamp - this.lastEvent.timeStamp;
      if (time < this.registeringTime) {
        if (i > -1) this.r.arr[i].times.push(time);
        else
          this.r.arr.push({
            pair: pair,
            times: [time],
          });
      }else
        this.lastEvent = null;

    }
    this.lastEvent = e;

    console.log(this.r.arr);
    this.r.refresh();
  }

  constructor(private r: ReaderService) {}
}
