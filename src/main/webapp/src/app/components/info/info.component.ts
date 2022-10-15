import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  $chunks: Observable<any> = this.http.get("api/biometry")

  constructor(private http: HttpClient) { }

  getStyle(number){
    if(number == 21)
      return "color: green"
    else if(number > 17 && number < 21)
        return "color: gold"
    else return "color: red"
  }

  ngOnInit(): void {
  }

}
