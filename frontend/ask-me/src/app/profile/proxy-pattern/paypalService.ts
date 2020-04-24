import { PaymentInterface } from './PaymentInterface';

export class PaypalService implements PaymentInterface {

    performTransaction(amount: number) {
        console.log("payment using paypal successful for amount " + amount)
    }

}