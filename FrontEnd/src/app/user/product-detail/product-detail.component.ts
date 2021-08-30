import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cart } from 'src/app/cart';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { WishList } from 'src/app/model/wish-list';
import { AuthService } from 'src/app/service/auth.service';
import { CartService } from 'src/app/service/cart.service';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { OrderService } from 'src/app/service/order.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
})
export class ProductDetailComponent implements OnInit {
  classBody: string = 'product-detail';
  page: number = 3;

  userId!: number;

  productId!: number;
  selectedProductDetail = new ProductDetail();
  productDetailString: string[] = [];
  listProductDetail: ProductDetail[] = [];
  product!: Product;
  products: Array<Product> = [];
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
    private categoryService: CategoryService,
    private orderService: OrderService,
    private userService: UserService,
    private cartService: CartService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.getProductById(this.productId);
    this.getProductDetail(this.productId);
    this.classBodyService.changeClass(this.classBody);
    this.cart = this.cartService.getCart();
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

  getProductById(id: number){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductById(this.token, id)
        .subscribe(
          (data: Response) => {
            this.product = data.data;
            console.log(this.product);
            this.categoryService.getCategoryById(this.token, this.product.categoryId)
              .subscribe((data: Response) => {
                this.product.categoryName = data.data.name;
              })
            this.productService.getReviewsByProductId(this.token, this.product.id)
              .subscribe((data: Response) => {
                this.product.reviews = data.data;
                this.product.reviews.forEach(s => {
                  this.userService.getUserById(this.token, s.userId)
                    .subscribe((d: Response) => {
                      s.user = d.data;
                      this.listUserReview.push(d.data);
                    })
                })
              })
          },
          error => {
            console.log(error);
          });
  }
 
  getProductDetail(id: number){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductDetailByProductId(this.token, id)
        .subscribe(
          (data: Response) => {
            if (data.data.length == 0) {
              this.productService.getAllProductDetailByProductId(this.token, id)
                .subscribe((data: Response) => {
                  this.listProductDetail = data.data;
                  this.listProductDetail.forEach((s) => {
                    this.productDetailString.push(s.note);
                  })
                  this.selectedProductDetail = this.listProductDetail[0];
                  console.log(this.listProductDetail);
                  console.log(this.productDetailString);
                })
            } else {
              this.listProductDetail = data.data;
              this.listProductDetail.forEach((s) => {
                this.productDetailString.push(s.note);
              })
              this.selectedProductDetail = this.listProductDetail[0];
              console.log(this.listProductDetail);
              console.log(this.productDetailString);
            }
          },
          error => {
            console.log(error);
          });
  }

  changeProductDetail(index: number) {
    console.log(this.productDetailString[index]);
    this.selectedProductDetail = this.listProductDetail[index];
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

  addToWishList() {
    if (!this.isLoggedIn()) {
      this.router.navigate(['../login']).then(this.reloadPage);
    } else {
      var wishList = new WishList();
      wishList.userId = this.userId;
      wishList.productId = this.productId;
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

  addToCart() {
    console.log(this.cart);
    var temp = new Cart();
    this.product.productDetails = new Array<ProductDetail>();
    this.product.productDetails.push(this.selectedProductDetail);
    temp.quantity = 1;
    var valueToRemove = 0;
    this.cart.forEach((c) => {
      if (c.product.id === this.product.id) {
        temp.quantity = c.quantity+1;
        valueToRemove = c.product.id;
      }
    })
    var copy = this.cart;
    this.cart = [];
    this.cart = copy.filter(x => x.product.id !== valueToRemove)
    temp.product = this.product;
    temp.price = this.product.price;
    temp.total = this.product.price * temp.quantity;
    this.cart.push(temp);
    this.cartService.saveCart(this.cart);
    window.location.reload();
  }

  reloadPage(): void {
    window.location.reload();
  }
  
}
