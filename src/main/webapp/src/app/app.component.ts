import { Component, HostListener } from '@angular/core';
import { ReaderService } from './reader.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Magisterka';
  screenWidth: number = window.innerWidth;
  isLoading: boolean = false;

  constructor(
    private r : ReaderService,
    public userService: UserService
    ){}

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.screenWidth = window.innerWidth;
  }
}
