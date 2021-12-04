import {Component, DoCheck, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ActiveService} from 'src/app/service/active.service';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {Color, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip} from 'ng2-charts';
import {StatisticService} from 'src/app/service/statistic.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {Response} from 'src/app/model/response';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit, DoCheck {
  @Input() statisticBy!: any;

  token!: any;
  total!: number;
  public pieChartOptions: ChartOptions = {
    responsive: true,
  };
  public pieChartLabels: Label[] = ['Unconfirmed', 'Delivering', 'Delivered', 'Cancelled'];
  public pieChartColors: Color[] = [
    {backgroundColor: 'red', hoverBackgroundColor: 'rgba(255,0,0,0.3)'},
    {backgroundColor: 'blue', hoverBackgroundColor: 'rgba(0,0,255,0.3)'},
    {backgroundColor: 'yellow', hoverBackgroundColor: 'rgba(255,255,0,0.3)'},
    {backgroundColor: 'rgba(0,255,255,1)', hoverBackgroundColor: 'rgba(0,255,255,0.3)'},
  ];
  // public pieChartData: SingleDataSet = [1,2,4,5];
  public pieChartData: ChartDataSets[] = [];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

  constructor(private activeService: ActiveService, private router: Router,
              private statisticService: StatisticService,
              private tokenStorageService: TokenStorageService,) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }

  ngDoCheck(): void {
    if (this.pieChartData.length == 2) {
      this.pieChartData.shift();
    }
  }

  ngOnInit(): void {
    this.token = this.tokenStorageService.getToken();
    console.log(this.statisticBy);
    console.log(this.pieChartData);
    if (this.statisticBy === 'year') {
      this.initPieChartForOrdersThisYear();
    } else {
      this.initPieChartForOrdersThisMonth();
    }
  }

  initPieChartForOrdersThisYear() {
    this.pieChartData = [];
    this.statisticService.countOrdersInThisYear(this.token)
      .subscribe((data: Response) => {
        this.pieChartData.push({
          data: data.data,
          backgroundColor: ['red', 'blue', 'yellow', 'rgba(0,255,255,1)']
        });
      });
    console.log(this.pieChartData);
  }

  initPieChartForOrdersThisMonth() {
    this.pieChartData = [];
    this.statisticService.countOrdersInThisMonth(this.token)
      .subscribe((data: Response) => {
        this.pieChartData.push({
          data: data.data,
          backgroundColor: ['red', 'blue', 'yellow', 'rgba(0,255,255,1)']
        });
      });
    console.log(this.pieChartData);
  }
}
