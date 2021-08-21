import { Provider } from "@angular/core";
import { Category } from "./category";

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
    importVoucherDetails!: any;
    wishlists!: any;
    reviews!: any;
}
