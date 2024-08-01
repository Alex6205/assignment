import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private ordersUrl: string;

  constructor(private http: HttpClient) {
    this.ordersUrl = 'http://localhost:4204/backend/order/restapi/orders';
  }

  public findById(id: string | null): Observable<Order[]> {
    return this.http.get<Order[]>(this.ordersUrl + "/customer/" + id);
  }
  public findAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.ordersUrl);
  }
  public save(order: Order) {
    return this.http.post<Order>(this.ordersUrl, order);
  }
}
