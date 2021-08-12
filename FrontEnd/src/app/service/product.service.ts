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

  createProduct(token: String, productRequest: ProductRequest): Observable<Product> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Product>(API_URL + 'admin/' + 'add-product', productRequest, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProduct(token: String): Observable<Product[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'admin/' + 'product', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

}
