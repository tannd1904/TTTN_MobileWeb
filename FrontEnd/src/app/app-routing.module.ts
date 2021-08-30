import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderDetail } from './order-detail';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CartComponent } from './user/cart/cart.component';
import { CheckoutComponent } from './user/checkout/checkout.component';
import { CompareComponent } from './user/compare/compare.component';
import { HomeComponent } from './user/home/home.component';
import { ListOrderComponent } from './user/list-order/list-order.component';
import { LoginComponent } from './user/login/login.component';
import { OrderDetailComponent } from './user/order-detail/order-detail.component';
import { ProductDetailComponent } from './user/product-detail/product-detail.component';
import { ProductGridComponent } from './user/product-grid/product-grid.component';
import { RegisterComponent } from './user/register/register.component';
import { SearchComponent } from './user/search/search.component';
import { WishlistComponent } from './user/wishlist/wishlist.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  // { path: 'changeInfo', component: ChangeInfoComponent },
  { path: 'product-grid', component: ProductGridComponent },
  { path: 'product-detail/:id', component: ProductDetailComponent},
  { path: 'checkout', component: CheckoutComponent},
  { path: 'compare', component: CompareComponent},
  { path: 'cart', component: CartComponent},
  { path: 'wishlist', component: WishlistComponent},
  { path: 'list-orders', component: ListOrderComponent},
  { path: 'order-detail/:id', component: OrderDetailComponent},
  { path: 'search', component: SearchComponent},
  //TODO: Add interceptor and Page Not Found Page
  // { path: '**', component: PageNotFoundComponent},

  { path: '', redirectTo: 'admin', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
