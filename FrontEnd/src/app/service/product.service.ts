import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Product} from '../model/product';
import {ProductDetail} from '../model/product-detail';
import {WishList} from '../model/wish-list';
import {AbstracService} from './abstrac.service';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends AbstracService {

  constructor(private http: HttpClient) {
    super();
  }

  createProduct(token: String, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Product>(API_URL + 'product/' + 'add-product', formData, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  editProduct(token: String, id: number, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Product>(API_URL + 'product/' + 'edit-product/' + id, formData, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  editProductWithoutImage(token: String, id: number, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Product>(API_URL + 'product/' + 'edit-product-2/' + id, formData, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  deleteProduct(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<Product>(API_URL + 'product/' + id, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  getProductImage(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'image/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProduct(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'get-all', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductImported(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'get-all-imported', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  searchProductByName(token: String, name: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'search-by-name/' + name)
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductByPrice(token: String, check: boolean): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    if (check === true) {
      return this.http.get<Product[]>(API_URL + 'product/' + 'get-all-desc', {headers: headers})
        .pipe(
          retry(3),
          catchError(this.handleError));
    } else {
      return this.http.get<Product[]>(API_URL + 'product/' + 'get-all-asc', {headers: headers})
        .pipe(
          retry(3),
          catchError(this.handleError));
    }

  }

  getTop4NewProduct(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'product/' + 'get-top-4-new', {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product>(API_URL + 'product/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductByCategoryId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product>(API_URL + 'product/' + 'get-by-category-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getImportedProductByCategoryId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product>(API_URL + 'product/' + 'get-imported-by-category-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductDetailById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getProductDetailByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + 'get-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getAllProductDetailByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + 'get-all-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getSameProductDetail(token: String, id: number, ram: string, color: string, memmory: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + 'get-same-detail/' +
      id + '/' + ram + '/' + color + '/' + memmory, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  countSameProductDetail(token: String, id: number, ram: string, color: string, memmory: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + 'count-same-detail/' +
      id + '/' + ram + '/' + color + '/' + memmory, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  countProductDetailByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'product-detail/' + 'count-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  addToWishList(token: String, wishList: WishList): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Product>(API_URL + 'wish-list/' + 'add', wishList, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  getWishListByUserId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail>(API_URL + 'wish-list/' + 'user-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  removeWishList(token: string, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.delete<ProductDetail>(API_URL + 'wish-list/' + id, {headers: headers})
      .pipe(
        catchError(this.handleError));
  }

  getReviewsByProductId(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'review/' + 'get-by-product-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

  getReviewsById(token: String, id: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get(API_URL + 'review/' + 'get-by-id/' + id, {headers: headers})
      .pipe(
        retry(3),
        catchError(this.handleError));
  }

}
