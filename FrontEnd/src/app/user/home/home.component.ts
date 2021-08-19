
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import { Response } from 'src/app/model/response';
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

  token!: string;

  constructor(
    private router: Router,
    private userService: UserService,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    // this.getPage();
    this.getTop4NewProduct();
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
            })
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
