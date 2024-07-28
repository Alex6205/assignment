import { Customer } from "./customer";

export class Order {
    id: number=0;
    item: string="";
    amount: number=0;
    customer: Customer = new Customer;

}
