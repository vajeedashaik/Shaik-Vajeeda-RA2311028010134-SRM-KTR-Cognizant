// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String cardHolder;

    CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    public void pay(double amount) {
        System.out.printf("Credit Card [%s, holder: %s]: paid $%.2f%n",
                maskCard(cardNumber), cardHolder, amount);
    }

    private String maskCard(String num) {
        return "**** **** **** " + num.substring(num.length() - 4);
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;

    PayPalPayment(String email) { this.email = email; }

    public void pay(double amount) {
        System.out.printf("PayPal [%s]: paid $%.2f%n", email, amount);
    }
}

class CryptoPayment implements PaymentStrategy {
    private final String walletAddress;

    CryptoPayment(String walletAddress) { this.walletAddress = walletAddress; }

    public void pay(double amount) {
        System.out.printf("Crypto [wallet: %s]: paid $%.2f worth of BTC%n", walletAddress, amount);
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) throw new IllegalStateException("No payment strategy set");
        strategy.pay(amount);
    }
}

public class Exercise8_Strategy {
    public static void main(String[] args) {
        PaymentContext ctx = new PaymentContext();

        ctx.setStrategy(new CreditCardPayment("4111111111111234", "Alice Smith"));
        ctx.executePayment(199.99);

        ctx.setStrategy(new PayPalPayment("alice@example.com"));
        ctx.executePayment(49.00);

        ctx.setStrategy(new CryptoPayment("1A2b3C4d5E6f7G8h"));
        ctx.executePayment(999.00);
    }
}
