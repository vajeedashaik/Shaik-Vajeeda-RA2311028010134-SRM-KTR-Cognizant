import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String stockSymbol, double price);
}

// Subject interface
interface Stock {
    void registerObserver(Observer o);
    void deregisterObserver(Observer o);
    void notifyObservers();
}

// Concrete subject
class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public void setStockPrice(String symbol, double price) {
        System.out.printf("%nStockMarket: %s price changed to $%.2f%n", symbol, price);
        this.symbol = symbol;
        this.price = price;
        notifyObservers();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("Registered: " + o.getClass().getSimpleName());
    }

    public void deregisterObserver(Observer o) {
        observers.remove(o);
        System.out.println("Deregistered: " + o.getClass().getSimpleName());
    }

    public void notifyObservers() {
        for (Observer o : observers) o.update(symbol, price);
    }
}

// Concrete observers
class MobileApp implements Observer {
    private final String userId;
    MobileApp(String userId) { this.userId = userId; }

    public void update(String symbol, double price) {
        System.out.printf("  [MobileApp:%s] Alert: %s is now $%.2f%n", userId, symbol, price);
    }
}

class WebApp implements Observer {
    public void update(String symbol, double price) {
        System.out.printf("  [WebApp] Dashboard updated: %s = $%.2f%n", symbol, price);
    }
}

public class Exercise7_Observer {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobile1 = new MobileApp("alice");
        Observer mobile2 = new MobileApp("bob");
        Observer web     = new WebApp();

        market.registerObserver(mobile1);
        market.registerObserver(mobile2);
        market.registerObserver(web);

        market.setStockPrice("AAPL", 182.50);
        market.setStockPrice("GOOG", 140.30);

        market.deregisterObserver(mobile2);
        market.setStockPrice("AAPL", 185.00);
    }
}
