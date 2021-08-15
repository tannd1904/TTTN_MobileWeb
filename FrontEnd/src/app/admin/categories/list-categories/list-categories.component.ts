import { Category } from './../../../model/category';
import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { ActiveService } from 'src/app/service/active.service';
import { Room } from 'src/app/model/room';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from 'src/app/service/category.service';
import { Response } from 'src/app/model/response';
import { DataTableDirective, DataTablesModule } from 'angular-datatables';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-list-categories',
  templateUrl: './list-categories.component.html',
  styleUrls: ['./list-categories.component.css']
})
export class ListCategoriesComponent implements OnInit {

  // @ViewChild(DataTableDirective, {static: true})
  // dtElement!: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  active: number = 4;
  token: any;
  dataForm!: FormGroup;
  submitted = false;
  deleteId!: number;
  message!: string;
  categories: Array<Category> = [];

  constructor(private fb: FormBuilder, private userService: UserService, private tokenStorageService: TokenStorageService, private activeService: ActiveService,  private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
    };

    this.getCategory();
    // this.getRoom();
    this.infoForm();
  }

  // rerender(): void {
  //   this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
  //     // Destroy the table first
  //     dtInstance.destroy();
  //     // Call the dtTrigger to rerender again
  //     this.dtTrigger.next();
  //   });
  // }

  infoForm(){
    this.dataForm = this.fb.group({
      name: ['', [Validators.required]]
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addCategory();
  }

  addCategory(){
    this.token = this.tokenStorageService.getToken();
    let category = new Category();
    category.name = this.f.name.value;
    this.categoryService.createCategory(this.token, category)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              this.message = "*" + data.message;
              console.log(this.message);
            } else {
              this.reloadPage();  
            }
          },
          error => {
            console.log(error);
          });
  }

  getCategory(){
    this.token = this.tokenStorageService.getToken();
    this.categoryService.getCategory(this.token)
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

  deleteCategory(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.categoryService.deleteCategory(this.token, id)
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

  clickedDeleteBtn(id: number) {
    this.deleteId = id;
  }

  reloadPage(): void {
    window.location.reload();
  }
}
