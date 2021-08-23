import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { Response } from 'src/app/model/response';
import { WishList } from 'src/app/model/wish-list';
import { AuthService } from 'src/app/service/auth.service';
import { CartService } from 'src/app/service/cart.service';
import { CountService } from 'src/app/service/count.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
  token!: string;
  userId!: number;
  cart = new Array<Cart>();
  wishList: WishList[] = [];

  constructor(private router: Router, private tokenStorageService: TokenStorageService, 
    private cartService: CartService, private countService: CountService,
    private authService: AuthService, private productService: ProductService) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.userId = user.id;
    this.getWishListByUserId(this.userId);
    this.cart = this.cartService.getCart();
  }

  getWishListByUserId(id: number){
    this.token = this.tokenStorageService.getToken();
    this.productService.getWishListByUserId(this.token, id)
        .subscribe(
          (data: Response) => {
            this.wishList = data.data;
            console.log(this.wishList);
            this.wishList.forEach(s => {
              this.productService.getProductById(this.token, s.productId)
                .subscribe((data: Response) => {
                  s.product = data.data;
                })
            })
          },
          error => {
            console.log(error);
          });
  }

  // TODO: add function delete wishlist and ADD TO CART btn
  removeWishList(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.removeWishList(this.token, id)
      .subscribe((data: Response) => {
        if (data.status !== 200) {
          var message = "Delete WishList unsuccessfully";
          console.log(message);
        }
        else {
          var message = "Delete WishList successfully";
          console.log(message);
          this.ngOnInit();
        }
      })
  }
  
  
}
