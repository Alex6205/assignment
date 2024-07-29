import { RouterModule, Routes } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { NgModule } from '@angular/core';
import { AddCustomerComponent } from './add-customer/add-customer.component';

export const routes: Routes = [{ path: 'customers', component: CustomerListComponent }, { path: 'addCustomer', component: AddCustomerComponent }];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
