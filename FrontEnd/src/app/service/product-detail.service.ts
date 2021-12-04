import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {ProductDetail} from '../model/product-detail';
import {AbstracService} from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService extends AbstracService {

  constructor(private http: HttpClient) {
    super();
  }

  createProductDetail(token: String, formData: ProductDetail): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'product-detail/' + 'add', formData, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

}
