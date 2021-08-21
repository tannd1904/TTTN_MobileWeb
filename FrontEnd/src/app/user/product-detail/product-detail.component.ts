import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
})
export class ProductDetailComponent implements OnInit {
  classBody: string = 'product-detail';
  page: number = 3;

  productId!: number;
  selectedProductDetail = new ProductDetail();
  productDetailString: string[] = [];
  listProductDetail: ProductDetail[] = [];
  product!: Product;
  products: Array<Product> = [];
  token!: string;

  dataForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.getProductById(this.productId);
    this.getProductDetail(this.productId);
    this.classBodyService.changeClass(this.classBody);
    
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
            this.listProductDetail.forEach((s) => {
              this.productDetailString.push(s.note);
            })
            this.selectedProductDetail = this.listProductDetail[0];
            console.log(this.listProductDetail);
            console.log(this.productDetailString);
          },
          error => {
            console.log(error);
          });
  }

  changeProductDetail(index: number) {
    console.log(this.productDetailString[index]);
    this.selectedProductDetail = this.listProductDetail[index];
  }
  
}
