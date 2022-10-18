import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  $chunks: Observable<any> = this.http.get("api/biometry")
  chunks: any = [];
  readyCount = 0;

  constructor(private http: HttpClient) {
  }

  getStyle(number) {
    if (number == 21)
      return "background-color: seagreen"
    else if (number > 17 && number < 21)
      return "background-color: goldenrod"
    else return "background-color: darkred"
  }

  ngOnInit(): void {
    this.$chunks.subscribe(chunks => {
      this.chunks = chunks;
      this.chunks.forEach(chunk => {
        if(chunk.times.length == 21){
          this.readyCount++;
        }
      })

      this.chunks = this.chunks.filter(e => e.times.length > 3);
    })
  }

  getAvgTime(arr:number[]){
    let sum = 0;
    arr.forEach(value => {
      sum += Number(value);
    })
    return (sum/arr.length).toFixed(1);
  }

}
