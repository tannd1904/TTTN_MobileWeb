import { HomeComponent } from './home/home.component';
import { ListCategoriesComponent } from './categories/list-categories/list-categories.component';
import { AdminComponent } from './admin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
import { InventoryReceivingVoucherComponent } from './inventory-receiving-voucher/inventory-receiving-voucher.component';
import { AddInventoryReceivingVoucherDetailComponent } from './import-voucher/add-inventory-receiving-voucher-detail/add-inventory-receiving-voucher-detail.component';
import { ProductComponent } from './product/list_product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ListComponent } from './import-voucher/list/list.component';
import { OrderListComponent } from './order/order-list/order-list.component';
import { OrderDetailComponent } from './order/order-detail/order-detail.component';

const routes: Routes = [
  { path: 'admin', component: AdminComponent, 
      children: [
        {path: '', component: HomeComponent},
        {path: 'products', component: ProductComponent},
        {path: 'category', component: ListCategoriesComponent},
        // {path: 'users', component: UsersComponent},
        // {path: 'edit-user/:id', component: EditUserComponent},
        {path: 'inventory', component: ListComponent },
        {path: 'inventory/add', component: AddInventoryReceivingVoucherDetailComponent },
        {path: 'products/product/:id', component: ProductDetailComponent },
        { path: 'list-orders', component: OrderListComponent},
        { path: 'order-detail/:id', component: OrderDetailComponent},
        // { path: '**', component: PageNotFoundComponent}
      ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
