import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Product } from '../model/product';
import { ProductRequest } from '../request/product-request';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends AbstracService {

  constructor(private http: HttpClient) { super(); }

  createProduct(token: String, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Product>(API_URL + 'product/' + 'add-product', formData, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  deleteProduct(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<Product>(API_URL + 'product/' + id, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getProduct(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getTop4NewProduct(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'get-top-4-new', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product>(API_URL + 'product/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

}
