import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ProductDetail } from '../model/product-detail';
import { ProductRequest } from '../request/product-request';
import { ProductDetailComponent } from '../user/product-detail/product-detail.component';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService extends AbstracService {

  constructor(private http: HttpClient) { super(); }

  createProductDetail(token: String, formData: ProductDetail): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'product-detail/' + 'add', formData, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getProductDetail(token: String, productId: String): Observable<ProductDetail[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail[]>(API_URL + 'admin/product-detail/' + productId, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }
}
