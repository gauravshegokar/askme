import { PaymentInterface } from './PaymentInterface'
import { PaypalService } from './paypalService'

export class Proxy implements PaymentInterface {

    private paypal: PaypalService;

    constructor() {
        this.paypal = new PaypalService()
    }

    performTransaction(amount) {
        if (this.checkMode() == "development") {
            console.log("fake payment performed")
        }

        if (this.checkMode() == "production") {
            this.paypal.performTransaction(amount)
            console.log("Performed Payment at Paypal")
        }
        
    }

    checkMode() {
        // return "development"
        return "production"
    }
}