import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveService } from 'src/app/service/active.service';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {
  active!: number;

  constructor(private activeService: ActiveService, private router: Router) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
   }

  ngOnInit(): void {
    this.activeService.currentActive.subscribe(active => this.active = active);
  }

  toInventory(){
    this.router.navigate(['admin/inventory']).then(this.reloadPage);
  }

  toProduct(){
    this.router.navigate(['admin/product']).then(this.reloadPage);
  }

  toProvider(){
    this.router.navigate(['admin/provider']).then(this.reloadPage);
  }

  toCategory(){
    this.router.navigate(['admin/category']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }

  // Test Chart

  public pieChartOptions: ChartOptions = {
    responsive: true,
  };
  public pieChartLabels: Label[] = ['Cancelled', 'Delivered', 'Delivering', 'Unconfirmed'];
  public pieChartData: SingleDataSet = [1, 2, 2, 1];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

}
