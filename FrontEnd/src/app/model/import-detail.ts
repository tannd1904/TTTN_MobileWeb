import { ProductDetail } from "./product-detail";

export class ImportDetail {
    id!: number;
    importVoucherId!: number;
    productId!: number;
    productName!: string;
    quantity!: number;
    price!: number;
    productDetails!: ProductDetail;
}
