import { Injectable } from '@angular/core';
import {AbstracService} from './abstrac.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../model/category';
import {catchError, retry} from 'rxjs/operators';
import {Accessory} from '../model/accessory';
import {AccessoryCate} from '../model/accessory-cate';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService extends AbstracService{

  constructor(private http: HttpClient) {
    super();
  }

  getAccessory(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Accessory[]>(API_URL + 'accessory/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getAccessoryById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Accessory[]>(API_URL + 'accessory/get-by-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getAccessoryCate(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<AccessoryCate[]>(API_URL + 'accessoryCate/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getAccessoryCateById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Accessory>(API_URL + 'accessoryCate/get-by-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }
}
