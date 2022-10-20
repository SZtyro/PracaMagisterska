import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {interval, Observable, of, Subject, Subscription, timer} from "rxjs";
import {ReaderService} from "../reader.service";
import {debounce} from "rxjs/operators";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class BiometryService {

  readonly api = '/api/biometry'
  period: number = 5;
  subscription: Subscription;

  subject = new Subject();

  constructor(
    private http: HttpClient,
    private router: Router,
    private reader: ReaderService) {

    this.controlWork();
  }

  controlWork() {
    this.subscription = this.subject
      .pipe(debounce(i => interval(this.period * 1000)))
      .subscribe(() => {
        if (this.reader.arr.length > 0)
          this.http.post(this.api, this.reader.arr)
            .subscribe(() => {
                this.reader.clearData();
                this.router.navigate(['/home'])
              },
              error => {
                if (error.status == 302 || error.status == 401) {
                  let results: any[] = JSON.parse(localStorage.getItem("results"));
                  if (!results) results = [];
                  results.push({msg: error.error.message, date: new Date()});
                  localStorage.setItem("results",JSON.stringify(results));
                  alert("Zostaniesz wylogowany z powodu wykrycia podejrzanych zachowań. Przepisz wynik: " + error.error.message)
                  window.location.reload();
                }
              }
            )
      })
  }

  disableControl() {
    if (this.subscription)
      this.subscription.unsubscribe();
    else
      throw Error("Subscription variable is null");
  }

  protect(request: Observable<any>): Observable<any> {
    let subject = new Subject();
    this.http.post(this.api, this.reader.arr).subscribe(
      () => {
        request.subscribe(subject.next, subject.error)
      },
      subject.error
    )
    return subject;
  }
}
