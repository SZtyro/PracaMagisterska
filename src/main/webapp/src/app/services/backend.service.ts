import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  readonly host = 'localhost:8080'

  constructor( private http : HttpClient) { }

  saveLearningSession(body){
    return this.http.post(`/learning`, body)
  }

}
