import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Order } from 'src/app/model/order';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { EmployeeService } from 'src/app/service/employee.service';
import { OrderService } from 'src/app/service/order.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  dataForm!: FormGroup;

  page: number = 6;
  submitted = false;
  user!: any;
  orders: Array<Order> = [];
  listEmployee: Array<User> = [];
  token: any;
  cancelOrderId!: number;

  constructor(
    private pageService: PageService,
    private fb: FormBuilder,
    private orderService: OrderService,
    private tokenStorageService: TokenStorageService,
    private employeeService: EmployeeService,
  ) {}

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.user = this.tokenStorageService.getUser();
    this.getAllOrder();
    this.getAllEmployee();
    this.infoForm();
  }

  infoForm() {
    this.dataForm = this.fb.group({
      shippingEmployee: ['', Validators.required]
    })
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.dataForm.value);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
  }

  get f() { return this.dataForm.controls; }

  getAllEmployee() {
    this.token = this.tokenStorageService.getToken();
    this.employeeService.getAllEmpl(this.token).subscribe(
      (data: Response) => {
        this.listEmployee = data.data;
        console.log(this.orders)
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAllOrder() {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getAllOrders(this.token).subscribe(
      (data: Response) => {
        this.orders = data.data;
        console.log(this.orders)
        this.dtTrigger.next();
      },
      (error) => {
        console.log(error);
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
    )
  }

  clickCancel(id: number) {
    this.cancelOrderId = id;
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
    )
  }
}
