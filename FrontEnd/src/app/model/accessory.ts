import {Promotion} from './promotion';

export class Accessory {
  id!: number;
  name!: string;
  status!: number;
  image!: string;
  price!: string;
  accessoryCateId!: number;
  accessoryCateName!: string;
  promotions: Promotion[] = [];
}
