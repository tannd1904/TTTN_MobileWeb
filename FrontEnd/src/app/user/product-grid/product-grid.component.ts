import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Cart} from 'src/app/cart';
import {Category} from 'src/app/model/category';
import {Product} from 'src/app/model/product';
import {ProductDetail} from 'src/app/model/product-detail';
import {Response} from 'src/app/model/response';
import {WishList} from 'src/app/model/wish-list';
import {CartService} from 'src/app/service/cart.service';
import {CategoryService} from 'src/app/service/category.service';
import {ClassBodyService} from 'src/app/service/class-body.service';
import {PageService} from 'src/app/service/page.service';
import {ProductService} from 'src/app/service/product.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';
import {Property} from '../../model/property';
import {PropertyService} from '../../service/property.service';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.css'],
})
export class ProductGridComponent implements OnInit {
  classBody: string = 'product-detail';
  page: number = 4;

  userId!: number;
  cart = new Array<Cart>();
  categories: Array<Category> = [];
  allProducts: Array<Product> = [];
  products: Array<Product> = [];
  listProductDetail: ProductDetail[] = [];
  displayProductList: Product[] = [];
  operatorArr = [-999,-999,-999,-999,-999];
  valueArr = ['','','','',''];
  flagArr = [false, false, false, false, false];
  token!: string;
  config!: any;

  colors = [
    {
      name: 'Black',
      checked: false
    },
    {
      name: 'White',
      checked: false
    },
    {
      name: 'Silver',
      checked: false
    },
    {
      name: 'Blue',
      checked: false
    },
    {
      name: 'Red',
      checked: false
    },
    {
      name: 'Gold',
      checked: false
    },
  ];

  memmories = [
    {
      name: '16 GB',
      checked: false
    },
    {
      name: '32 GB',
      checked: false
    },
    {
      name: '64 GB',
      checked: false
    },
    {
      name: '128 GB',
      checked: false
    },
    {
      name: '256 GB',
      checked: false
    },
    {
      name: '512 GB',
      checked: false
    },
    {
      name: '1024 GB',
      checked: false
    },
  ];

  rams = [
    {
      name: '2GB',
      checked: false
    },
    {
      name: '3GB',
      checked: false
    },
    {
      name: '4GB',
      checked: false
    },
    {
      name: '6GB',
      checked: false
    },
    {
      name: '8GB',
      checked: false
    },
    {
      name: '12GB',
      checked: false
    },
    {
      name: '16GB',
      checked: false
    },
  ];

  prices = [
    {
      name: '0$ - 500$',
      from: 0,
      to: 500,
      checked: false
    },
    {
      name: '500$ - 800$',
      from: 500,
      to: 800,
      checked: false
    },
    {
      name: '800$ - 1000$',
      from: 800,
      to: 1000,
      checked: false
    },
    {
      name: '1000$ - 1500$',
      from: 1000,
      to: 1500,
      checked: false
    },
    {
      name: '> 1500$',
      from: 1500,
      to: 10000000,
      checked: false
    },

  ];


  constructor(
    private classBodyService: ClassBodyService,
    private pageService: PageService,
    private tokenStorageService: TokenStorageService,
    private productService: ProductService,
    private propertyService: PropertyService,
    private categoryService: CategoryService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  get selectedRam() {
    //Get all the selected brands
    return this.rams.filter(opt => opt.checked);
  }

  get selectedPrice() {
    //Get all the selected brands
    return this.prices.filter(opt => opt.checked);
  }

  get selectedColor() {
    return this.colors.filter(opt => opt.checked);
  }

  get selectedMemory() {
    return this.memmories.filter(opt => opt.checked);
  }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.getAllProduct();
    this.getCategory();
    this.config = {
      itemsPerPage: 6,
      currentPage: 1,
      totalItems: this.products.length
    };
    this.cart = this.cartService.getCart();
  }

  clickCompare(product: any) {
    console.log(product);
    sessionStorage.setItem('PRODUCT_COMPARE', JSON.stringify(product));
    this.router.navigate(['../compare']);
  }

  isLoggedIn(): boolean {
    this.token = this.tokenStorageService.getToken();
    if (this.token == '{}') {
      return false;
    } else {
      const user = this.tokenStorageService.getUser();
      this.userId = user.id;
      return true;
    }
  }

  addToWishList(productId: number) {
    if (!this.isLoggedIn()) {
      this.router.navigate(['../login']).then(window.location.reload);
    } else {
      var wishList = new WishList();
      wishList.userId = this.userId;
      wishList.productId = productId;
      this.token = this.tokenStorageService.getToken();
      this.productService.addToWishList(this.token, wishList)
        .subscribe(
          (data: Response) => {
            if (data.status !== 200) {
              var message = 'Create WishList unsuccessfully';
              console.log(message);
            } else {
              var message = 'Create WishList successfully';
              console.log(message);
              this.router.navigate(['../wishlist']);
            }
          }, (err) => {
            console.log(err);
          }
        );
    }
  }

  addToCart(id: number) {
    var pro = new Array<Product>();
    pro = this.products.filter(x => x.id == id);
    pro.forEach(p => {
      if (!(p.productDetails.length == 0 || p.productDetails.length == 1)) {
        this.router.navigate(['../product-detail/' + id]);
      } else {
        var temp = new Cart();
        temp.quantity = 1;
        var valueToRemove = 0;
        this.cart.forEach((c) => {
          if (c.product.id === p.id) {
            temp.quantity = c.quantity + 1;
            valueToRemove = c.product.id;
          }
        });
        var copy = this.cart;
        this.cart = [];
        this.cart = copy.filter(x => x.product.id !== valueToRemove);
        temp.product = p;
        temp.price = p.price;
        temp.total = p.price * temp.quantity;
        this.cart.push(temp);
        this.cartService.saveCart(this.cart);
        window.location.reload();
      }
    });
  }

  getAllProduct() {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductImported(this.token).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach((s) => {
          this.categoryService
            .getCategoryById(this.token, s.categoryId)
            .subscribe(
              (data: Response) => {
                s.categoryName = data.data.name;
              },
              (err) => {
                console.log(err);
              }
            );
          s.productDetails = new Array<ProductDetail>();
          this.productService.getAllProductDetailByProductId(this.token, s.id)
            .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err);
              }
            );
          s.properties = new Array<Property>();
          this.propertyService.getPropertyByProductId(this.token, s.id)
            .subscribe((d: Response) => {
              s.properties = d.data;
            }, (err) => {
              console.log(err);
            });
        });
        this.allProducts = this.products;
        this.products = this.products.sort(function(high, low) {
          if (low.name < high.name) {
            return -1;
          } else if (low.name > high.name) {
            return 1;
          } else {
            return 0;
          }
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getProductByCategoryId(categoryId: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getImportedProductByCategoryId(this.token, categoryId).subscribe(
      (data: Response) => {
        this.products = data.data;
        console.log(this.products);
        this.products.forEach((s) => {
          this.categoryService
            .getCategoryById(this.token, s.categoryId)
            .subscribe(
              (data: Response) => {
                s.categoryName = data.data.name;
              },
              (err) => {
                console.log(err);
              }
            );
          s.productDetails = new Array<ProductDetail>();
          this.productService.getAllProductDetailByProductId(this.token, s.id)
            .subscribe((d: Response) => {
                s.productDetails = d.data;
              }, (err) => {
                console.log(err);
              }
            );
          s.properties = new Array<Property>();
          this.propertyService.getPropertyByProductId(this.token, s.id)
            .subscribe((d: Response) => {
              s.properties = d.data;
            }, (err) => {
              console.log(err);
            });
        });
        this.allProducts = this.products;
        console.log(this.products[0].importVoucherDetails[0].productDetails);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getCategory() {
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

  getProductDetail(id: number) {
    this.token = this.tokenStorageService.getToken();
    this.productService.getProductDetailByProductId(this.token, id)
      .subscribe(
        (data: Response) => {
          this.listProductDetail = data.data;
          // console.log(this.listProductDetail);
        },
        error => {
          console.log(error);
        });
  }

  chooseBrand(event: any) {
    console.log(event.target.value);
    this.getProductByCategoryId(event.target.value);
  }

  sort(event: any) {
    console.log('start sort');
    switch (event.target.value) {
      case 'Low': {
        console.log('sort low to high');
        this.products = this.products.sort((low, high) => low.price - high.price);
        break;
      }

      case 'High': {
        console.log('sort high to low');
        this.products = this.products.sort((low, high) => high.price - low.price);
        break;
      }

      case 'Name': {
        console.log('sort a to z');
        this.products = this.products.sort(function(low, high) {
          if (low.name < high.name) {
            return -1;
          } else if (low.name > high.name) {
            return 1;
          } else {
            return 0;
          }
        });
        break;
      }

      case 'NameZ': {
        this.products = this.products.sort(function(high, low) {
          if (low.name < high.name) {
            return -1;
          } else if (low.name > high.name) {
            return 1;
          } else {
            return 0;
          }
        });
        break;
      }

      default: {
        this.products = this.products.sort((low, high) => low.price - high.price);
        break;
      }

    }
    return this.products;

  }

  pageChanged(event: any) {
    this.config.currentPage = event;
  }

  onChange(event: any) {
    console.log('start filter');
    console.log(this.selectedColor);
    console.log(this.selectedRam);
    console.log(this.selectedMemory);
    console.log(this.selectedPrice);
    window.location.hash = 'products';

    this.displayProductList = [];
    for (var i = 0; i < this.selectedPrice.length; i++) {
      var lst = this.allProducts.filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to);
      for (var j = 0; j < lst.length; j++) {
        this.displayProductList.push(lst[j]);
      }
    }
    if (this.selectedRam.length != 0) {
      if (this.displayProductList.length == 0) {
        for (var i = 0; i < this.selectedRam.length; i++) {
          var lst = this.allProducts.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
        console.log(this.displayProductList);
      }
      var tempProductlst = this.displayProductList;
      this.displayProductList = [];
      for (var i = 0; i < this.selectedRam.length; i++) {
        //Filtering the same list which was filtered in brand list
        var lst = tempProductlst.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    }
    if (this.selectedColor.length != 0) {
      if (this.displayProductList.length == 0) {
        for (var i = 0; i < this.selectedColor.length; i++) {
          var lst = this.allProducts.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
        console.log(this.displayProductList);
      }
      var tempProductlst = this.displayProductList;
      this.displayProductList = [];
      for (var i = 0; i < this.selectedColor.length; i++) {
        //Filtering the same list which was filtered in brand list
        var lst = tempProductlst.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    }
    if (this.selectedMemory.length != 0) {
      if (this.displayProductList.length == 0) {
        for (var i = 0; i < this.selectedMemory.length; i++) {
          var lst = this.allProducts.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
        console.log(this.displayProductList);
      }
      var tempProductlst = this.displayProductList;
      this.displayProductList = [];
      for (var i = 0; i < this.selectedMemory.length; i++) {
        //Filtering the same list which was filtered in brand list
        var lst = tempProductlst.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    }

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0
      && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    }

    this.products = this.displayProductList;
  }

  onChangePrice(event: any) {
    console.log('start filter');
    console.log(this.selectedColor);
    console.log(this.selectedRam);
    console.log(this.selectedMemory);
    console.log(this.selectedPrice);

    //the first clicked in filter list
    if (this.displayProductList.length == 0) {
      this.displayProductList = [];
      for (var i = 0; i < this.selectedPrice.length; i++) {
        var lst = this.allProducts.filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    } else { //already have clicked in some option
      //the first clicked in price filter
      if (this.selectedPrice.length == 1) {
        var tempProductlst = this.displayProductList;
        this.displayProductList = [];
        for (var i = 0; i < this.selectedPrice.length; i++) {
          //Filtering the same list which was filtered in brand list
          var lst = tempProductlst.filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
      } else { //already have clicked in price filter
        //select all products that meet the condition of new chosen price and older conditions of ram, color, and mem
        //firstly, check whether exist any condition filter
        if (this.selectedRam.length == 0 && this.selectedColor.length == 0
          && this.selectedMemory.length == 0) {
          //select all products that meet the conditions of price filter
          this.displayProductList = [];
          for (var i = 0; i < this.selectedPrice.length; i++) {
            var lst = this.allProducts.filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to);
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        } else { //already have older conditions of ram, color, and mem
          const temp = this.displayProductList[0].productDetails[0];
          this.displayProductList = [];
          for (var i = 0; i < this.selectedPrice.length; i++) {
            var lst = this.allProducts.filter(x => x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to);
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
          if (this.selectedRam.length != 0) {
            var tempProductlst = this.displayProductList;
            this.displayProductList = [];
            for (var i = 0; i < this.selectedRam.length; i++) {
              //Filtering the same list which was filtered in brand list
              var lst = tempProductlst.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
          }
          if (this.selectedColor.length != 0) {
            var tempProductlst = this.displayProductList;
            this.displayProductList = [];
            for (var i = 0; i < this.selectedColor.length; i++) {
              //Filtering the same list which was filtered in brand list
              var lst = tempProductlst.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
          }
          if (this.selectedMemory.length != 0) {
            var tempProductlst = this.displayProductList;
            this.displayProductList = [];
            for (var i = 0; i < this.selectedMemory.length; i++) {
              //Filtering the same list which was filtered in brand list
              var lst = tempProductlst.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
          }
        }
      }
    }

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0
      && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    }

    this.products = this.displayProductList;


  }

  onChangeRam(event: any) {
    console.log('start filter');


    //the first clicked in filter list
    if (this.displayProductList.length == 0) {
      this.displayProductList = [];
      for (var i = 0; i < this.selectedRam.length; i++) {
        var lst = this.allProducts.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    } else { //already have clicked in some option
      //the first clicked in price filter
      if (this.selectedRam.length == 1) {
        var tempProductlst = this.displayProductList;
        this.displayProductList = [];
        for (var i = 0; i < this.selectedRam.length; i++) {
          //Filtering the same list which was filtered in brand list
          var lst = tempProductlst.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
      } else { //already have clicked in price filter
        //select all products that meet the condition of new chosen price and older conditions of ram, color, and mem
        //firstly, check whether exist any condition filter
        if (this.selectedPrice.length == 0 && this.selectedColor.length == 0
          && this.selectedMemory.length == 0) {
          //select all products that meet the conditions of price filter
          this.displayProductList = [];
          for (var i = 0; i < this.selectedRam.length; i++) {
            var lst = this.allProducts.filter(x => x.productDetails[0].ram == this.selectedRam[i].name);
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        } else { //already have older conditions of ram, color, and mem
          const temp = this.displayProductList[0].productDetails[0];
          this.displayProductList = [];
          for (var i = 0; i < this.selectedRam.length; i++) {
            var lst = this.allProducts.filter(x =>
              (x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to)
              && (x.productDetails[0].ram == this.selectedRam[i].name) && (x.productDetails[0].color == temp.color)
              && (x.productDetails[0].memmory == temp.memmory));
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        }
      }
    }

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0
      && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    }

    this.products = this.displayProductList;


  }

  onChangeColor(event: any) {
    console.log('start filter');

    //the first clicked in filter list
    if (this.displayProductList.length == 0) {
      this.displayProductList = [];
      for (var i = 0; i < this.selectedColor.length; i++) {
        var lst = this.allProducts.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    } else { //already have clicked in some option
      //the first clicked in price filter
      if (this.selectedColor.length == 1) {
        var tempProductlst = this.displayProductList;
        this.displayProductList = [];
        for (var i = 0; i < this.selectedColor.length; i++) {
          //Filtering the same list which was filtered in brand list
          var lst = tempProductlst.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
      } else { //already have clicked in price filter
        //select all products that meet the condition of new chosen price and older conditions of ram, color, and mem
        //firstly, check whether exist any condition filter
        if (this.selectedPrice.length == 0 && this.selectedRam.length == 0
          && this.selectedMemory.length == 0) {
          //select all products that meet the conditions of price filter
          this.displayProductList = [];
          for (var i = 0; i < this.selectedColor.length; i++) {
            var lst = this.allProducts.filter(x => x.productDetails[0].color == this.selectedColor[i].name);
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        } else { //already have older conditions of ram, color, and mem
          const temp = this.displayProductList[0].productDetails[0];
          this.displayProductList = [];
          for (var i = 0; i < this.selectedColor.length; i++) {
            var lst = this.allProducts.filter(x =>
              (x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to)
              && (x.productDetails[0].color == this.selectedColor[i].name) && (x.productDetails[0].ram == temp.ram)
              && (x.productDetails[0].memmory == temp.memmory));
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        }
      }
    }

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0
      && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    }

    this.products = this.displayProductList;


  }

  onChangeMem(event: any) {
    console.log('start filter');

    //the first clicked in filter list
    if (this.displayProductList.length == 0) {
      this.displayProductList = [];
      for (var i = 0; i < this.selectedMemory.length; i++) {
        var lst = this.allProducts.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
        for (var j = 0; j < lst.length; j++) {
          this.displayProductList.push(lst[j]);
        }
      }
    } else { //already have clicked in some option
      //the first clicked in price filter
      if (this.selectedMemory.length == 1) {
        var tempProductlst = this.displayProductList;
        this.displayProductList = [];
        for (var i = 0; i < this.selectedMemory.length; i++) {
          //Filtering the same list which was filtered in brand list
          var lst = tempProductlst.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
        }
      } else { //already have clicked in price filter
        //select all products that meet the condition of new chosen price and older conditions of ram, color, and mem
        //firstly, check whether exist any condition filter
        if (this.selectedPrice.length == 0 && this.selectedColor.length == 0
          && this.selectedRam.length == 0) {
          //select all products that meet the conditions of price filter
          this.displayProductList = [];
          for (var i = 0; i < this.selectedMemory.length; i++) {
            var lst = this.allProducts.filter(x => x.productDetails[0].memmory == this.selectedMemory[i].name);
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        } else { //already have older conditions of ram, color, and mem
          const temp = this.displayProductList[0].productDetails[0];
          this.displayProductList = [];
          for (var i = 0; i < this.selectedMemory.length; i++) {
            var lst = this.allProducts.filter(x =>
              (x.price > this.selectedPrice[i].from && x.price < this.selectedPrice[i].to)
              && (x.productDetails[0].memmory == this.selectedMemory[i].name) && (x.productDetails[0].color == temp.color)
              && (x.productDetails[0].ram == temp.ram));
            for (var j = 0; j < lst.length; j++) {
              this.displayProductList.push(lst[j]);
            }
          }
        }
      }
    }

    if (this.selectedRam.length == 0 && this.selectedColor.length == 0
      && this.selectedMemory.length == 0 && this.selectedPrice.length == 0) {
      this.displayProductList = this.allProducts;
    }

    this.products = this.displayProductList;


  }

  resetFilter() {
    this.getAllProduct();
    this.rams.forEach(s => {
      s.checked = false;
    });
    this.colors.forEach(s => {
      s.checked = false;
    });
    this.prices.forEach(s => {
      s.checked = false;
    });
    this.memmories.forEach(s => {
      s.checked = false;
    });
    window.location.reload();
  }

  applyFilter() {
    console.log(this.operatorArr);
    console.log(this.valueArr);
    this.displayProductList = [];
    this.operatorArr.forEach((item, index) => {
      console.log(item);
      if ((item != -999) && (this.valueArr[index] != '')) {
        switch (index) {
          case 0:
            if (item == 0) {
              console.log(123);
              var lst = this.allProducts.filter(x => x.productDetails[0].memmory.includes(this.valueArr[index].toString()));
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == -1) {
              var lst = this.allProducts.filter(x => {
                const mem = parseInt(x.productDetails[0].memmory.substring(0, 3));
                return (parseInt(this.valueArr[index]) > mem);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == 1) {
              var lst = this.allProducts.filter(x => {
                const mem = parseInt(x.productDetails[0].memmory.substring(0, 3));
                return (parseInt(this.valueArr[index]) < mem);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            break;
          case 1:
            if (item == 0) {
              var lst = this.allProducts.filter(x => x.productDetails[0].ram.includes(this.valueArr[index].toString()));
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == -1) {
              var lst = this.allProducts.filter(x => {
                const ram = parseInt(x.productDetails[0].ram.substring(0, 2));
                return (parseInt(this.valueArr[index]) > ram);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == 1) {
              var lst = this.allProducts.filter(x => {
                const ram = parseInt(x.productDetails[0].memmory.substring(0, 2));
                console.log(ram);
                return (parseInt(this.valueArr[index]) < ram);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            break;
          case 2:
            if (item == 0) {
              var lst = this.allProducts.filter(x => x.productDetails[0].pin.includes(this.valueArr[index].toString()));
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == -1) {
              var lst = this.allProducts.filter(x => {
                const pin = parseInt(x.productDetails[0].pin.substring(
                  x.productDetails[0].pin.length - 8, x.productDetails[0].pin.length -4));
                return (parseInt(this.valueArr[index]) > pin);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == 1) {
              var lst = this.allProducts.filter(x => {
                const pin = parseInt(x.productDetails[0].pin.substring(
                  x.productDetails[0].pin.length - 8, x.productDetails[0].pin.length -4));
                return (parseInt(this.valueArr[index]) < pin);
              });
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            break;
          case 3:
            if (item == 0) {
              var lst = this.allProducts.filter(x =>
                (x.properties.filter(y => (y.name == 'Product Weight')
                  && (y.description.includes(this.valueArr[index].toString())))).length > 0);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == -1) {
              var lst = this.allProducts.filter(x =>
                (x.properties.filter(y => {
                  const weight = parseInt(y.description.substring(0, 3));
                  return (y.name == 'Product Weight') && (parseInt(this.valueArr[index]) > weight);
                })).length > 0);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            if (item == 1) {
              var lst = this.allProducts.filter(x =>
                (x.properties.filter(y => {
                  const weight = parseInt(y.description.substring(0, 3));
                  return (y.name == 'Product Weight') && (parseInt(this.valueArr[index]) < weight);
                })).length > 0);
              for (var j = 0; j < lst.length; j++) {
                this.displayProductList.push(lst[j]);
              }
            }
            break;
        }
      } else if ((item !== -999) && (this.valueArr[index] === '')) {
        this.flagArr[index] = true;
      } else if (index == 4) {
        var lst = this.allProducts.filter(x =>
            (x.properties.filter(y => (y.name == 'SIM Card Slots')
              && (y.description.includes(item.toString())))).length > 0);
          for (var j = 0; j < lst.length; j++) {
            this.displayProductList.push(lst[j]);
          }
      }
    });
    this.products = Array.from(new Set(this.displayProductList));
  }
}
