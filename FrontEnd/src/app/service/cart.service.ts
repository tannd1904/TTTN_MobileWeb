import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import { retry } from 'rxjs/internal/operators/retry';
import { Cart } from '../cart';
import { Checkout } from '../checkout';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/addcart/';
const API_URL_ = 'http://localhost:8080/api/order/';

@Injectable({
  providedIn: 'root'
})
export class CartService extends AbstracService {

  constructor(private http: HttpClient) { super(); }

  getCartDetailByUser(token: String, userId: number): Observable<Cart[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);

    return this.http.get<Cart[]>(API_URL + 'getCartsByUserId/' + userId,{ headers: headers})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  addToCart(token: String, productId: number, userId: number, qty: number, price: number): Observable<Cart[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Cart[]>(API_URL + 'addProduct', {
      productId,
      userId,
      qty,
      price
    },{ headers: headers})
    .pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  checkout(token: String, order_date: String, amount: number, receiver: String, address: String, phone_number: String, status: number, user_id: number): Observable<Checkout>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Checkout>(API_URL_ + 'checkout', {
      order_date,
      amount,
      receiver,
      address,
      phone_number,
      status,
      user_id
    },{ headers: headers})
    .pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  getOrderList(token: String, userId: number):Observable<Checkout[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Checkout[]>(API_URL_ + 'listOrder/' + userId,{ headers: headers})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  deleteCartById(token: String, id: number):Observable<Cart[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<Cart[]>(API_URL + 'delete/' + id,{ headers: headers})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  updateCartById(token: String, id: number, quantity: number):Observable<Cart[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Cart[]>(API_URL + 'update/' + id,{quantity},{headers: headers})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  countCartById(token: String, userId: number):Observable<any>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'count/' + userId,{headers: headers, responseType: 'text'})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  getCartByIdCart(token: String, cartId: number):Observable<Cart>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Cart>(API_URL + 'getCartByIdCart/' + cartId,{headers: headers})
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  getCart() {
    return JSON.parse(sessionStorage.getItem('CART') || '[]')
  }

  saveCart(cart: any) {
    window.sessionStorage.removeItem('CART');
    window.sessionStorage.setItem('CART', JSON.stringify(cart));
  }
}
