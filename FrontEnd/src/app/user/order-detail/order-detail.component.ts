import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { OrderDetail } from 'src/app/model/order-detail';
import { Response } from 'src/app/model/response';
import { AuthService } from 'src/app/service/auth.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { OrderService } from 'src/app/service/order.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  orderId!: number;
  token!: string;
  order = new Order();
  listOrderDetail = new Array<OrderDetail>();

  constructor(
    private router: Router,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private productService: ProductService,) { }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['id'];
    this.getOrderById(this.orderId);
    this.getOrderDetailByOrderId(this.orderId);
  }

  getOrderById(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getOrderById(this.token, this.orderId)
      .subscribe((data: Response) => {
        this.order = data.data;
        console.log(this.order)
      }, (err) => {
        console.log(err);
      })
  }

  getOrderDetailByOrderId(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getOrderDetailByOrderId(this.token, this.orderId).subscribe(
      (data: Response) => {
        this.listOrderDetail = data.data;
        this.listOrderDetail.forEach(s => {
          this.productService.getProductDetailById(this.token, s.productDetailId)
            .subscribe((data: Response) => {
              s.productDetail = data.data;
            })
          this.productService.getProductById(this.token, s.productId)
            .subscribe((data: Response) => {
              s.product = data.data;
            })
        })
        console.log(this.listOrderDetail);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
