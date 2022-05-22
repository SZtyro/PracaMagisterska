import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/reader.service';
import { BackendService } from 'src/app/services/backend.service';

@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  constructor(
    private reader: ReaderService,
    private backend: BackendService
  ) { }

  ngOnInit(): void {
  }

  save(){
    this.backend.saveLearningSession(this.reader.arr).subscribe()
  }
}
