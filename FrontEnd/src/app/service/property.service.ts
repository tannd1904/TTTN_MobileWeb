import { Injectable } from '@angular/core';
import {AbstracService} from './abstrac.service';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Category} from '../model/category';
import {catchError, retry} from 'rxjs/operators';
import {Property} from '../model/property';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class PropertyService extends AbstracService{

  constructor(private http: HttpClient) {
    super();
  }

  getAllProperties(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Property[]>(API_URL + 'property/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getPropertyByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Property[]>(API_URL + 'property/get-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }
}
