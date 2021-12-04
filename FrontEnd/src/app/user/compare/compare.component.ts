import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Cart} from 'src/app/cart';
import {Product} from 'src/app/model/product';
import {ProductDetail} from 'src/app/model/product-detail';
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
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styleUrls: ['./compare.component.css']
})
export class CompareComponent implements OnInit {

  classBody: string = 'home';
  products: Array<Product> = [];
  page: number = 0;

  response!: Response;
  userId!: number;
  cart = new Array<Cart>();

  token!: string;
  filter!: any;
  toggle = false;
  keyword = 'name';
  firstProduct = new Product();
  secondProduct = new Product();

  selectedProductDetailFirst = new ProductDetail();
  productDetailStringFirst: string[] = [];
  listProductDetailFirst: ProductDetail[] = [];

  selectedProductDetailSecond = new ProductDetail();
  productDetailStringSecond: string[] = [];
  listProductDetailSecond: ProductDetail[] = [];

  constructor(private router: Router,
              private userService: UserService,
              private classBodyService: ClassBodyService,
              private pageService: PageService,
              private tokenStorageService: TokenStorageService,
              private productService: ProductService,
              private categoryService: CategoryService,
              private cartService: CartService,) {
  }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    this.cart = this.cartService.getCart();
    this.getAllProduct();
    this.firstProduct = JSON.parse(sessionStorage.getItem('PRODUCT_COMPARE') || '{}');
    this.getProductDetail(this.firstProduct.id);
    this.products = this.products.filter(x => x.id != this.firstProduct.id);
  }

  getAllProduct() {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
      });
  }

  selectEvent(item: any) {
    this.secondProduct = item;
    console.log(this.secondProduct);
    this.selectedProductDetailSecond = new ProductDetail();
    this.productDetailStringSecond = [];
    this.listProductDetailSecond = [];
    this.getProductDetailSecond(this.secondProduct.id);
  }

  getProductDetail(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductDetailByProductId(this.token, id)
      .subscribe(
        (data: Response) => {
          if (data.data.length == 0) {
            this.productService.getAllProductDetailByProductId(this.token, id)
              .subscribe((data: Response) => {
                this.listProductDetailFirst = data.data;
                this.listProductDetailFirst.forEach((s) => {
                  this.productDetailStringFirst.push(s.note);
                });
                this.selectedProductDetailFirst = this.listProductDetailFirst[0];
                console.log(this.listProductDetailFirst);
                console.log(this.productDetailStringFirst);
              });
          } else {
            this.listProductDetailFirst = data.data;
            this.listProductDetailFirst.forEach((s) => {
              this.productDetailStringFirst.push(s.note);
            });
            this.selectedProductDetailFirst = this.listProductDetailFirst[0];
            console.log(this.listProductDetailFirst);
            console.log(this.productDetailStringFirst);
          }
        },
        error => {
          console.log(error);
        });
  }

  getProductDetailSecond(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductDetailByProductId(this.token, id)
      .subscribe(
        (data: Response) => {
          if (data.data.length == 0) {
            this.productService.getAllProductDetailByProductId(this.token, id)
              .subscribe((data: Response) => {
                this.listProductDetailSecond = data.data;
                this.listProductDetailSecond.forEach((s) => {
                  this.productDetailStringSecond.push(s.note);
                });
                this.selectedProductDetailSecond = this.listProductDetailSecond[0];
                console.log(this.listProductDetailSecond);
                console.log(this.productDetailStringSecond);
              });
          } else {
            this.listProductDetailSecond = data.data;
            this.listProductDetailSecond.forEach((s) => {
              this.productDetailStringSecond.push(s.note);
            });
            this.selectedProductDetailSecond = this.listProductDetailSecond[0];
            console.log(this.listProductDetailSecond);
            console.log(this.productDetailStringSecond);
          }
        },
        error => {
          console.log(error);
        });
  }

  changeProductDetail(index: number, check: boolean) {
    if (check) {
      console.log(this.productDetailStringFirst[index]);
      this.selectedProductDetailFirst = this.listProductDetailFirst[index];
    } else {
      console.log(this.productDetailStringSecond[index]);
      this.selectedProductDetailSecond = this.listProductDetailSecond[index];
    }

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

  reloadPage(): void {
    window.location.reload();
  }

}
