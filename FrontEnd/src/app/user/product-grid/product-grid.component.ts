import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
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

  products: Array<Product> = [];
  token!: string;
  config!: any;

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
      },
      (error) => {
        console.log(error);
      }
    );
  }

  pageChanged(event: any) {
    this.config.currentPage = event;
  }
}
