import {Component, DoCheck, Input, OnInit} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {Label} from 'ng2-charts';
import {Response} from 'src/app/model/response';
import {StatisticService} from 'src/app/service/statistic.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit, DoCheck {
  @Input() parentData!: any;
  @Input() parentDataLabel!: any;
  @Input() parentLabel!: any;
  @Input() statisticOf!: any;

  data: ChartDataSets[] = [];
  token!: any;

  public barChartOptions: ChartOptions = {
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

  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];
  public barChartLabels: Label[] = [];
  public barChartData: ChartDataSets[] = [];


  constructor(private statisticService: StatisticService,
              private tokenStorageService: TokenStorageService,) {
  }

  ngDoCheck(): void {

    if (this.barChartData.length == 3) {
      this.barChartData.shift();
    }

    // if (this.lineChartData.length == 3) {
    //   this.lineChartData.shift();
    // }
  }

  ngOnInit() {
    this.token = this.tokenStorageService.getToken();

    // console.log(this.parentData)
    // console.log(this.parentDataLabel)
    // console.log(this.parentLabel)
    if (this.statisticOf == 'yearInventory') {
      this.initBarChartForInventoryThisYear();
    } else {
      this.initBarChartForUserRegisThisMonth();
      console.log(this.barChartData);
    }
  }

  initBarChartForUserRegisThisMonth() {
    this.barChartLabels = this.parentLabel;
    for (let i = 0; i < this.parentData.length; i++) {
      this.barChartData.push({
        data: this.parentData[i],
        label: this.parentDataLabel[i]
      });
    }
    console.log(this.barChartData);
  }

  initBarChartForInventoryThisYear() {
    this.barChartLabels = this.parentLabel;
    this.barChartData = [];
    this.statisticService.countInventoryThisYear(this.token)
      .subscribe((data: Response) => {
        this.barChartData.push(
          {
            data: data.data.listExport,
            label: 'Export',
            backgroundColor: 'rgba(255,99,132,0.6)',
            borderColor: 'rgba(255,99,132,1)',
            hoverBackgroundColor: 'rgba(255,99,132,0.8)',
            hoverBorderColor: 'rgba(255,99,132,1)'

          },
          {
            data: data.data.listImport,
            label: 'Import',
            backgroundColor: 'rgba(54,162,235,0.6)',
            borderColor: 'rgba(54,162,235,1)',
            hoverBackgroundColor: 'rgba(54,162,235,0.8)',
            hoverBorderColor: 'rgba(54,162,235,1)'
          },
        );
      });
  }

}
