export class Order {
    id!: number;
    firstNameOfReceiver!: string;
    lastNameOfReceiver!: string;
    emailOfReceiver!: string;
    addressOfReceiver!: string;
    phoneOfReceiver!: string;
    dateOfOrder!: Date;
    total!: number;
    note!: string;
    status!: number;
    userId!: number;
    employeeId!: number;
    listOrderDetails!: any;
    returnId!: any;
    invoice!: any;
    reviewId!: any;
}
