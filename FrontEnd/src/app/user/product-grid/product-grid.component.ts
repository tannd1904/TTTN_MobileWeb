import { newArray } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
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
  color = [];


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
        });
        console.log(this.products[0].importVoucherDetails);
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
}
