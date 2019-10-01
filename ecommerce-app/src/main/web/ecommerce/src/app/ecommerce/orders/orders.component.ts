import {Component, OnInit} from '@angular/core';
import {ProductOrders} from "../models/product-orders.model";
import {Subscription} from "rxjs/internal/Subscription";
import {EcommerceService} from "../services/EcommerceService";
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
    orders: ProductOrders;
    total: number;
    totalStr: string;
    paid: boolean;
    successMessage:string
    sub: Subscription;
    payPalConfig?: IPayPalConfig;

    constructor(private ecommerceService: EcommerceService) {
        this.orders = this.ecommerceService.ProductOrders;
    }

    ngOnInit() {
        this.paid = false;
        this.successMessage='';
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.orders = this.ecommerceService.ProductOrders;
        });
        this.loadTotal();
        this.initConfig();
    }

    loadTotal() {
        this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
            this.total = this.ecommerceService.Total;
            this.totalStr = this.total+'';
        });
    }
    
    private initConfig(): void {
      this.payPalConfig = {
      currency: 'USD',
      clientId: 'sb',
      createOrderOnClient: (data) => <ICreateOrderRequest>{
        intent: 'CAPTURE',
        purchase_units: [
          {
            amount: {
              currency_code: 'USD',
              value: this.totalStr,
              breakdown: {
                item_total: {
                  currency_code: 'USD',
                  value: this.totalStr
                }
              }
            },
            items: [
              {
                name: 'Ecommerce Products',
                quantity: '1',
                category: 'DIGITAL_GOODS',
                unit_amount: {
                  currency_code: 'USD',
                  value: this.totalStr,
                },
              }
            ]
          }
        ]
      },
      advanced: {
        commit: 'true'
      },
      style: {
        label: 'paypal',
        layout: 'vertical'
      },
      onApprove: (data, actions) => {
        console.log('onApprove - transaction was approved, but not authorized', data, actions);
        actions.order.get().then(details => {
          console.log('onApprove - you can get full order details inside onApprove: ', details);
        });
      },
      onClientAuthorization: (data) => {
        
        this.successMessage = "Your order is placed successfully.";
        console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
        this.ecommerceService.saveOrder(this.orders).subscribe();
      },
      onCancel: (data, actions) => {
        console.log('OnCancel', data, actions);
      },
      onError: err => {
        console.log('OnError', err);
      },
      onClick: (data, actions) => {
        console.log('onClick', data, actions);
      },
    };
    }
}
