import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
})
export class ProductDetailComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  productId!: number;
  product = new Product();
  token: any;
  imgURL: any;
  imageFile: any;
  public message!: string;
  public imagePath: any;
  dataForm!: FormGroup;
  submitted = false;
  productDetails: Array<ProductDetail> = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5,10,20,50,100],
      processing: true,
    };
    this.getProduct(this.productId);
    this.getProductDetail(this.productId);
  }

  getProduct(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductById(this.token, id)
      .subscribe((data: Response) => {
        this.product = data.data;
      }, (err) => {
        console.log(err);
      })
  }

  getProductDetail(id: number){
    this.token = this.tokenStorageService.getToken();
    this.productService.getAllProductDetailByProductId(this.token, id)
        .subscribe(
          (data: Response) => {
            this.productDetails = data.data;
            this.dtTrigger.next();
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
