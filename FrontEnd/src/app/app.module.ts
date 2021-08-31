import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { AdminModule } from './admin/admin.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './user/home/home.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { NgxPaginationModule } from 'ngx-pagination';

import { LoginGuard } from './guard/login.guard';
import { LoginInterceptor } from './interceptor/login.interceptor';
import { UserService } from './service/user.service';
import { AuthService } from './service/auth.service';
import { CartService } from './service/cart.service';
import { CountService } from './service/count.service';
import { TokenStorageService } from './service/token-storage.service';
import { AuthInterceptor } from './service/auth.interceptor';
import { ProductGridComponent } from './user/product-grid/product-grid.component';
import { ProductDetailComponent } from './user/product-detail/product-detail.component';
import { CheckoutComponent } from './user/checkout/checkout.component';
import { CompareComponent } from './user/compare/compare.component';
import { CartComponent } from './user/cart/cart.component';
import { WishlistComponent } from './user/wishlist/wishlist.component';
import { DataTablesModule } from 'angular-datatables';
import { ListOrderComponent } from './user/list-order/list-order.component';
import { OrderDetailComponent } from './user/order-detail/order-detail.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { SearchComponent } from './user/search/search.component';
import { AutocompleteLibModule } from 'angular-ng-autocomplete';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    PageNotFoundComponent,
    ProductGridComponent,
    ProductDetailComponent,
    CheckoutComponent,
    CompareComponent,
    CartComponent,
    WishlistComponent,
    ListOrderComponent,
    OrderDetailComponent,
    SearchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AdminModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    Ng2SearchPipeModule,
    AutocompleteLibModule,
    CommonModule,
    DataTablesModule
  ],
  //, providers: [UserService, AuthService, CartService, CountService, TokenStorageService ,LoginGuard, 
  //   {provide: HTTP_INTERCEPTORS, useClass:  AuthInterceptor, multi: true}
  // ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
