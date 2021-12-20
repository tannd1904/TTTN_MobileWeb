import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Cart} from 'src/app/cart';
import {Order} from 'src/app/model/order';
import {OrderDetail} from 'src/app/model/order-detail';
import {Response} from 'src/app/model/response';
import {AuthService} from 'src/app/service/auth.service';
import {CartService} from 'src/app/service/cart.service';
import {CountService} from 'src/app/service/count.service';
import {OrderService} from 'src/app/service/order.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  token!: string;
  dataForm!: FormGroup;
  user!: any;

  cart = new Array<Cart>();
  subTotal = 0;

  orderId!: number;
  order: Order = new Order();

  submitted = false;

  constructor(private router: Router, private tokenStorageService: TokenStorageService,
              private cartService: CartService, private countService: CountService,
              private authService: AuthService, private orderService: OrderService,
              private fb: FormBuilder,) {
  }

  get f() {
    return this.dataForm.controls;
  }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
    this.cart.forEach(c => {
      this.subTotal += c.price * c.quantity;
    });
    this.user = this.tokenStorageService.getUser();
    console.log(this.user);
    this.infoForm();

  }

  infoForm() {
    this.dataForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      address: ['', [Validators.required]],
      email: ['', [Validators.required, , Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@'
        + '[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
      phone: ['', [Validators.required, Validators.pattern('[0]{1}[0-9]{9}')]],
      note: [''],
    });

    this.dataForm.patchValue({
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      address: this.user.address,
      email: this.user.email,
      phone: this.user.phone,
    });

    this.token = this.tokenStorageService.getToken();
    var orderDetail = new OrderDetail();
    orderDetail.orderId = 1;
    orderDetail.productId = 18;
    orderDetail.productDetailId = 18;
    this.orderService.createOrderDetail(this.token, orderDetail);
  }

  onSubmit(): void {
    this.submitted = true;
    console.log(this.dataForm.value);
    if (this.dataForm.invalid) {
      console.log('aaa');
      return;
    }
    // this.addProduct();
    console.log(this.f.value);
    var ordeTemp = new Order();
    ordeTemp.firstNameOfReceiver = this.f.firstName.value;
    ordeTemp.lastNameOfReceiver = this.f.lastName.value;
    ordeTemp.emailOfReceiver = this.f.email.value;
    ordeTemp.phoneOfReceiver = this.f.phone.value;
    ordeTemp.addressOfReceiver = this.f.address.value;
    ordeTemp.note = this.f.note.value;
    ordeTemp.userId = this.user.id;
    ordeTemp.dateOfOrder = new Date();
    ordeTemp.status = 0;
    ordeTemp.total = this.subTotal * 1.1;
    console.log(ordeTemp);
    console.log(this.cart);
    this.createOrder(ordeTemp);
  }

  createOrder(order: Order) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.createOrder(this.token, order)
      .subscribe(
        (data: Response) => {
          if (data.status !== 200) {
            var message = 'Create Order unsuccessfully';
            console.log(message);
          } else {
            var message = 'Create Order successfully';
            this.order = data.data;
            this.orderId = data.data.id;
            var listOrderDetail = new Array<OrderDetail>();
            this.cart.forEach(c => {
              for (let i = 0; i < c.quantity; i++) {
                console.log('begin saving order detail...');
                var orderDetail = new OrderDetail();
                orderDetail.orderId = this.orderId;
                orderDetail.productDetailId = c.product.productDetails[0].serial;
                orderDetail.productId = c.product.id;
                listOrderDetail.push(orderDetail);
                // console.log(orderDetail);
                // this.token = this.tokenStorageService.getToken();
                // this.orderService.createOrderDetail(this.token, orderDetail)
                //     .subscribe((data: Response) => {
                //       console.log(data)
                //       if (data.status !== 200) {
                //         var mess = "Create Order Detail Unsuccessfully"
                //         console.log(mess)
                //       } else {
                //         var mess = "Create Order Detail Successfully"
                //         console.log(mess);
                //       }
                //     })
              }
            });

            console.log(message);
            console.log(listOrderDetail);
            this.orderService.createListOrderDetail(this.token, listOrderDetail)
              .subscribe((data: Response) => {
                console.log(data);
                if (data.status !== 200) {
                  window.alert('Somethings went wrong! Your order will be remove!');
                  var mess = 'Create List Order Detail Unsuccessfully';
                  console.log(mess);
                  console.log(data);
                } else {
                  var mess = 'Create Order Detail Successfully';
                  console.log(mess);
                  console.log(data);
                  sessionStorage.removeItem('CART');
                  this.router.navigate(['../list-orders']);
                }
              }, (err) => {
                window.alert('Somethings went wrong! Your order will be remove!');
                console.log(err);
              });

            // TODO: delete cart, show notification, navigate to order list page

          }
        }, (err) => {
          window.alert('Somethings went wrong! Your order will be remove!');
          console.log(err);
        }
      );
  }

}
