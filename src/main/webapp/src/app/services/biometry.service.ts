import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {interval, Observable, of, Subject, timer} from "rxjs";
import {ReaderService} from "../reader.service";
import {debounce} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BiometryService {

  readonly api = '/api/biometry'

  constructor(
    private http: HttpClient,
    private reader: ReaderService) {


    interval(5000).subscribe(()=>{
      this.http.post(this.api, this.reader.arr)
        .subscribe(
          () => {this.reader.clearData()},
          console.error
        )
    })

  }

  protect(request: Observable<any>): Observable<any> {
    let subject = new Subject();

    this.http.post(this.api, this.reader.arr).subscribe(
      success => {
        request.subscribe(subject.next, subject.error)
      }, subject.error
    )

    return subject;
  }
}
