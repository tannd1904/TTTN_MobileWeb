import { Provider } from "@angular/core";
import { Category } from "./category";
import { ImportDetail } from "./import-detail";
import { ProductDetail } from "./product-detail";

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
    reviews!: any;
    productDetails: ProductDetail[] = [];
}
