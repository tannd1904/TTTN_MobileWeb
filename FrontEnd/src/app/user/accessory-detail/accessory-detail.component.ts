import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Cart} from 'src/app/cart';
import {Product} from 'src/app/model/product';
import {ProductDetail} from 'src/app/model/product-detail';
import {Response} from 'src/app/model/response';
import {User} from 'src/app/model/user';
import {WishList} from 'src/app/model/wish-list';
import {AuthService} from 'src/app/service/auth.service';
import {CartService} from 'src/app/service/cart.service';
import {CategoryService} from 'src/app/service/category.service';
import {ClassBodyService} from 'src/app/service/class-body.service';
import {OrderService} from 'src/app/service/order.service';
import {PageService} from 'src/app/service/page.service';
import {ProductService} from 'src/app/service/product.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';
import {PropertyService} from '../../service/property.service';
import {Accessory} from '../../model/accessory';
import {AccessoryService} from '../../service/accessory.service';
import {PromotionService} from '../../service/promotion.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './accessory-detail.component.html',
  styleUrls: ['./accessory-detail.component.css'],
})
export class AccessoryDetailComponent implements OnInit {
  classBody: string = 'accessory-detail';
  page: number = 3;

  userId!: number;

  accessoryId!: number;
  accessory!: Accessory;

  wishList: Array<WishList> = [];
  listUserReview: Array<User> = [];
  token!: string;

  cart = new Array<Cart>();

  dataForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private productService: ProductService,
    private userService: UserService,
    private cartService: CartService,
    private accessoryService: AccessoryService,
    private promotionService: PromotionService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.accessoryId = this.route.snapshot.params['id'];
    this.getAccessoryById(this.accessoryId);
    this.classBodyService.changeClass(this.classBody);
    this.cart = this.cartService.getCart();
  }

  getAccessoryById(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.accessoryService.getAccessoryById(this.token, id)
      .subscribe((data: Response) => {
        this.accessory = data.data;
        console.log(this.accessory);
        this.accessoryService.getAccessoryCateById(this.token, this.accessory.accessoryCateId)
          .subscribe((d: Response) => {
            this.accessory.accessoryCateName = data.data.name;
          });
        this.promotionService.getPromotionByAccessoryId(this.token, this.accessory.id)
          .subscribe((d: Response) => {
            this.accessory.promotions = data.data;
          });
      }, (err) => {
        console.log(err);
      });
  }

  isLoggedIn(): boolean {
    this.token = this.tokenStorageService.getToken();
    if (this.token == '{}') {
      return false;
    } else {
      const user = this.tokenStorageService.getUser();
      this.userId = user.id;
      return true;
    }
  }

  // addToWishList() {
  //   if (!this.isLoggedIn()) {
  //     this.router.navigate(['../login']).then(this.reloadPage);
  //   } else {
  //     var wishList = new WishList();
  //     wishList.userId = this.userId;
  //     wishList.productId = this.productId;
  //     this.token = this.tokenStorageService.getToken();
  //     this.productService.addToWishList(this.token, wishList)
  //       .subscribe(
  //         (data: Response) => {
  //           if (data.status !== 200) {
  //             var message = 'Create WishList unsuccessfully';
  //             console.log(message);
  //           } else {
  //             var message = 'Create WishList successfully';
  //             console.log(message);
  //             this.router.navigate(['../wishlist']);
  //           }
  //         }, (err) => {
  //           console.log(err);
  //         }
  //       );
  //   }
  // }

  // addToCart() {
  //   console.log(this.cart);
  //   var temp = new Cart();
  //   this.product.productDetails = new Array<ProductDetail>();
  //   this.product.productDetails.push(this.selectedProductDetail);
  //   temp.quantity = 1;
  //   var valueToRemove = 0;
  //   this.cart.forEach((c) => {
  //     if (c.product.id === this.product.id) {
  //       temp.quantity = c.quantity + 1;
  //       valueToRemove = c.product.id;
  //     }
  //   });
  //   var copy = this.cart;
  //   this.cart = [];
  //   this.cart = copy.filter(x => x.product.id !== valueToRemove);
  //   temp.product = this.product;
  //   temp.price = this.product.price;
  //   temp.total = this.product.price * temp.quantity;
  //   this.cart.push(temp);
  //   this.cartService.saveCart(this.cart);
  //   window.location.reload();
  // }

  reloadPage(): void {
    window.location.reload();
  }

}
