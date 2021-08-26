import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../model/order';
import { OrderDetail } from '../model/order-detail';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends AbstracService {

  constructor(private http: HttpClient) { super() }

  createOrder(token: String, order: Order): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order/' + 'add', order, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  createOrderDetail(token: String, order: OrderDetail): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order-detail/' + 'add', order, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
}
