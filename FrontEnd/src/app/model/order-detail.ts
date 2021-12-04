import {Product} from './product';

export class OrderDetail {
  id!: number;
  productDetailId!: number;
  productDetail!: any;
  quantity!: number;
  orderId!: number;
  productId!: number;
  product!: Product;
}
