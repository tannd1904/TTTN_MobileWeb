import { Abstract } from './abstract';
import { Product } from './product';
import { Room } from './room';

export class Category extends Abstract {
    id!: number;
    name!: string;
}
