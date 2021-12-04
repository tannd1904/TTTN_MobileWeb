import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Category} from '../model/category';
import {AbstracService} from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstracService {

  constructor(private http: HttpClient) {
    super();
  }

  createCategory(token: String, category: Category): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Category>(API_URL + 'category/' + 'add-category', category, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  deleteCategory(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<Category>(API_URL + 'category/' + id, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  getCategory(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category[]>(API_URL + 'category/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getCategoryById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category>(API_URL + 'category/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }
}
