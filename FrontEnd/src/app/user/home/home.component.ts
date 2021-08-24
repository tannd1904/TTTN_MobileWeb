
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { WishList } from 'src/app/model/wish-list';
import { CartService } from 'src/app/service/cart.service';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  classBody: string = 'home';
  products: Array<Product> = [];
  page: number = 0;
  
  response!: Response;
  userId!: number;
  cart = new Array<Cart>();

  token!: string;

  constructor(
    private router: Router,
    private userService: UserService,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private categoryService: CategoryService,
    private cartService: CartService,
  ) {}

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    // this.getPage();
    this.getTop4NewProduct();
    this.cart = this.cartService.getCart();
  }

  isLoggedIn():boolean{
    this.token = this.tokenStorageService.getToken();
    if(this.token == '{}')
    {
      return false;
    }else{    
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
              var message = "Create WishList unsuccessfully";
              console.log(message);
            } else {
              var message = "Create WishList successfully";
              console.log(message);
              this.router.navigate(['../wishlist']);
            }
          }, (err) => {
            console.log(err);
          }
        )
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
            temp.quantity = c.quantity+1;
            valueToRemove = c.product.id;
          }
        })
        var copy = this.cart;
        this.cart = [];
        this.cart = copy.filter(x => x.product.id !== valueToRemove)
        temp.product = p;
        temp.price = p.price;
        temp.total = p.price * temp.quantity;
        this.cart.push(temp);
        this.cartService.saveCart(this.cart);
        window.location.reload();
      }
    })
  }

  getPage() {
    this.userService.getPromotionProduct().subscribe(
      (data: Product[]) => {
        this.products = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getTop4NewProduct() {
    this.token = this.tokenStorageService.getToken();
    this.productService.getTop4NewProduct(this.token).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach(s => {
          this.categoryService.getCategoryById(this.token, s.categoryId)
            .subscribe((data: Response) => {
              s.categoryName = data.data.name;
            }, (err) => {
              console.log(err);
            });
          s.productDetails = new Array<ProductDetail>();
          this.productService.getProductDetailByProductId(this.token, s.id)
              .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err)}
              )
        })
      },
      (error) => {
        console.log(error);
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  toProductGrid() {
    this.router.navigate(['/product-grid']).then(this.reloadPage);
  }
}
