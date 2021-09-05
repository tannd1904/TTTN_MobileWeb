import { Component, Input, OnInit } from '@angular/core';
import { Checkout } from 'src/app/checkout';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { EmployeeService } from 'src/app/service/employee.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  @Input('class')
  klass!: string

  @Input()
  ngClass!: string | string[] | Set<string> | { [klass: string]: any; }

  token!: any;
  users: User[] = [];
  products: Array<Checkout> = [];
  statistics: number[] = [];

  saleSelect : boolean = true;
  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

  dataInventoryThisYear = [
    [0, 0, 0, 0, 0, 0, 0, 15, 3, 0, 0, 0] ,
    [0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0] 
  ]
  dataLabelInventoryThisYear = ['Import', 'Export'];
  labelInventoryThisYear = this.monthNames;
  
  // dataUserThisYear = [
  //   [0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0,],
  //   [0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0,]
  // ]
  
  dataLabelUser: string[] = ['Users', 'Employees'];
  date = new Date();
  thisMonthName = [this.labelInventoryThisYear[this.date.getMonth()]]

  dataUserThisMonth: any[] = [];
  dataUserThisYear: any[] = [...[]];

  constructor(private userService: UserService,
    private employeeService: EmployeeService, 
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.token = this.tokenStorageService.getToken();
    this.setDataUserThisMonth();
    this.setDataUserThisYear();
  }

  setDataUserThisMonth() {
    var temp = new Array();
    this.userService.countUserRegisThisMonth(this.token)
      .subscribe((data: Response) => {
        temp.push(data.data)
      })
    var temp2 = new Array();
    this.employeeService.countEmployeeAddedThisMonth(this.token)
    .subscribe((data: Response) => {
      temp2.push(data.data)
    })
    this.dataUserThisMonth = [temp, temp2];
    console.log(this.dataUserThisMonth)
  }

  async setDataUserThisYear() {
    var user = new Array();
    var employee = new Array();
    
    Object.setPrototypeOf(this.dataUserThisYear, Array(2))
    this.userService.countUserRegisThisYear(this.token)
      .subscribe((data: Response) => {
        Object.setPrototypeOf(data.data, Array(12));
        user = data.data;
        console.log(user)
        this.dataUserThisYear.push(user);
      })
    
    this.employeeService.countEmployeeAddedThisYear(this.token)
    .subscribe((data: Response) => {
        Object.setPrototypeOf(data.data, Array(12));
        employee = data.data;
        console.log(employee[0])
        this.dataUserThisYear.push(employee);
    })
    console.log(this.dataUserThisYear)
    console.log(this.dataUserThisYear[0])
  }

}