// Thread-safe Singleton via double-checked locking
class Logger {
    private static volatile Logger instance;

    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

public class Exercise1_Singleton {
    public static void main(String[] args) {
        Logger log1 = Logger.getInstance();
        Logger log2 = Logger.getInstance();
        Logger log3 = Logger.getInstance();

        log1.log("Application started");
        log2.log("Processing request");
        log3.log("Application stopped");

        System.out.println("\nSame instance? " + (log1 == log2 && log2 == log3));
    }
}
