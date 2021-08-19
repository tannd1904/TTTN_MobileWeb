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
  categories: Array<Category> = [];
  products: Array<Product> = [];
  dataForm!: FormGroup;
  submitted = false;
  toggleDeleteBtn = true;
  deleteId!: number;
  message!: string;
  imgURL: any;
  imageFile: any;
  imagePath!: any;

  types = ['New', 'Like New', 'Second Hand', 'Old'];

  constructor(private router: Router, private fb: FormBuilder, 
    private activeService: ActiveService, private tokenStorageService: TokenStorageService, 
    private providerService: ProviderService, private categoryService: CategoryService,
    private productService: ProductService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };
    this.getCategory();
    this.getProduct();
    this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      name: ['', [Validators.required]],  
      price: ['', [Validators.required]], 
      type: ['', [Validators.required]], 
      status:  ['', [Validators.required]],
      description: ['', [Validators.required]],  
      categoryId: ['', [Validators.required]], 
      image: [], 
    })
  }

  get f() { return this.dataForm.controls; }

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
            this.products = data.data;
            this.dtTrigger.next();
            console.log(this.products);
          },
          error => {
            console.log(error);
          });
  }

  onSelectFile(event:any){
    if(event.target.files.length >0){
      const file = event.target.files[0];
      this.imageFile = file;
      // this.f['image'].setValue(file);

      var mimeType = event.target.files[0].type;
      if(mimeType.match(/image\/*/) == null){
        this.message = "Only images are supported";
        return;
      }
      var reader = new FileReader();
      this.imagePath = file;
      console.log(this.imageFile);
      console.log(JSON.stringify(this.imageFile));
      console.log(JSON.stringify(this.imagePath));
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
        console.log(this.imgURL);
      }
    }
  }

  addProduct(){
    this.token = this.tokenStorageService.getToken();
    let formData = new FormData();
    let product = new Product();
    product.name = this.f.name.value;
    product.type = this.f.type.value;
    product.price = this.f.price.value;
    product.description = this.f.description.value;
    product.status = this.f.status.value;
    product.categoryId = this.f.categoryId.value;
    console.log(this.imageFile);
    console.log(product);
    formData.append('product', JSON.stringify(product));
    formData.append('file', this.imageFile);
    this.productService.createProduct(this.token, formData)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              this.message = "*" + data.message;
            } else {
              this.reloadPage();
            }
          },
          error => {
            console.log(error);
          });
  }

  deleteProduct(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.deleteProduct(this.token, id)
        .subscribe(
          (data: Response) => {
            if (data.status === 404) {
              this.message = data.message;
            } else {
              this.reloadPage();
            }
          },
          error => {
            console.log(error);
          });
  }

  toProductDetail(productId: string){
    this.router.navigate(['admin/product/' + productId]).then(this.reloadPage);
  }

  clickedDeleteBtn(id: number) {
    this.deleteId = id;
  }

  reloadPage(): void {
    window.location.reload();
  }
}
