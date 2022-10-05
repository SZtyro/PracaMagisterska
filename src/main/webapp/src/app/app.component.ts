import {Component, HostListener, OnInit} from '@angular/core';
import { ReaderService } from './reader.service';
import {BackendService} from "./services/backend.service";
import {BiometryService} from "./services/biometry.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Magisterka';
  screenWidth: number = window.innerWidth;
  isLoading: boolean = false;
  userProfile;

  constructor(
    private r : ReaderService,
    public backend: BackendService,
    private biometry: BiometryService
    ){}

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.screenWidth = window.innerWidth;
  }

  ngOnInit() {
    this.backend.getUserProfile().subscribe(
        profile => this.userProfile = profile
    )
  }

  logout(){
    this.backend.logOut().subscribe(() => window.location.reload())
  }
}
