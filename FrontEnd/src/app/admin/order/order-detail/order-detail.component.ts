import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Order} from 'src/app/model/order';
import {Response} from 'src/app/model/response';
import {OrderDetail} from 'src/app/model/order-detail';
import {AuthService} from 'src/app/service/auth.service';
import {ClassBodyService} from 'src/app/service/class-body.service';
import {OrderService} from 'src/app/service/order.service';
import {PageService} from 'src/app/service/page.service';
import {ProductService} from 'src/app/service/product.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {EmployeeService} from 'src/app/service/employee.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  user!: any;
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
    private employeeService: EmployeeService,
    private productService: ProductService,) {
  }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['id'];
    this.user = this.tokenStorageService.getUser();
    this.getOrderById(this.orderId);
    this.getOrderDetailByOrderId(this.orderId);
  }

  getOrderById(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getOrderById(this.token, this.orderId)
      .subscribe((data: Response) => {
        this.order = data.data;
        if (this.order.employeeId != null) {
          this.employeeService.getEmplById(this.order.employeeId, this.token)
            .subscribe((data: Response) => {
              this.order.employee = data.data;
            });
        }
        console.log(this.order);
      }, (err) => {
        console.log(err);
      });
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
            });
          this.productService.getProductById(this.token, s.productId)
            .subscribe((data: Response) => {
              s.product = data.data;
            });
        });
        console.log(this.listOrderDetail);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  receiveOrder(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.receiveOrder(this.token, id).subscribe(
      (data: Response) => {
        var message = data.message;
        console.log(message);
        window.location.reload();
      }, (err) => {
        console.log(err);
      }
    );
  }

  confirmOrder(id: number) {
    var employeeId = this.user.id;
    console.log(employeeId);
    this.token = this.tokenStorageService.getToken();
    this.orderService.confirmOrder(this.token, id, employeeId).subscribe(
      (data: Response) => {
        var message = data.message;
        console.log(message);
        window.location.reload();
      }, (err) => {
        console.log(err);
      }
    );
  }

  cancelOrder(id: number) {
    var employeeId = this.user.id;
    console.log(employeeId);
    this.token = this.tokenStorageService.getToken();
    this.orderService.cancelOrder(this.token, id, employeeId).subscribe(
      (data: Response) => {
        var message = data.message;
        console.log(message);
        window.location.reload();
      }, (err) => {
        console.log(err);
      }
    );
  }

}
