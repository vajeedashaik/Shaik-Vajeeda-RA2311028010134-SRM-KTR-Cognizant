// Target interface our system expects
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee 1: PayPal with its own API
class PayPalGateway {
    public void makePayment(double amount) {
        System.out.printf("PayPal: processing payment of $%.2f%n", amount);
    }
}

// Adaptee 2: Stripe with its own API
class StripeGateway {
    public void charge(double amountInCents) {
        System.out.printf("Stripe: charging %.0f cents ($%.2f)%n",
                amountInCents, amountInCents / 100);
    }
}

// Adaptee 3: Square with its own API
class SquareGateway {
    public void submitTransaction(String amountStr) {
        System.out.println("Square: submitting transaction of " + amountStr);
    }
}

// Adapters translate PaymentProcessor calls to gateway-specific calls
class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway gateway;
    PayPalAdapter(PayPalGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) { gateway.makePayment(amount); }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeGateway gateway;
    StripeAdapter(StripeGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) { gateway.charge(amount * 100); }
}

class SquareAdapter implements PaymentProcessor {
    private final SquareGateway gateway;
    SquareAdapter(SquareGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) {
        gateway.submitTransaction(String.format("$%.2f", amount));
    }
}

public class Exercise4_Adapter {
    static void checkout(PaymentProcessor processor, double amount) {
        System.out.print("Checkout -> ");
        processor.processPayment(amount);
    }

    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        PaymentProcessor square = new SquareAdapter(new SquareGateway());

        checkout(paypal, 49.99);
        checkout(stripe, 149.00);
        checkout(square, 29.50);
    }
}
