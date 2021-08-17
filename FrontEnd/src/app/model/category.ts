import { Abstract } from './abstract';
import { Product } from './product';

export class Category extends Abstract {
    id!: number;
    name!: string;
}
