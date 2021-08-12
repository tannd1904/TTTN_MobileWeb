
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { AbstracService } from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProviderService extends AbstracService {

  constructor(private http: HttpClient) { super(); }

  createProvider(token: String, provider: Provider): Observable<Provider> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Provider>(API_URL + 'admin/' + 'add-provider', provider, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProvider(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Provider[]>(API_URL + 'admin/' + 'provider', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }
}
