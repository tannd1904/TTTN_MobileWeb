import { Component, DoCheck, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { Response } from 'src/app/model/response';
import { EmployeeService } from 'src/app/service/employee.service';
import { StatisticService } from 'src/app/service/statistic.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent implements OnInit, DoCheck {
  @Input() parentData!: any;
  @Input() parentDataLabel!: any;
  @Input() parentLabel!: any;
  @Input() type!: any;

  token!: any;

  monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

  
  public lineChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      yAxes: [{
        display: true,
        ticks: {
          beginAtZero: true,
          min: 0,
      }
      }]
    }
  };
  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType: ChartType = 'line';
  public lineChartPlugins = [];
  // public lineChartData: ChartDataSets[] = [
  //   {data: [0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0], label: 'Users'},
  //   {data: [0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0], label: 'Employees'},
  // ];
  public lineChartData: ChartDataSets[] = []
  public lineChartLabels: Label[] = [];

  constructor(private userService: UserService,
    private employeeService: EmployeeService, 
    private statisticService: StatisticService,
    private tokenStorageService: TokenStorageService) { }

  ngDoCheck(): void {
    if (this.type == 'User Registration') {
      if (this.lineChartData.length == 3) {
        this.lineChartData.shift();
      }
    }
    // if (this.lineChartData.length == 3) {
    //   this.lineChartData.shift();
    // }
  }

  ngOnInit() {
    this.token = this.tokenStorageService.getToken();
    this.initLineChartForUserRegisThisYear();
  }

  initLineChartForUserRegisThisYear() {
    console.log(this.parentData)
    console.log(this.parentData.length)
    this.lineChartLabels = this.parentLabel;
    
    // for (let i=0; i<this.parentData.length; i++) {
    //   this.lineChartData.push({
    //     data: this.parentData[i].data, 
    //     label: this.parentData[i].label
    //   })
    // }
    console.log(this.lineChartData);
    this.userService.countUserRegisThisYear(this.token)
      .subscribe((data: Response) => {
        this.lineChartData.push({
          data: data.data, 
          label: 'Users',
          borderColor: 'black',
          backgroundColor: 'rgba(255,0,0,0.5)',
        })
      })
    this.employeeService.countEmployeeAddedThisYear(this.token)
      .subscribe((data: Response) => {
        this.lineChartData.push({
          data: data.data, 
          label: 'Employees',
          borderColor: 'black',
          backgroundColor: 'rgba(0,0,255,0.5)',
        })
      })
    this.lineChartData.forEach(s => {
      console.log(s)
    })
    console.log(this.lineChartData);
  }

}
