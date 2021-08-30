import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { OrderDetail } from 'src/app/model/order-detail';
import { Product } from 'src/app/model/product';
import { Response } from 'src/app/model/response';
import { Review } from 'src/app/model/review';
import { AuthService } from 'src/app/service/auth.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { OrderService } from 'src/app/service/order.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  orderId!: number;
  productId!: number;
  submitted = false;
  clickReview: boolean[] = [];

  message = '';
  imgURL: any;
  imageFile: any;
  imagePath!: any;
  token!: string;
  order = new Order();
  dataForm!: FormGroup;
  listOrderDetail = new Array<OrderDetail>();
  listProduct = new Array<Product>();
  listProductId = new Array<number>();

  constructor(
    private router: Router,
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private productService: ProductService,
    private fb: FormBuilder,) { }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['id'];
    this.getOrderById(this.orderId);
    this.getOrderDetailByOrderId(this.orderId);
    this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      comment: ['', [Validators.required]], 
      rate: ['', Validators.required],
      image: [], 
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    console.log(this.dataForm.value);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.addReview(this.productId);
  }

  addReview(id : number) {
    this.token = this.tokenStorageService.getToken();
    let formData = new FormData();
    let review = new Review();
    review.rating = this.f.rate.value;
    review.content = this.f.comment.value;
    review.orderId = this.orderId;
    review.productId = id;
    console.log(this.imageFile);
    formData.append('review', JSON.stringify(review));
    formData.append('file', this.imageFile);
    console.log(review);
    this.orderService.createReview(this.token, formData)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              this.message = "*" + data.message;
            } else {
              window.location.reload();
            }
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
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
        console.log(this.imgURL);
      }
    }
  }

  getOrderById(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getOrderById(this.token, this.orderId)
      .subscribe((data: Response) => {
        this.order = data.data;
        this.order.listReviews.forEach(s => {
          console.log('get review')
          this.productService.getReviewsById(this.token, s.id)
            .subscribe((data: Response) => {
              console.log(data.data);
              s.productId = data.data.productId;
              s.orderId = data.data.orderId;
            })
        })
        console.log(this.order)
      }, (err) => {
        console.log(err);
      })
  }

  getOrderDetailByOrderId(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.getOrderDetailByOrderId(this.token, this.orderId).subscribe(
      (data: Response) => {
        this.listOrderDetail = data.data;
        this.listOrderDetail.forEach(s => {
          this.productService.getProductDetailById(this.token, s.productDetailId)
            .subscribe((data: Response) => {
              s.productDetail = data.data;
            })
          this.productService.getProductById(this.token, s.productId)
            .subscribe((data: Response) => {
              s.product = data.data;
            })
        })
        console.log(this.listOrderDetail);
        this.listOrderDetail.forEach(c => {
          this.listProductId.push(c.productId);
        })

        this.order.listReviews.forEach(z => {
          this.listProductId = this.listProductId.filter(x => x != z.productId)
        })
        console.log(this.listProductId)
        const array = new Set(this.listProductId);
        console.log(array);
        this.listProduct = [];
        array.forEach(s => {
        this.productService.getProductById(this.token, s)
          .subscribe((data: Response) => {
            this.listProduct.push(data.data)
          })
        })
        console.log(this.listProduct);
        this.clickReview.length = this.listProduct.length;
        this.clickReview.fill(false);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  clickReviewBtn(id: number, productId: number) {
    this.infoForm();
    this.submitted = false;
    this.productId = productId;
    this.clickReview.fill(false)
    this.clickReview[id] = !this.clickReview[id];
    console.log(this.clickReview);
  }

  isProductHadReview(id: number) {
    var check = false;
    this.order.listReviews.forEach(s => {
      if (s.productId === id) {
        check = true;
      }
    })
    return check; 
  }

  receiveOrder(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.receiveOrder(this.token, id).subscribe(
      (data: Response) => {
        var message = data.message;
        console.log(message);
        window.location.reload();
      }, (err) => {
        console.log(err);
      }
    )
  }

  cancelOrder(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.orderService.cancelOrderByUser(this.token, id).subscribe(
      (data: Response) => {
        var message = data.message;
        console.log(message);
        window.location.reload();
      }, (err) => {
        console.log(err);
      }
    )
  }
}
