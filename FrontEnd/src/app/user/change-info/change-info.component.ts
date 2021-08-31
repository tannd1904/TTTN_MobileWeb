import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Response } from 'src/app/model/response';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-change-info',
  templateUrl: './change-info.component.html',
  styleUrls: ['./change-info.component.css']
})
export class ChangeInfoComponent implements OnInit {

  user!: User;
  id!: number;
  dataForm!: FormGroup;

  notification = false;
  message!: string;
  submitted = false;
  token!: any;
  isAdmin = false;

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private userService: UserService,
    private employeeService: EmployeeService,
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.id = user.id;
    if (user.role == "ROLE_ADMIN") {
      this.isAdmin = true;
    }

    this.dataForm = this.formBuilder.group({
      id: [{value: '', disabled: true}],
      username: [{value: '', disabled: true}, Validators.required],
      password: [{value: '', disabled: true}],
      email: [{value: '', disabled: true}, Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      phone: ['', Validators.required],
      address: ['', Validators.required],
    })
    console.log(this.id);
    this.getUserById(this.id);
  }

  getUserById(id: number) {
    this.token = this.tokenStorageService.getToken();
    if (!this.isAdmin) {
      this.userService.getUserById(this.token, id)
      .subscribe((data: Response) => {
        this.user = data.data;
        console.log(this.user);
        this.dataForm.patchValue({
          username: this.user.email,
          firstname: this.user.firstname,
          lastname: this.user.lastname,
          phone: this.user.phone,
          address: this.user.address,
        });
      }, (err) => {
        console.log(err);
      })
    } else {
      this.employeeService.getEmplById(id, this.token)
      .subscribe((data: Response) => {
        this.user = data.data;
        console.log(this.user);
        this.dataForm.patchValue({
          username: this.user.email,
          firstname: this.user.firstname,
          lastname: this.user.lastname,
          phone: this.user.phone,
          address: this.user.address,
        });
      }, (err) => {
        console.log(err);
      })
    }
    
  }

  get f() {
    return this.dataForm.controls;
  }

  updateUser(id: number, user: User) {
    this.token = this.tokenStorageService.getToken();
    if (!this.isAdmin) {
      this.userService.updateUser(this.token, id, user)
      .subscribe(
        (data: Response) => {
          console.log(data);
          this.router.navigate(['/']);
          // this.userService.updateProductDetails(id, this.imageDetails);           
        },
        error => {
          console.log(error);
        });
    } else {
      this.employeeService.updateEmployee(this.token, id, user)
          .subscribe(
            (data: Response) => {
              console.log(data);
              this.router.navigate(['../admin']);
              // this.userService.updateProductDetails(id, this.imageDetails);           
            },
            error => {
              console.log(error);
            });
    }
    
  }

  onSubmit() {
    // alert("123");
    this.submitted = true;
    if (this.f.invalid) {
      alert("456");
      return;
    } else {
      this.user.firstname = this.f.firstname.value;
      this.user.lastname = this.f.lastname.value;
      this.user.phone = this.f.phone.value;
      this.user.address = this.f.address.value;
      console.log(this.user);
      
      this.updateUser(this.user.id, this.user);
    }
  }
}
