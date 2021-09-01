import { Product } from './../model/product';
import { Category } from './../model/category';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from '../model/user';
import { OrderDetail } from '../order-detail';
import { Checkout } from '../checkout';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstracService {
  constructor(private http: HttpClient) { super(); }


  getAllUsers(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User[]>(API_URL + 'user/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3),
                    catchError(this.handleError))
  }

  getUserById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User>(API_URL + 'user/' + id, { headers: headers})
                .pipe(
                  retry(3),
                  catchError(this.handleError))
  }

  deleteUser(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete(API_URL + 'admin/' + id, { headers: headers})
                  .pipe(
                    catchError(this.handleError))
  }

  updateUser(token: String, id: number, user: User): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<User>(API_URL + 'user/' + id, user, { headers: headers})
                  .pipe(
                    catchError(this.handleError)
                  )
  }

  doesEmailExist(email: string): Observable<boolean> {
    let url = `${API_URL}user/emailcheck`;

    let content: any = {};
    content.email = email;

    let response$: Observable<boolean> = this.http.post<boolean>(url, content);

    return response$;
  }
}
