import { Component } from '@angular/core';
import { Customer } from '../customer';
import { Order } from '../order';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { OrderService } from '../order-service.service';

@Component({
  selector: 'app-add-order',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './add-order.component.html',
  styleUrl: './add-order.component.css'
})
export class AddOrderComponent {
  order: Order = { id: 0, item: "", amount: 0, customer: new Customer }
  id!: string | number | null;


  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = params.get('id');

    });
  }



  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderService) {
  }

  onSubmit() {
    this.order.customer.id = Number(this.id);
    this.orderService.save(this.order).subscribe(result => this.gotoOrderList());
  }

  gotoOrderList() {
    this.router.navigate(['/orders/' + this.id]);
  }

}
