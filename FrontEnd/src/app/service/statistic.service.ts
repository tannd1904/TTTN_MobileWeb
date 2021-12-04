import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {AbstracService} from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class StatisticService extends AbstracService {
  constructor(private http: HttpClient) {
    super();
  }

  countUserRegisThisMonth(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'account/year', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  countOrdersInThisYear(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'order/year', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  countOrdersInThisMonth(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'order/month', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  countInventoryThisYear(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'inventory/year', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  calculateRevenueAllTime(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'revenue/all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  calculateRevenueThisYear(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'revenue/year', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  calculateRevenueThisMonth(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'revenue/month', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getNumberOfOrders(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'order/all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getNumberOfProducts(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'product/all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getNumberOfUsers(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'statistic/' + 'user/all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }
}
