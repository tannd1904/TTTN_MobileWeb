import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {
  @Input() parentData!: any;
  @Input() parentDataLabel!: any;
  @Input() parentLabel!: any;

  data: ChartDataSets[] = [];

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


  constructor() { }

  ngOnInit() {
    // console.log(this.parentData)
    // console.log(this.parentDataLabel)
    // console.log(this.parentLabel)
    this.barChartLabels = this.parentLabel;
    for (let i=0; i<this.parentData.length; i++) {
      this.barChartData.push({
        data: this.parentData[i], 
        label: this.parentDataLabel[i]
      })
    }
    console.log(this.barChartData);
  }

}