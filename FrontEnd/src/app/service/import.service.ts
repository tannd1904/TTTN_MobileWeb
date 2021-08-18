import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Category } from '../model/category';
import { ImportDetail } from '../model/import-detail';
import { ImportVoucher } from '../model/import-voucher';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ImportService extends AbstracService {

  constructor(private http: HttpClient) { super() }

  getAllImports(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ImportVoucher[]>(API_URL + 'import/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getImportById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ImportVoucher[]>(API_URL + 'import/' + id, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getAllImportDetails(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ImportDetail[]>(API_URL + 'import-detail/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  createImportDetail(token: String, importDetail: ImportDetail): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'import-detail/' + 'add', importDetail, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  createImport(token: String, importVoucher: ImportVoucher): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'import/' + 'add', importVoucher, {headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
}
