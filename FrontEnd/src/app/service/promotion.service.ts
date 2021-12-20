import { Injectable } from '@angular/core';
import {AbstracService} from './abstrac.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Accessory} from '../model/accessory';
import {catchError, retry} from 'rxjs/operators';
import {Promotion} from '../model/promotion';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class PromotionService extends AbstracService{

  constructor(private http: HttpClient) { super(); }

  getPromotions(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Promotion[]>(API_URL + 'promotion/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getPromotionByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Promotion[]>(API_URL + 'promotion/get-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getPromotionByAccessoryId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Promotion[]>(API_URL + 'promotion/get-by-accessory-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }
}
