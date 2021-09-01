import { Component, Input, OnInit } from '@angular/core';
import { Checkout } from 'src/app/checkout';
import { User } from 'src/app/model/user';
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

  constructor(private userService: UserService,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    // this.getTop5Users();
    // this.getTop10Orders();
    // this.getStatistics();
  }

}
