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
  orders: Array<Order> = [];
  token: any;

  constructor(
    private pageService: PageService,
    private orderService: OrderService,
    private tokenStorageService: TokenStorageService,
  ) {}

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.getAllOrder();
  }

  getAllOrder() {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getAllOrders(this.token).subscribe(
      (data: Response) => {
        this.orders = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
