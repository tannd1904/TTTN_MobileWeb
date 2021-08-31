import { newArray } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SimpleOuterSubscriber } from 'rxjs/internal/innerSubscribe';
import { Cart } from 'src/app/cart';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { WishList } from 'src/app/model/wish-list';
import { CartService } from 'src/app/service/cart.service';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.css'],
})
export class ProductGridComponent implements OnInit {
  classBody: string = 'product-detail';
  page: number = 4;

  userId!: number;
  cart = new Array<Cart>();
  categories: Array<Category> = [];
  allProducts: Array<Product> = [];
  products: Array<Product> = [];
  listProductDetail: ProductDetail[] = [];
  displayProductList: Product[] = [];
  token!: string;
  config!: any;
  
  colors = [
    {
      name: "Black",
      checked: false
    },
    {
      name: "White",
      checked: false
    },
    {
      name: "Silver",
      checked: false
    },
    {
      name: "Blue",
      checked: false
    },
    {
      name: "Red",
      checked: false
    },
  ]

  memmories = [
    {
      name: "8 GB",
      checked: false
    }
  ]

  rams = [
    {
      name: "2GB",
      checked: false
    },
    {
      name: "3GB",
      checked: false
    },
    {
      name: "4GB",
      checked: false
    },
    {
      name: "6GB",
      checked: false
    },
    {
      name: "8GB",
      checked: false
    },
    {
      name: "12GB",
      checked: false
    },
    {
      name: "16GB",
      checked: false
    },
  ]

  prices = [
    {
      name: "0$ - 500$",
      from: 0,
      to: 500,
      checked: false
    },
    {
      name: "500$ - 800$",
      from: 500,
      to: 800,
      checked: false
    },
    {
      name: "800$ - 1000$",
      from: 800,
      to: 1000,
      checked: false
    },
    {
      name: "1000$ - 1500$",
      from: 1000,
      to: 1500,
      checked: false
    },
    {
      name: "> 1500$",
      from: 1500,
      to: 10000000,
      checked: false
    },

  ]


  constructor(
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private categoryService: CategoryService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.getAllProduct();
    this.getCategory();
    this.config = {
      itemsPerPage: 6,
      currentPage: 1,
      totalItems: this.products.length
    };
    this.cart = this.cartService.getCart();
  }

  clickCompare(product: any) {
    console.log(product);
    sessionStorage.setItem('PRODUCT_COMPARE', JSON.stringify(product)); 
    this.router.navigate(['../compare'])
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
        this.router.navigate(['../product-detail/' + id]);
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

  getAllProduct() {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach((s) => {
          this.categoryService
            .getCategoryById(this.token, s.categoryId)
            .subscribe(
              (data: Response) => {
                s.categoryName = data.data.name;
              },
              (err) => {
                console.log(err);
              }
            );
          s.productDetails = new Array<ProductDetail>();
          this.productService.getProductDetailByProductId(this.token, s.id)
              .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err)}
              )
        });
        this.allProducts = this.products;
        this.products = this.products.sort(function (high, low) {
          if (low.name < high.name) {
            return -1;
          }
          else if (low.name > high.name) {
            return 1;
          }
          else {
            return 0;
          }
        })
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAllProductByPrice(check: boolean) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductByPrice(this.token, check).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach((s) => {
          this.categoryService
            .getCategoryById(this.token, s.categoryId)
            .subscribe(
              (data: Response) => {
                s.categoryName = data.data.name;
              },
              (err) => {
                console.log(err);
              }
            );
          s.productDetails = new Array<ProductDetail>();
          this.productService.getProductDetailByProductId(this.token, s.id)
              .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err)}
              )
        });
        
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getProductByCategoryId(categoryId: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductByCategoryId(this.token, categoryId).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach((s) => {
          this.categoryService
            .getCategoryById(this.token, s.categoryId)
            .subscribe(
              (data: Response) => {
                s.categoryName = data.data.name;
              },
              (err) => {
                console.log(err);
              }
            );
          s.productDetails = new Array<ProductDetail>();
          this.productService.getProductDetailByProductId(this.token, s.id)
              .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err)}
              )
        });
        console.log(this.products[0].importVoucherDetails[0].productDetails);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getCategory(){
    this.token = this.tokenStorageService.getToken();
    this.categoryService.getCategory(this.token)
        .subscribe(
          (data: Response) => {
            this.categories = data.data;
            console.log(this.categories);
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
            this.listProductDetail = data.data;
            // console.log(this.listProductDetail);
          },
          error => {
            console.log(error);
          });
  }

  sort(event: any) {
    console.log('start sort');
    switch (event.target.value) {
      case "Low":
        {
          console.log('sort low to high')
          this.products = this.products.sort((low, high) => low.price - high.price);
          break;
        }

      case "High":
        {
          console.log('sort high to low')
          this.products = this.products.sort((low, high) => high.price - low.price);
          break;
        }

      case "Name":
        {
          console.log('sort a to z')
          this.products = this.products.sort(function (low, high) {
            if (low.name < high.name) {
              return -1;
            }
            else if (low.name > high.name) {
              return 1;
            }
            else {
              return 0;
            }
          })
          break;
        }

      case "NameZ":
        {
          this.products = this.products.sort(function (high, low) {
            if (low.name < high.name) {
              return -1;
            }
            else if (low.name > high.name) {
              return 1;
            }
            else {
              return 0;
            }
          })
          break;
        }

      default: {
        this.products = this.products.sort((low, high) => low.price - high.price);
        break;
      }

    }
    return this.products;

  }


  pageChanged(event: any) {
    this.config.currentPage = event;
  }

  get selectedRam() {
    //Get all the selected brands
    return this.rams.filter(opt => opt.checked)
  }

  get selectedPrice() {
    //Get all the selected brands
    return this.prices.filter(opt => opt.checked)
  }

  get selectedColor() {
    return this.colors.filter(opt => opt.checked)
  }

  get selectedMemory() {
    return this.memmories.filter(opt => opt.checked)
  }

  onChange(event: any) {
    console.log('start filter');
    console.log(this.selectedColor);
    console.log(this.selectedRam);
    console.log(this.selectedMemory);
    console.log(this.selectedPrice);

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0 
        && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    } else {
      if (this.displayProductList.length == this.allProducts.length) {
        this.displayProductList = [];
        for (var i = 0; i < this.selectedPrice.length; i++) {
          var lst = this.allProducts.filter(
            x => 
            ((x.price > this.selectedPrice[i].from) && (x.price < this.selectedPrice[i].to)));
            lst.forEach(s => {
              this.displayProductList.push(s)
            })
          } 
        if (this.displayProductList.length == 0) {
          this.allProducts.forEach(product => {
            for (var i = 0; i < this.selectedRam.length; i++) {
              var lst = product.productDetails.filter(x => x.ram == this.selectedRam[i].name);
              if (lst.length != 0) {
                this.displayProductList.push(product);
              } 
            }
          })
        } else {
          var temp = this.displayProductList;
          this.displayProductList = [];
          temp.forEach(product => {
            for (var i = 0; i < this.selectedRam.length; i++) {
              var lst = product.productDetails.filter(x => x.ram == this.selectedRam[i].name);
              if (lst.length != 0) {
                this.displayProductList.push(product);
              } 
            }
          })
        }
      } 
      // else {

      // }


       

      // this.allProducts.forEach(product => {
        

          
  
      //   if (this.selectedRam.length > 0) {
      //     if (this.selectedColor.length > 0) {
      //       var tempProductlst = this.displayProductList;
      //       this.displayProductList = [];
      //       for (var i = 0; i < this.selectedColor.length; i++) {
      //         tempProductlst.forEach(temp => {
      //           var lst = temp.productDetails.filter(x => x.color == this.selectedColor[i].name);
      //           if (lst.length != 0) {
      //             this.displayProductList.push(temp);
      //           }
      //         })
      //       }
      //     }
      //   } else {
      //     for (var i = 0; i < this.selectedColor.length; i++) {
      //       var lst = product.productDetails.filter(x => x.ram == this.selectedColor[i].name);
      //       if (lst.length != 0) {
      //         this.displayProductList.push(product);
      //       } 
      //     }
      //   }
      // })
    }

    this.products = this.displayProductList;

    // câu lệnh filter giá:
    // filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to)

    
  }

  resetFilter() {
    this.getAllProduct();
    this.rams.forEach(s => {
      s.checked = false;
    })
    this.colors.forEach(s => {
      s.checked = false;
    })
    this.prices.forEach(s => {
      s.checked = false;
    })
    this.memmories.forEach(s => {
      s.checked = false;
    })
  }
}
 