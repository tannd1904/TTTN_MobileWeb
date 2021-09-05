import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from '../model/user';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends AbstracService {

  constructor(private http: HttpClient) { super();}

  getAllEmpl(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User[]>(API_URL + 'employee/' + 'get-all', { headers: headers})
                  .pipe(
                    retry(3),
                    catchError(this.handleError));
  }

  getEmplById(id: number, token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User>(API_URL + 'employee/' + id, { headers: headers})
                  .pipe(
                    retry(3),
                    catchError(this.handleError));
  }

  countEmployeeAddedThisMonth(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User>(API_URL + 'employee/' + 'count-in-current-month', { headers: headers})
                  .pipe(
                    retry(3),
                    catchError(this.handleError));
  }

  countEmployeeAddedThisYear(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<User>(API_URL + 'employee/' + 'count-in-current-year', { headers: headers})
                  .pipe(
                    retry(3),
                    catchError(this.handleError));
  }

  updateEmployee(token: String, id: number, user: User): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<User>(API_URL + 'employee/' + id, user, { headers: headers})
                  .pipe(
                    catchError(this.handleError)
                  )
  }
}
