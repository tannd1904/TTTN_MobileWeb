import {Component, Input, OnInit} from '@angular/core';
import {Checkout} from 'src/app/checkout';
import {Response} from 'src/app/model/response';
import {User} from 'src/app/model/user';
import {EmployeeService} from 'src/app/service/employee.service';
import {StatisticService} from 'src/app/service/statistic.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  @Input('class')
  klass!: string;

  @Input()
  ngClass!: string | string[] | Set<string> | { [klass: string]: any; };

  revenueEarning!: any;
  numberOfOrders!: any;
  numberOfProducts!: any;
  numberOfUsers!: any;

  token!: any;
  users: User[] = [];
  products: Array<Checkout> = [];
  statistics: number[] = [];

  saleSelect: boolean = true;
  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

  dataInventoryThisYear = [
    [0, 0, 0, 0, 0, 0, 0, 15, 3, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0]
  ];
  dataLabelInventoryThisYear = ['Import', 'Export'];
  labelInventoryThisYear = this.monthNames;

  // dataUserThisYear = [
  //   [0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0,],
  //   [0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0,]
  // ]

  dataLabelUser: string[] = ['Users', 'Employees'];
  date = new Date();
  thisMonthName = [this.labelInventoryThisYear[this.date.getMonth()]];

  dataUserThisMonth: any[] = [];
  dataUserThisYear: any[] = [...[]];

  typeLineChartUserRegis = 'User Registration';
  statisticOrderByMonth = 'month';
  statisticOrderByYear = 'year';
  statisticInventory = 'yearInventory';

  constructor(private userService: UserService,
              private employeeService: EmployeeService,
              private statisticService: StatisticService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.token = this.tokenStorageService.getToken();
    this.setDataUserThisMonth();
    this.setDataUserThisYear();
    this.getStatistic();
  }

  getStatistic() {
    this.statisticService.calculateRevenueAllTime(this.token)
      .subscribe((data: Response) => {
        this.revenueEarning = data.data;
      });
    this.statisticService.getNumberOfOrders(this.token)
      .subscribe((data: Response) => {
        this.numberOfOrders = data.data;
      });
    this.statisticService.getNumberOfProducts(this.token)
      .subscribe((data: Response) => {
        this.numberOfProducts = data.data;
      });
    this.statisticService.getNumberOfUsers(this.token)
      .subscribe((data: Response) => {
        this.numberOfUsers = data.data;
      });
  }

  setDataUserThisMonth() {
    var temp = new Array();
    this.userService.countUserRegisThisMonth(this.token)
      .subscribe((data: Response) => {
        temp.push(data.data);
      });
    var temp2 = new Array();
    this.employeeService.countEmployeeAddedThisMonth(this.token)
      .subscribe((data: Response) => {
        temp2.push(data.data);
      });
    this.dataUserThisMonth = [temp, temp2];
    console.log(this.dataUserThisMonth);
  }

  setDataUserThisYear() {
    var arr: Object[] = [];
    this.userService.countUserRegisThisYear(this.token)
      .subscribe((data: Response) => {
        const object = {data: data.data, label: 'User'};
        arr.push(object);
      });
    this.employeeService.countEmployeeAddedThisYear(this.token)
      .subscribe((data: Response) => {
        const object = {data: data.data, label: 'Employee'};
        arr.push(object);
      });
    console.log(arr);
    console.log(arr[0]);
    this.dataUserThisYear = [arr[0], arr[1]];
    console.log(this.dataUserThisYear);
  }

}
