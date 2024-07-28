import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer-service.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})
export class CustomerListComponent implements OnInit {

  customers: Customer[] =[];

  constructor(private customerService:CustomerService) {
  }

  ngOnInit() {
    this.customerService.findAll().subscribe(data => {
      this.customers = data;
    });
  }

}
