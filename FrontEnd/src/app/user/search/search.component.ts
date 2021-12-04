import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Cart} from 'src/app/cart';
import {Product} from 'src/app/model/product';
import {Response} from 'src/app/model/response';
import {WishList} from 'src/app/model/wish-list';
import {CartService} from 'src/app/service/cart.service';
import {CategoryService} from 'src/app/service/category.service';
import {ClassBodyService} from 'src/app/service/class-body.service';
import {PageService} from 'src/app/service/page.service';
import {ProductService} from 'src/app/service/product.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {

  searchResult: any;
  products: Product[] = [];
  token!: string;
  userId!: number;
  cart = new Array<Cart>();

  constructor(private router: Router,
              private userService: UserService,
              private classBodyService: ClassBodyService,
              private pageService: PageService,
              private tokenStorageService: TokenStorageService,
              private productService: ProductService,
              private categoryService: CategoryService,
              private cartService: CartService,) {
  }

  ngOnDestroy(): void {
    sessionStorage.removeItem('SEARCH');
  }

  ngOnInit(): void {
    this.searchResult = JSON.parse(sessionStorage.getItem('SEARCH') || '{}');
    this.searchProductByName(this.searchResult.searchKey);
    console.log(this.router.url);
  }

  searchProductByName(name: string) {
    this.token = this.tokenStorageService.getToken();
    this.productService.searchProductByName(this.token, name).subscribe(
      (data: Response) => {
        this.products = data.data;
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

  addToWishList(productId: number) {
    if (!this.isLoggedIn()) {
      this.router.navigate(['../login']).then(window.location.reload);
    } else {
      var wishList = new WishList();
      wishList.userId = this.userId;
      wishList.productId = productId;
      this.token = this.tokenStorageService.getToken();
      this.productService.addToWishList(this.token, wishList)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              var message = 'Create WishList unsuccessfully';
              console.log(message);
            } else {
              var message = 'Create WishList successfully';
              console.log(message);
              this.router.navigate(['../wishlist']);
            }
          }, (err) => {
            console.log(err);
          }
        );
    }
  }

  addToCart(id: number) {
    var pro = new Array<Product>();
    pro = this.products.filter(x => x.id == id);
    pro.forEach(p => {
      if (!(p.productDetails.length == 0 || p.productDetails.length == 1)) {
        this.router.navigate(['./product-detail/' + id]);
      } else {
        var temp = new Cart();
        temp.quantity = 1;
        var valueToRemove = 0;
        this.cart.forEach((c) => {
          if (c.product.id === p.id) {
            temp.quantity = c.quantity + 1;
            valueToRemove = c.product.id;
          }
        });
        var copy = this.cart;
        this.cart = [];
        this.cart = copy.filter(x => x.product.id !== valueToRemove);
        temp.product = p;
        temp.price = p.price;
        temp.total = p.price * temp.quantity;
        this.cart.push(temp);
        this.cartService.saveCart(this.cart);
        window.location.reload();
      }
    });
  }

  sort(event: any) {
    console.log('start sort');
    switch (event.target.value) {
      case 'Low': {
        console.log('sort low to high');
        this.products = this.products.sort((low, high) => low.price - high.price);
        break;
      }

      case 'High': {
        console.log('sort high to low');
        this.products = this.products.sort((low, high) => high.price - low.price);
        break;
      }

      case 'Name': {
        console.log('sort a to z');
        this.products = this.products.sort(function(low, high) {
          if (low.name < high.name) {
            return -1;
          } else if (low.name > high.name) {
            return 1;
          } else {
            return 0;
          }
        });
        break;
      }

      case 'NameZ': {
        this.products = this.products.sort(function(high, low) {
          if (low.name < high.name) {
            return -1;
          } else if (low.name > high.name) {
            return 1;
          } else {
            return 0;
          }
        });
        break;
      }

      default: {
        this.products = this.products.sort((low, high) => low.price - high.price);
        break;
      }

    }
    return this.products;

  }

}
