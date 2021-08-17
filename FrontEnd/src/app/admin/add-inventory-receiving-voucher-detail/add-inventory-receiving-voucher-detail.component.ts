import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Import } from 'src/app/model/import';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { ActiveService } from 'src/app/service/active.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { ImportService } from 'src/app/service/import.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-add-inventory-receiving-voucher-detail',
  templateUrl: './add-inventory-receiving-voucher-detail.component.html',
  styleUrls: ['./add-inventory-receiving-voucher-detail.component.css'],
})
export class AddInventoryReceivingVoucherDetailComponent implements OnInit {
  @Input('class')
  klass!: string;

  @Input()
  ngClass!: string | string[] | Set<string> | { [klass: string]: any };

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  imports: Array<Import> = [];

  token: any;
  submitted = false;
  toggleForm: boolean[] = [false, false];
  
  quantity: number = 3;

  dataForm!: FormGroup;
  subForm!: FormGroup[];
  testForm!: FormGroup;

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
  ) {}

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };
    this.infoForm();
    this.infoSubForm();
    console.log(this.testForm.controls.serialForm); 
    console.log(this.array.controls);
    this.getImports();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      color: [{value: this.color[0]}, [Validators.required]],
      ram: [{value: this.ram[0]}, [Validators.required]],
      cpu: [{value: this.cpu[0]}, [Validators.required]],
      screen: [{value: this.screen[0]}, [Validators.required]],
      memmory: [{value: this.memmory[0]}, [Validators.required]],
      camera: [{value: this.camera[0]}, [Validators.required]],
      pin: [{value: this.pin[0]}, [Validators.required]],
      os: [{value: this.os[0]}, [Validators.required]],
      quantity: ['', [Validators.required]]
    })
  }

  infoSubForm() {
    console.log(this.f.quantity.value);
    this.quantity = this.f.quantity.value;
    console.log(this.quantity);
    this.testForm = this.fb.group({
      serialForm: this.fb.array(this.initFormArray)
    })

    console.log(this.testForm.controls.serialForm); 
    console.log(this.array.controls);
  }

  test2() {
    
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
    if (this.f.invalid) {
      return;
    }
  }

  onSubmitSubForm() {
    console.log('submit thanh cong')
    console.log(this.array.controls[1].value.serial);
  }

  getImports() {
    this.token = this.tokenStorageService.getToken();
    this.importService.getAllImports(this.token).subscribe(
      (data: Response) => {
        this.imports = data.data;
        this.imports.forEach(s => {
          console.log(s.id);
          this.token = this.tokenStorageService.getToken();
          this.employeeService.getEmplById(s.id, this.token).subscribe(
            (data: Response) => {
              s.employeeFullName = data.data.firstname + ' ' + data.data.lastname;
            }, (err) => {
              console.log(err);
            }
            );
          console.log(s.employeeFullName);
        })
        this.dtTrigger.next();
        console.log(this.imports);
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
}
