import {ImportDetail} from './import-detail';
import {ProductDetail} from './product-detail';
import {Review} from './review';
import {Property} from './property';

export class Product {
  id!: number;
  name!: string;
  status!: number;
  price!: number;
  type!: string;
  image!: any;
  imageFile!: any;
  description!: string;
  categoryId!: number;
  categoryName!: string;
  importVoucherDetails: ImportDetail[] = [];
  wishlists!: any;
  reviews: Review[] = [];
  productDetails: ProductDetail[] = [];
  promotion!: any;
  quantity!: number;
  madeIn!: string;
  properties: Property[] = [];
}
