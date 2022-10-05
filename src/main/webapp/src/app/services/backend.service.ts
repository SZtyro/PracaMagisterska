import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  readonly host = 'api'

  constructor( private http : HttpClient) { }

  saveLearningSession(body){
    return this.http.post(`/learning`, body)
  }

  logOut(){
    return this.http.post(`${this.host}/user/logout`, null)
  }

  getUserProfile(){
    return this.http.get(`${this.host}/user`)
  }


}
