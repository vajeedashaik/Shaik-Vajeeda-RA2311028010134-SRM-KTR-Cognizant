import java.util.ArrayList;
import java.util.List;

// Observer interface
interface StockObserver {
    void update(String stockSymbol, double price);
}

// Subject interface
interface Stock {
    void registerObserver(StockObserver o);
    void deregisterObserver(StockObserver o);
    void notifyObservers();
}

// Concrete subject
class StockMarket implements Stock {
    private final List<StockObserver> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public void setStockPrice(String symbol, double price) {
        System.out.printf("%nStockMarket: %s price changed to $%.2f%n", symbol, price);
        this.symbol = symbol;
        this.price = price;
        notifyObservers();
    }

    public void registerObserver(StockObserver o) {
        observers.add(o);
        System.out.println("Registered: " + o.getClass().getSimpleName());
    }

    public void deregisterObserver(StockObserver o) {
        observers.remove(o);
        System.out.println("Deregistered: " + o.getClass().getSimpleName());
    }

    public void notifyObservers() {
        for (StockObserver o : observers) o.update(symbol, price);
    }
}

// Concrete observers
class MobileApp implements StockObserver {
    private final String userId;
    MobileApp(String userId) { this.userId = userId; }

    public void update(String symbol, double price) {
        System.out.printf("  [MobileApp:%s] Alert: %s is now $%.2f%n", userId, symbol, price);
    }
}

class WebApp implements StockObserver {
    public void update(String symbol, double price) {
        System.out.printf("  [WebApp] Dashboard updated: %s = $%.2f%n", symbol, price);
    }
}

public class Exercise7_Observer {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        StockObserver mobile1 = new MobileApp("alice");
        StockObserver mobile2 = new MobileApp("bob");
        StockObserver web     = new WebApp();

        market.registerObserver(mobile1);
        market.registerObserver(mobile2);
        market.registerObserver(web);

        market.setStockPrice("AAPL", 182.50);
        market.setStockPrice("GOOG", 140.30);

        market.deregisterObserver(mobile2);
        market.setStockPrice("AAPL", 185.00);
    }
}
