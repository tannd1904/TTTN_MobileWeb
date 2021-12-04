import {HomeComponent} from './home/home.component';
import {ListCategoriesComponent} from './categories/list-categories/list-categories.component';
import {AdminComponent} from './admin.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
  AddInventoryReceivingVoucherDetailComponent
} from './import-voucher/add-inventory-receiving-voucher-detail/add-inventory-receiving-voucher-detail.component';
import {ProductComponent} from './product/list_product/product.component';
import {ProductDetailComponent} from './product/product-detail/product-detail.component';
import {ListComponent} from './import-voucher/list/list.component';
import {OrderListComponent} from './order/order-list/order-list.component';
import {OrderDetailComponent} from './order/order-detail/order-detail.component';
import {ChangeInfoComponent} from '../user/change-info/change-info.component';
import {UserListComponent} from './user-list/user-list.component';
import {EmployeeListComponent} from './employee-list/employee-list.component';

const routes: Routes = [
  {
    path: 'admin', component: AdminComponent,
    children: [
      {path: '', component: HomeComponent},
      {path: 'products', component: ProductComponent},
      {path: 'category', component: ListCategoriesComponent},
      {path: 'list-users', component: UserListComponent},
      {path: 'list-employees', component: EmployeeListComponent},
      // {path: 'users', component: UsersComponent},
      // {path: 'edit-user/:id', component: EditUserComponent},
      {path: 'inventory', component: ListComponent},
      {path: 'inventory/add', component: AddInventoryReceivingVoucherDetailComponent},
      {path: 'products/product/:id', component: ProductDetailComponent},
      {path: 'list-orders', component: OrderListComponent},
      {path: 'order-detail/:id', component: OrderDetailComponent},
      {path: 'changeInfo', component: ChangeInfoComponent},
      // { path: '**', component: PageNotFoundComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
