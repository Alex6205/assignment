
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer-service.service';
import { NgFor } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgFor],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend';
  customers: Customer[] =[];

  constructor(private customerService:CustomerService) {
  }

  ngOnInit() {
    this.customerService.findAll().subscribe(data => {
      this.customers = data;
    });
  }
}


