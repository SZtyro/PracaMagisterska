import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { ChartData, ChartOptions } from 'chart.js';
import { ReaderService } from 'src/app/reader.service';

@Component({
  selector: 'app-page-a',
  templateUrl: './page-a.component.html',
  styleUrls: ['./page-a.component.scss'],
})
export class PageAComponent implements OnInit {
  // data: ChartData = {
  //   labels: this.r.arr.map(e => {return e.pair}),
  //   datasets: [{
  //     label: 'Czasy poszczególnych par',
  //     data: this.r.arr.map(e => {
  //       let avg = 0;
  //       e.times.forEach(time => {
  //         avg += time
  //       })
  //       avg = avg/e.times.length
  //       return avg
  //     }),

  //   }]
  // };

  times: number[] = []
  selectedPair
  avgTimes

  pairData: ChartData = {
    labels: [],
    datasets: [{
      data: []
    }],
  };

  options2: ChartOptions = {
    backgroundColor: '#332211',
  }

  options: ChartOptions = {
    onClick: (e) => {
      //@ts-ignore
      const points = e.chart.getElementsAtEventForMode(
        e,
        'nearest',
        { intersect: true },
        true
      );

      if (points.length) {
        const firstPoint = points[0];
        const label = this.r.chartData.labels[firstPoint.index];
        this.selectedPair = label;
        console.log(label);

        let elem = this.r.arr.find((e) => e.pair == label);
        console.log(elem)
        let p: ChartData = {
          labels: [],
          datasets: []
        }
        p['labels'] = []
        elem.times.forEach((e,index) => {p['labels'].push(index) })
        
        
        console.log(this.pairData.labels);
        p['datasets'] = [
          {
            label: 'a',
            data: elem.times,
          },
        ];

        this.times = elem.times
        this.avgTimes = 0;
        elem.times.forEach(e => {
          this.avgTimes += e;
        })
        this.avgTimes = this.avgTimes / elem.times.length
        this.pairData = p;
        console.log(this.pairData)
      }
    },
  };
  constructor(public r: ReaderService) {}

  ngOnInit(): void {}
}
