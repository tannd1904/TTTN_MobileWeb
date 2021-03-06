import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from 'src/app/model/login-request';
import {Response} from 'src/app/model/response';
import {AuthService} from 'src/app/service/auth.service';
import {CartService} from 'src/app/service/cart.service';
import {ClassBodyService} from 'src/app/service/class-body.service';
import {CountService} from 'src/app/service/count.service';
import {PageService} from 'src/app/service/page.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';

declare var greCaptcha: any;


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  dataForm!: FormGroup;
  role!: string;
  token: string = '';
  IsLogin = false;
  count!: any;
  classBody: string = 'user-login blog';
  page: number = 2;
  submitted = false;
  captchaError: boolean = false;
  invalidLogin: boolean = false;
  loginResponse!: string;

  constructor(private fb: FormBuilder, private authService: AuthService,
              private tokenStorage: TokenStorageService, private router: Router,
              private tokenStorageService: TokenStorageService,
              private cartService: CartService, private countService: CountService,
              private classBodyService: ClassBodyService,
              private pageService: PageService) {
  }

  get f() {
    return this.dataForm.controls;
  }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.infoForm();
  }

  infoForm() {
    /*Create Form group*/
    this.dataForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@'
        + '[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.dataForm.invalid) {
      return;
    }
    // const response = greCaptcha.getResponse();
    // if (response.length === 0) {
    //   this.captchaError = true;
    //   return;
    // }
    let login = new LoginRequest();
    login.email = this.dataForm.controls.email.value;
    login.password = this.dataForm.controls.password.value;
    // login.recaptchaResponse = response;
    this.authService.login2(login).subscribe(
      (data: Response) => {
        if (data.status === 200) {
          let userLogin = data.data;
          this.tokenStorage.saveUser(userLogin);
          this.token = this.tokenStorage.getUser().token;
          this.tokenStorage.saveToken(this.token);
          this.role = this.tokenStorage.getUser().role;
          const user = this.tokenStorageService.getUser();
          if (this.role == 'ROLE_USER') {
            // this.cartService.countCartById(this.token, user.id)
            //                         .subscribe(
            //                           (data) => {
            //                             this.count = data;
            //                             this.countService.changeCount(this.count);
            //                           },
            //                           error => {
            //                             console.log(error);
            //                           }
            //                         );
            this.router.navigate(['../']).then(this.reloadPage);
          } else {
            this.router.navigate(['admin']).then(this.reloadPage);
          }
        } else {
          this.invalidLogin = true;
          this.loginResponse = data.message;
        }
        // greCaptcha.reset();
      },
      err => {
        this.IsLogin = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

}
