import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { Order } from 'src/app/model/order';
import { OrderDetail } from 'src/app/model/order-detail';
import { Response } from 'src/app/model/response';
import { AuthService } from 'src/app/service/auth.service';
import { CartService } from 'src/app/service/cart.service';
import { CountService } from 'src/app/service/count.service';
import { OrderService } from 'src/app/service/order.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  token!: string;

  cart = new Array<Cart>();
  subTotal = 0;

  orderId!: number;
  order: Order = new Order();

  constructor(private router: Router, private tokenStorageService: TokenStorageService, 
    private cartService: CartService, private countService: CountService,
    private authService: AuthService, private orderService: OrderService,) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
    this.cart.forEach(c => {
      this.subTotal += c.price * c.quantity;
    })
  }

  createOrder(order: Order) {
    this.orderService.createOrder(this.token, order)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              var message = "Create Order unsuccessfully";
              console.log(message);
            } else {
              var message = "Create Order successfully";
              this.order = data.data;
              this.orderId = data.data.id;
              this.cart.forEach(c => {
                for (let i=0; i<c.quantity; i++) {
                  var orderDetail = new OrderDetail();
                  orderDetail.orderId = this.orderId;
                  orderDetail.productDetailId = c.product.productDetails[0];
                  orderDetail.productId = c.product.id;
                  this.orderService.createOrderDetail(this.token, orderDetail)
                      .subscribe((data: Response) => {
                        if (data.status !== 200) {
                          var mess = "Create Order Detail Unsuccessfully"
                          console.log(mess)
                        } else {
                          var mess = "Create Order Detail Successfully"
                          console.log(mess);
                        }
                      })
                }
              })
              console.log(message);
            }
          }, (err) => {
            console.log(err);
          }
        )
  }

}
