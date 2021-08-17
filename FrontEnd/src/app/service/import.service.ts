import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Category } from '../model/category';
import { Import } from '../model/import';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ImportService extends AbstracService {

  constructor(private http: HttpClient) { super() }

  createCategory(token: String, category: Category): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Category>(API_URL + 'import/' + 'add-category', category, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  deleteCategory(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<Category>(API_URL + 'import/' + id, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getCategory(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category[]>(API_URL + 'import/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getAllImports(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Import[]>(API_URL + 'import/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }
}
