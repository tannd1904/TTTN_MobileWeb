import { AuthService } from './../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { TokenStorageService } from '../service/token-storage.service';
import { CountService } from '../service/count.service';
import { Router } from '@angular/router';
import { Cart } from '../cart';
import { ProductService } from '../service/product.service';
import { WishList } from '../model/wish-list';
import { Response } from '../model/response';
import { Product } from '../model/product';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username!: string;
  userId!: number;

  token = '';
  filter!: any;

  //count!: number;
  count!: number;
  nameUser: string = "";
  cart = new Array<Cart>();
  subTotal = 0;
  wishList = new Array<WishList>();
  products: Array<Product> = [];

  constructor(private router: Router, private tokenStorageService: TokenStorageService, 
              private cartService: CartService, private countService: CountService,
              private authService: AuthService, private productService: ProductService) {
   }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.userId = user.id;
    console.log(user);
    if(user.token != null)
    {
      this.nameUser = user.lastName + " " + user.firstName;
      this.getWishListByUserId(this.userId);
    }
    this.cart = this.cartService.getCart();
    this.cart.forEach(c => {
      this.subTotal += c.price * c.quantity;
    })
    this.getAllProduct();
    
  }

  getAllProduct() {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
      })
  }

  searchProductByName(name: string) {
    var object = {searchKey: this.filter};
    sessionStorage.setItem('SEARCH', JSON.stringify(object));
    if (this.router.url === '/search') {
      this.router.navigate(['../search']).then(this.reloadPage);  
    } else {
      this.router.navigate(['../search']);
    }
  }

  removeProductInCart(id: number) {
    this.cart = this.cart.filter(x => x.product.id != id);
    this.cart.forEach(c => {
      this.subTotal += c.price * c.quantity;
    })
    this.cartService.saveCart(this.cart);
    this.cartService.getCart();
    this.ngOnInit();
  }

  getWishListByUserId(id: number){
    this.token = this.tokenStorageService.getToken();
    this.productService.getWishListByUserId(this.token, id)
        .subscribe(
          (data: Response) => {
            this.wishList = data.data;
            console.log(this.wishList);
          },
          error => {
            console.log(error);
          });
  }

  isLoggedIn():boolean{
    this.token = this.tokenStorageService.getToken();
    if(this.token == '{}')
    {
      return false;
    }else{    
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      this.userId = user.id;
      return true;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['../']);
  }

  directPage(){
    this.router.navigate(['../']).then(this.reloadPage);
  }

  directRegisterPage(){
    this.router.navigate(['../register']).then(this.reloadPage);
  }

  directLoginPage(){
    this.router.navigate(['../login']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }

  directChangeInfo() {
    this.router.navigate(['../changeInfo']);
  }
  
}
