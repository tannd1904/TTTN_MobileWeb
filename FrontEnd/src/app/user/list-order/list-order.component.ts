import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/model/order';
import { Response } from 'src/app/model/response';
import { OrderService } from 'src/app/service/order.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrls: ['./list-order.component.css'],
})
export class ListOrderComponent implements OnInit {
  page: number = 6;
  user!: any;
  orders: Array<Order> = [];
  listConfirmingOrders: Array<Order> = [];
  listDeliveringOrders: Array<Order> = [];
  listDeliveredOrders: Array<Order> = [];
  listCancelledOrders: Array<Order> = [];
  token: any;

  constructor(
    private pageService: PageService,
    private orderService: OrderService,
    private tokenStorageService: TokenStorageService,
  ) {}

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.user = this.tokenStorageService.getUser();
    this.getAllOrder(this.user.id);
  }

  getAllOrder(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getAllOrdersByUserId(this.token, id).subscribe(
      (data: Response) => {
        this.orders = data.data;
        this.listConfirmingOrders = this.orders.filter(x => x.status == 0);
        this.listDeliveringOrders = this.orders.filter(x => x.status == 1);
        this.listDeliveredOrders = this.orders.filter(x => x.status == 2);
        this.listCancelledOrders = this.orders.filter(x => x.status == 3);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
