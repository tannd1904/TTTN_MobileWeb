import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { Provider } from 'src/app/model/provider';
import { Response } from 'src/app/model/response';
import { ActiveService } from 'src/app/service/active.service';
import { CategoryService } from 'src/app/service/category.service';
import { ProductService } from 'src/app/service/product.service';
import { ProviderService } from 'src/app/service/provider.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  
  // @ViewChild(DataTableDirective, {static: true})
  // dtElement!: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  active: number = 2;
  token: any;
  providers: Array<Provider> = [];
  categories: Array<Category> = [];
  products: Array<Product> = [];
  dataForm!: FormGroup;
  submitted = false;
  toggleDeleteBtn = true;

  constructor(private router: Router, private fb: FormBuilder, private activeService: ActiveService, private tokenStorageService: TokenStorageService, private providerService: ProviderService, private categoryService: CategoryService,private productService: ProductService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };
    // this.getProvider();
    // this.getCategory();
    // this.getProduct();
    // this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      productName: ['', [Validators.required]],  
      status:  ['', [Validators.required]],
      description: ['', [Validators.required]],  
      providerId:  ['', [Validators.required]],
      categoryId: ['', [Validators.required]], 
    })
  }

  get f() { return this.dataForm.controls; }

  getProvider(){
    this.token = this.tokenStorageService.getToken();
    this.providerService.getProvider(this.token)
      .subscribe(
        (data: Provider[]) => {
          this.providers = data;
          console.log(this.providers);
        },
        error => {
          console.log(error);
        });
  }

  getCategory(){
    this.token = this.tokenStorageService.getToken();
    this.categoryService.getCategory(this.token)
      .subscribe(
        (data) => {
          this.categories = data;
        },
        error => {
          console.log(error);
        });
  }

  onSubmit(): void {
    this.submitted = true;
    console.log(this.dataForm.value);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.addProduct();
  }

  getProduct(){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token)
        .subscribe(
          (data: Response) => {
            this.categories = data.data;
            this.dtTrigger.next();
            console.log(this.categories);
          },
          error => {
            console.log(error);
          });
  }

  addProduct(){
    this.token = this.tokenStorageService.getToken();
    let provider = this.dataForm.value;
    this.productService.createProduct(this.token, provider)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  toProductDetail(productId: string){
    this.router.navigate(['admin/product/' + productId]).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
