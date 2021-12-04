import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subject} from 'rxjs';
import {Category} from 'src/app/model/category';
import {Response} from 'src/app/model/response';
import {User} from 'src/app/model/user';
import {ActiveService} from 'src/app/service/active.service';
import {CategoryService} from 'src/app/service/category.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
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

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private activeService: ActiveService,
              private categoryService: CategoryService,
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

    this.getUsers();
    // this.getRoom();
    this.infoForm();
  }

  infoForm() {
    this.dataForm = this.fb.group({
      name: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.dataForm.invalid) {
      return;
    }
    this.addCategory();
  }

  addCategory() {
    this.token = this.tokenStorageService.getToken();
    let category = new Category();
    category.name = this.f.name.value;
    this.categoryService.createCategory(this.token, category).subscribe(
      (data: Response) => {
        if (data.status !== 200) {
          this.message = '*' + data.message;
          console.log(this.message);
        } else {
          this.ngOnInit();
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getUsers() {
    this.token = this.tokenStorageService.getToken();
    this.userService.getAllUsers(this.token).subscribe(
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

  reloadPage(): void {
    window.location.reload();
  }

}
