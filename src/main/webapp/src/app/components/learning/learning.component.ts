import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/reader.service';
import { BackendService } from 'src/app/services/backend.service';
import {BiometryService} from "../../services/biometry.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss'],
})
export class LearningComponent implements OnInit {

  text:string;

  $words : Observable<any> = this.http.get("/api/word")

  constructor(
    private reader: ReaderService,
    private backend: BackendService,
    private biometry : BiometryService,
    private http: HttpClient
    ) {}

  ngOnInit(): void {}

  save() {
    this.biometry.protect(this.backend.saveLearningSession(this.reader.arr))
      .subscribe(() => {
        this.reader.arr = [];
        this.text = ""
      });
  }
}
