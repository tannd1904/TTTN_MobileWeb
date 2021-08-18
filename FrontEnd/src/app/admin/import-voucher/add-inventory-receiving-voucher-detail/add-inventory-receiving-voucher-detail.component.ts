import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { ImportVoucher } from 'src/app/model/import-voucher';
import { ImportDetail } from 'src/app/model/import-detail';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { ActiveService } from 'src/app/service/active.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { ImportService } from 'src/app/service/import.service';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-add-inventory-receiving-voucher-detail',
  templateUrl: './add-inventory-receiving-voucher-detail.component.html',
  styleUrls: ['./add-inventory-receiving-voucher-detail.component.css'],
  providers: [DatePipe]
})
export class AddInventoryReceivingVoucherDetailComponent implements OnInit {
  @Input('class')
  klass!: string;

  @Input()
  ngClass!: string | string[] | Set<string> | { [klass: string]: any };

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  
  import!: ImportVoucher;
  imports: Array<ImportVoucher> = [];
  importDetail!: ImportDetail;
  listImportDetail: Array<ImportDetail> = [];
  products: Product[] = [];
  productDetail = new ProductDetail();
  listProductDetail: ProductDetail[] = [];

  token: any;
  submitted = false;
  submitted1 = false;
  toggleForm: boolean[] = [false, false];
  message!: string;
  currentDate!: any;
  thisEmployee!: any;
  
  quantity: number = 0;

  dataForm!: FormGroup;
  subForm!: FormGroup[];
  testForm!: FormGroup;
  importDetailId!: number;

  otherOption = [false, false, false, false, false, false, false, false];
  color = ['Red', 'Black', 'White', 'Pink', 'Gold', 'Other'];
  ram = ['2GB', '3GB', '4GB', '5GB', '6GB', 'Other'];
  cpu = ['Gen 7th', 'Gen X', 'Other'];
  screen = ['Full HD', '2K', 'Other'];
  memmory = ['SD 16GB', 'HD 8GB', 'Other'];
  camera = ['Dual Cam', '12.0 pixel', '18.0 pixel', 'Other'];
  pin = ['2000 mAh', '3000 mAh', '4000 mAh', 'Other'];
  os = ['Android', 'iOS', 'Other'];

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private activeService: ActiveService,
    private importService: ImportService,
    private employeeService: EmployeeService,
    private productService: ProductService,
    private productDetailService: ProductDetailService,
    private datePipe: DatePipe,
  ) {}

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };
    this.currentDate = new Date();
    this.currentDate = this.datePipe.transform(this.currentDate, 'yyyy-MM-dd');
    this.thisEmployee = this.tokenStorageService.getUser();

    
    this.infoForm();
    this.infoSubForm();
    this.loadImport();
    // this.getImports();
    this.getProduct();
  }

  // TODO: Remove item importId in sessionStorage when unused
  loadImport() {
    if (sessionStorage.getItem('importId') === null) {
      this.import = new ImportVoucher();
      this.import.date = this.currentDate;
      this.import.employeeId = this.thisEmployee.id;
      this.createImport(this.import);
    } else {
      var id = JSON.parse(sessionStorage.getItem('importId') || '{}');
      this.getImportById(id);
    }
  }

  infoForm(){
    this.dataForm = this.fb.group({
      productId: ['', Validators.required],
      color: ['', [Validators.required]],
      ram: ['', [Validators.required]],
      cpu: ['', [Validators.required]],
      screen: ['', [Validators.required]],
      memmory: ['', [Validators.required]],
      camera: ['', [Validators.required]],
      pin: ['', [Validators.required]],
      os: ['', [Validators.required]],
      note: [''],
      quantity: ['', [Validators.required, Validators.min(0), Validators.max(10)]]
    })
  }

  infoSubForm() {
    this.quantity = this.f.quantity.value;
    this.testForm = this.fb.group({
      serialForm: this.fb.array(this.initFormArray)
    })
  }


  get initFormArray(){
    
    let array = [];

    for(let i = 0;i <this.quantity;i++){
      array.push(
        
        this.fb.group({
          serial: ['', Validators.required]
        })

      )
    }

    return array;
  }

  get f() { return this.dataForm.controls; }

  get array() { return this.testForm.get('serialForm') as FormArray}

  onSubmit() {
    this.submitted = true;
    // this.infoSubForm();
    if (this.dataForm.invalid) {
      return;
    } else {
      this.productDetail = new ProductDetail();
      this.productDetail.color = this.f.color.value;
      this.productDetail.cpu = this.f.cpu.value;
      this.productDetail.ram = this.f.ram.value;
      this.productDetail.screen = this.f.screen.value;
      this.productDetail.memmory = this.f.memmory.value;
      this.productDetail.pin = this.f.pin.value;
      this.productDetail.os = this.f.os.value;
      this.productDetail.camera = this.f.camera.value;
      this.productDetail.note = this.f.note.value;
      console.log(this.productDetail);
      
      this.importDetail = new ImportDetail();
      this.importDetail.quantity = this.f.quantity.value;
      this.importDetail.productId = this.f.productId.value;
      this.importDetail.price = 0;
      this.importDetail.importVoucherId = this.import.id;

      this.infoSubForm();
      this.toggleForm[0] = !this.toggleForm[0]; 
      this.toggleForm[1] = !this.toggleForm[1];
      
    }
  }

  onSubmitSubForm() {
    this.submitted1 = true;
    console.log('submit thanh cong')
    if (this.array.invalid) {
      return;
    } else {
      let serial : string[] = [];
      for (let i=0; i<this.quantity; i++) {
        serial.push(this.array.controls[i].value.serial)
      }
      console.log(serial);
      console.log(this.importDetail);
      this.createImportDetail(this.importDetail);
      console.log(this.importDetail);
      for(let i = 0; i<this.importDetail.quantity; i++) {
        console.log(this.array.controls[i].value.serial);
        let pro = new ProductDetail();
        pro = this.productDetail;
        pro.serial = serial[i];
        console.log(pro.serial);
        console.log(pro);
        let id = JSON.parse(sessionStorage.getItem('importDetailId') || '{}');
        console.log(id);
        pro.importVoucherDetailId = id;
        console.log(pro);
        this.createProductDetail(pro);
        this.listProductDetail.push(pro);
      }
      console.log(this.listProductDetail);
      // this.array.controls.forEach((f) => {
        // console.log(f.value.serial)
        // let pro = new ProductDetail();
        // pro = this.productDetail;
        // pro.serial = f.value.serial
        // console.log(this.importDetailId);
        // let id = JSON.parse(sessionStorage.getItem('importDetailId') || '{}');
        // console.log(id);
        // pro.importVoucherDetailId = id;
        // console.log(pro);
        // TODO: continue fix bug in this place, client cant get importVoucherDetailId

        // this.productDetail.serial = f.value.serial;
        // console.log(this.productDetail);
        // this.productDetail.importVoucherDetailId = this.importDetail.id;
        // this.createProductDetail(this.productDetail)
      // })
      // this.reloadPage();
    }

  }

  createImport(importVoucher: ImportVoucher) {
    this.token = this.tokenStorageService.getToken();
    this.importService.createImport(this.token, importVoucher)
      .subscribe(
        (data: Response) => {
          if (data.status !== 200) {
            this.message = "*" + data.message;
          } else {
            this.message = "Create Import successfully";
            console.log(this.message);
            sessionStorage.setItem('importId', JSON.stringify(data.data.id));
          }
        }, (err) => {
          console.log(err);
        }
      )
  }

  createImportDetail(importDetail: ImportDetail) {
    this.token = this.tokenStorageService.getToken();
    this.importService.createImportDetail(this.token, importDetail)
      .subscribe(
        (data: Response) => {
          if (data.status !== 200) {
            this.message = "*" + data.message;
          } else {
            console.log(data.data);
            this.importDetailId = data.data.id;
            sessionStorage.setItem('importDetailId', JSON.stringify(data.data.id));
          }
        }, (err) => {
          console.log(err);
        }
      )
  }

  createProductDetail(productDetail: ProductDetail) {
    this.token = this.tokenStorageService.getToken();
    this.productDetailService.createProductDetail(this.token, productDetail)
      .subscribe(
        (data: Response) => {
          if (data.status !== 200) {
            this.message = "*" + data.message;
          } else {
            console.log('Create product detail successfully');
            console.log(data.data)
          }
        }, (err) => {
          console.log(err);
        }
      )
  }

  getProduct(){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token)
        .subscribe(
          (data: Response) => {
            this.products = data.data;
          },
          error => {
            console.log(error);
          });
  }

  getImportById(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.importService.getImportById(this.token, id)
        .subscribe(
          (data: Response) => {
            this.import = data.data;
          },
          error => {
            console.log(error);
          });
  }

  getAllImportDetail(){
    this.token = this.tokenStorageService.getToken();
    this.importService.getAllImportDetails(this.token)
        .subscribe(
          (data: Response) => {
            this.listImportDetail = data.data;
          },
          error => {
            console.log(error);
          });
  }

  getImports() {
    this.token = this.tokenStorageService.getToken();
    this.importService.getAllImports(this.token).subscribe(
      (data: Response) => {
        this.imports = data.data;
        this.imports.forEach(s => {
          this.token = this.tokenStorageService.getToken();
          this.employeeService.getEmplById(s.employeeId, this.token).subscribe(
            (data: Response) => {
              s.employeeFullName = data.data.firstname + ' ' + data.data.lastname;
            }, (err) => {
              console.log(err);
            }
            );
        })
        this.dtTrigger.next();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getEmplName(id: number) {
    let empl = new User();
    this.token = this.tokenStorageService.getToken();
    this.employeeService.getEmplById(id, this.token).subscribe(
      (data: Response) => {
        empl = data.data;
        return empl.firstname + empl.lastname;
      }, (error) => {
        console.log(error);
      }
    )
  }

  test(index: number, value: string) {
    console.log(value);
    if (value !== 'Other') {
      this.otherOption[index] = false;
    } else {
      this.otherOption[index] = true;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
