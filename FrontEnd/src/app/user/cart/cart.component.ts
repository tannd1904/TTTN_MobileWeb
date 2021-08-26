import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { Response } from 'src/app/model/response';
import { AuthService } from 'src/app/service/auth.service';
import { CartService } from 'src/app/service/cart.service';
import { CountService } from 'src/app/service/count.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  token!: string;

  count!: number;
  cart = new Array<Cart>();
  subTotal = 0;

  constructor(private router: Router, private tokenStorageService: TokenStorageService, 
    private cartService: CartService, private countService: CountService,
    private authService: AuthService, private productService: ProductService,) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
    this.calculateTotal();
  }

  countProductDetail(id: number, ram: string, color: string, memmory: string){
    this.token = this.tokenStorageService.getToken();
    this.productService.countSameProductDetail(this.token, id, ram, color, memmory)
        .subscribe(
          (data: Response) => {
            this.count = data.data;
          },
          error => {
            console.log(error);
          });
  }

  calculateTotal() {
    this.subTotal = 0;
    this.cart.forEach(c => {
      this.subTotal += c.total;
    })
  }

  removeProductInCart(id: number) {
    this.cart = this.cart.filter(x => x.product.id != id);
    this.cartService.saveCart(this.cart);
    this.cart = this.cartService.getCart();
    this.calculateTotal();
    this.reloadPage();
  }

  increaseQuantity(id: number) {
    this.cart.forEach(c => {
      if (c.product.id === id) {
        this.countProductDetail(id, c.product.productDetails[0].ram, 
                              c.product.productDetails[0].color, c.product.productDetails[0].memmory);
        console.log(this.count)
        if (c.quantity == this.count) {
          window.alert('Not allowed! Excess Cargo!')
        } else {
          c.quantity++;
          c.total = c.price * c.quantity;
        }
      }
    })
    this.calculateTotal();
    this.cartService.saveCart(this.cart);
  }

  decreaseQuantity(id: number) {
    this.cart.forEach(c => {
      if (c.product.id === id) {
        if (c.quantity == 1) {
          this.removeProductInCart(c.product.id);
        } else {
          c.quantity--;
          c.total = c.price * c.quantity;
        }
      }
    })
    this.calculateTotal();
    this.cartService.saveCart(this.cart);
  }

  isLoggedIn():boolean{
    this.token = this.tokenStorageService.getToken();
    if(this.token == '{}')
    {
      return false;
    }else{    
      const user = this.tokenStorageService.getUser();
      return true;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }

  processToCheckout() {
    if (!this.isLoggedIn()) {
      this.router.navigate(['../login']).then(this.reloadPage);
    } else {
      this.router.navigate(['../checkout']);
    }
  }

}
