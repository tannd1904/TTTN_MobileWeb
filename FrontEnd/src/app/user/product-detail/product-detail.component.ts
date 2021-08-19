import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
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

  products: Array<Product> = [];
  token!: string;

  constructor(
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
  }

  
}
