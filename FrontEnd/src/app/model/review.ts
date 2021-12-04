import {Abstract} from './abstract';
import {User} from './user';

export class Review extends Abstract {
  id!: number;
  content!: number;
  image!: string;
  orderId!: number;
  productId!: number;
  rating!: number;
  userId!: number;
  user!: User;
}
