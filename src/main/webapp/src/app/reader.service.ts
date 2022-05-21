import { HostListener, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  arr: {
    pair: string;
    times: number[];
  }[] = [];

  chartData;

  refresh(){
    this.chartData = {
      labels: this.arr.map(e => {return e.pair}),
      datasets: [{
        label: 'Średni czas lotu dla pary',
        data: this.arr.map(e => {
          let avg = 0;
          e.times.forEach(time => {
            avg += time
          })
          avg = avg/e.times.length
          return avg
        }),
      }]
    }
  }

}
