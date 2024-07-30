import { RouterModule, Routes } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { NgModule } from '@angular/core';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { OrderListComponent } from './order-list/order-list.component';
import { AddOrderComponent } from './add-order/add-order.component';

export const routes: Routes = [ { path: 'customers', component: CustomerListComponent }, 
                                { path: 'addCustomer', component: AddCustomerComponent }, 
                                { path: 'orders/:id', component: OrderListComponent }, 
                                { path: 'addOrder/:id', component: AddOrderComponent }];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
