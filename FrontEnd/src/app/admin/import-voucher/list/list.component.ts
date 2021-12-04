import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Subject} from 'rxjs';
import {ImportVoucher} from 'src/app/model/import-voucher';
import {Response} from 'src/app/model/response';
import {ActiveService} from 'src/app/service/active.service';
import {EmployeeService} from 'src/app/service/employee.service';
import {ImportService} from 'src/app/service/import.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {UserService} from 'src/app/service/user.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  imports: Array<ImportVoucher> = [];

  token: any;
  submitted = false;
  toggleForm: boolean[] = [false, false];
  message!: string;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private activeService: ActiveService,
              private importService: ImportService,
              private employeeService: EmployeeService,
  ) {
  }

  ngOnInit(): void {
    this.getImports();
  }

  getImports() {
    this.token = this.tokenStorageService.getToken();
    this.importService.getAllImports(this.token).subscribe(
      (data: Response) => {
        console.log(data);
        this.imports = data.data;
        this.imports.forEach((s, i) => {
          console.log(s.id);
          this.token = this.tokenStorageService.getToken();
          this.employeeService.getEmplById(s.employeeId, this.token).subscribe(
            (data: Response) => {
              console.log(data);
              s.employeeFullName = data.data.firstname + ' ' + data.data.lastname;
            }, (err) => {
              console.log(err);
            }
          );
          console.log(s.employeeFullName);

        });
        console.log(this.imports);
        this.dtTrigger.next();

      },
      (error) => {
        console.log(error);
      }
    );
  }

  createImport() {
    sessionStorage.removeItem('importId');
    sessionStorage.removeItem('importDetailId');
  }

}
