import {Component, OnInit} from '@angular/core';
import {AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {Observable, of, Subject} from 'rxjs';
import {Category} from 'src/app/model/category';
import {Response} from 'src/app/model/response';
import {User} from 'src/app/model/user';
import {ActiveService} from 'src/app/service/active.service';
import {AuthService} from 'src/app/service/auth.service';
import {CategoryService} from 'src/app/service/category.service';
import {EmployeeService} from 'src/app/service/employee.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';
import {delay, map, switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  active: number = 4;
  token: any;
  dataForm!: FormGroup;
  submitted = false;
  deleteId!: number;
  message!: string;
  categories: Array<Category> = [];
  users: Array<User> = [];
  role: String = 'admin';

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private activeService: ActiveService,
              private categoryService: CategoryService,
              private employeeService: EmployeeService,
              private authService: AuthService,
              private router: Router,
  ) {
  }

  get f() {
    return this.dataForm.controls;
  }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };

    this.getEmployee();
    // this.getRoom();
    this.infoForm();
  }

  infoForm() {
    /*Create Form group*/
    this.dataForm = this.fb.group(
      {
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        gender: ['', [Validators.required]],
        phone: ['', [Validators.required, Validators.pattern('^[_0-9]{10}')]],
        email: [
          '',
          [
            Validators.required,
            Validators.pattern(
              '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@' +
              '[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'
            ),
          ],
          [this.emailExistsValidator()],
        ],
        address: ['', [Validators.required]],
        password: ['', [Validators.required]],
        cfmPassword: ['', [Validators.required]],
      },
      {
        validators: this.MustMatch('password', 'cfmPassword'),
      }
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.dataForm.invalid) {
      return;
    }
    const val = this.dataForm.value;
    console.log(val);
    this.addData();
  }


  getEmployee() {
    this.token = this.tokenStorageService.getToken();
    this.employeeService.getAllEmpl(this.token).subscribe(
      (data: Response) => {
        this.users = data.data;
        this.dtTrigger.next();
        console.log(this.users);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteCategory(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.categoryService.deleteCategory(this.token, id).subscribe(
      (data: Response) => {
        if (data.status === 404) {
          this.message = data.message;
        } else {
          this.ngOnInit();
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  clickedDeleteBtn(id: number) {
    this.deleteId = id;
  }

  addData() {
    this.authService.register(this.dataForm.value, this.role).subscribe((data: Response) => {
      console.log('Registion success');
      this.message = data.message;
      this.reloadPage();
    });
  }

  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (matchingControl.errors && !matchingControl.errors.MustMatch) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({MustMatch: true});
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  reloadPage(): void {
    window.location.reload();
  }

  private emailExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return of(control.value).pipe(
        delay(500),
        switchMap((email: any) => this.userService.doesEmailExist(email).pipe(
          map(emailExists => emailExists ? {emailExists: true} : null)
        ))
      );
    };
  }
}
