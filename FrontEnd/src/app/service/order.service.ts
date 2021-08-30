import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Order } from '../model/order';
import { OrderDetail } from '../model/order-detail';
import { Review } from '../model/review';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends AbstracService {

  constructor(private http: HttpClient) { super() }

  createReview(token: String, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Review>(API_URL + 'review/' + 'add-review', formData, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getAllOrders(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'order/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getAllOrdersByUserId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'order/' + 'get-by-user-id/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getOrderById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order>(API_URL + 'order/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getAllOrderDetails(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderDetail[]>(API_URL + 'order-detail/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getOrderDetailById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderDetail>(API_URL + 'order-detail/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getOrderDetailByOrderId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderDetail>(API_URL + 'order-detail/' + 'by-order-id/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  createOrder(token: String, order: Order): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order/' + 'add', order, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  createOrderDetail(token: String, orderDetail: OrderDetail): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order-detail/' + 'add-detail', orderDetail, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  createListOrderDetail(token: String, listOrderDetail: any): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order-detail/' + 'add-list', listOrderDetail, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  confirmOrder(token: String, id: number, employeeId: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'order/' + 'confirm/' + id + '/' + employeeId, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  receiveOrder(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'order/' + 'receive/' + id , {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  cancelOrder(token: String, id: number, employeeId: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'order/' + 'cancel/' + id + '/' + employeeId, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  cancelOrderByUser(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'order/' + 'cancel/' + id, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
}
