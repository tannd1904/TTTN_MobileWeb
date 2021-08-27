import { HomeComponent } from './home/home.component';
import { ListCategoriesComponent } from './categories/list-categories/list-categories.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { InventoryReceivingVoucherComponent } from './inventory-receiving-voucher/inventory-receiving-voucher.component';
import { AddInventoryReceivingVoucherDetailComponent } from './import-voucher/add-inventory-receiving-voucher-detail/add-inventory-receiving-voucher-detail.component';
import { ListProviderComponent } from './provider/list-provider/list-provider.component';
import { ProductComponent } from './product/list_product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ChartsModule } from 'ng2-charts';
import { PieChartComponent} from './pie-chart/pie-chart.component';
import { LineChartComponent } from './line-chart/line-chart.component';
import { CalendarComponent } from './calendar/calendar.component';
import { DataTablesModule } from 'angular-datatables';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ImportComponent } from './product/import/import-product.component';
import { ListComponent } from './import-voucher/list/list.component';
import { OrderListComponent } from './order/order-list/order-list.component';
import { OrderDetailComponent } from './order/order-detail/order-detail.component';


@NgModule({
  declarations: [
    AdminComponent,
    ListCategoriesComponent,
    HomeComponent,
    InventoryReceivingVoucherComponent,
    AddInventoryReceivingVoucherDetailComponent,
    ProductComponent,
    ListProviderComponent,
    ProductDetailComponent,
    PieChartComponent,
    LineChartComponent,
    LineChartComponent,
    CalendarComponent,
    ImportComponent,
    ListComponent,
    OrderListComponent,
    OrderDetailComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule, 
    Ng2SearchPipeModule,
    ChartsModule,
    DataTablesModule,
    NgxDatatableModule,
  ],
  providers: [],
  bootstrap: [AdminComponent]
})
export class AdminModule { }
