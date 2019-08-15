import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sample} from '../model/sample';

@Injectable({
  providedIn: 'root'
})
export class SampleService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = '/api/samples';
  }

  public listAll(): Observable<Sample[]> {
    return this.http.get<Sample[]>(`${this.url}`);
  }

  public delete(): Observable<Sample[]> {
    return this.http.get<Sample[]>(`${this.url}`);
  }
}
