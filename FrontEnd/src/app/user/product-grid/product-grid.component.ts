import { newArray } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { SimpleOuterSubscriber } from 'rxjs/internal/innerSubscribe';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
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

  categories: Array<Category> = [];
  products: Array<Product> = [];
  listProductDetail: ProductDetail[] = [];
  token!: string;
  config!: any;
  
  colors = [
    {
      name: "Black",
      checked: false
    }
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
    var displayProductList: Product[] = [];
    var productData = new Array<ProductDetail>();

    this.products.forEach(product => {
      for (var i = 0; i < this.selectedRam.length; i++) {
        var lst = product.productDetails.filter(x => x.ram == this.selectedRam[i].name);
        if (lst.length != 0) {
          displayProductList.push(product);
        } 
      }  
      if (this.selectedRam.length > 0) {
        if (this.selectedColor.length > 0) {
          var tempProductlst = displayProductList;
          displayProductList = [];
          for (var i = 0; i < this.selectedColor.length; i++) {
            tempProductlst.forEach(temp => {
              var lst = temp.productDetails.filter(x => x.color == this.selectedColor[i].name);
              if (lst.length != 0) {
                displayProductList.push(temp);
              }
            })
          }
        }
      } else {
        for (var i = 0; i < this.selectedColor.length; i++) {
          var lst = product.productDetails.filter(x => x.ram == this.selectedRam[i].name);
          if (lst.length != 0) {
            displayProductList.push(product);
          } 
        }
      }

      if (this.selectedRam.length == 0 && this.selectedColor.length == 0 
          && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
        displayProductList = this.products;
      }
    })

    this.products = displayProductList;

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
 