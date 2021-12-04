import {Category} from './../model/category';
import {TokenStorageService} from './../service/token-storage.service';
import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  @Input('class')
  klass!: string;

  @Input()
  ngClass!: string | string[] | Set<string> | { [klass: string]: any; };
  clickCategory = false;
  toggleSideBar = false;
  isActive = [true, false, false, false, false, false, false, false];

  categories: Array<Category> = [];

  categoryName: string = '';

  constructor(private tokenStorageService: TokenStorageService,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {

  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['../']);
  }

  directChangeInfo() {
    this.router.navigate(['../changeInfo']);
  }

}
