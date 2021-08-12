import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Category } from '../model/category';
import { CatogeryRequest } from '../request/catogery-request';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstracService {

  constructor(private http: HttpClient) { super(); }

  createCategory(token: String, category: CatogeryRequest): Observable<Category> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Category>(API_URL + 'admin/' + 'add-category', category, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getCategory(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category[]>(API_URL + 'admin/' + 'category', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }
}
