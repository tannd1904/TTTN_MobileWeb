import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { AuthService } from 'src/app/service/auth.service';
import { CartService } from 'src/app/service/cart.service';
import { CountService } from 'src/app/service/count.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cart = new Array<Cart>();
  subTotal = 0;

  constructor(private router: Router, private tokenStorageService: TokenStorageService, 
    private cartService: CartService, private countService: CountService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
    this.cart.forEach(c => {
      this.subTotal += c.price * c.quantity;
    })
  }

}
