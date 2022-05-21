import { Component } from '@angular/core';
import { ReaderService } from './reader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Magisterka';

  constructor(private r : ReaderService){}
}
