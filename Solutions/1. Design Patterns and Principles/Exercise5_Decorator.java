// Component interface
interface Notifier {
    void send(String message);
}

// Concrete base component
class EmailNotifier implements Notifier {
    private final String email;
    EmailNotifier(String email) { this.email = email; }

    public void send(String message) {
        System.out.println("Email to " + email + ": " + message);
    }
}

// Abstract decorator â€” wraps another Notifier
abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrapped;
    NotifierDecorator(Notifier wrapped) { this.wrapped = wrapped; }

    public void send(String message) { wrapped.send(message); }
}

// Concrete decorators add extra notification channels
class SMSNotifierDecorator extends NotifierDecorator {
    private final String phoneNumber;

    SMSNotifierDecorator(Notifier wrapped, String phoneNumber) {
        super(wrapped);
        this.phoneNumber = phoneNumber;
    }

    public void send(String message) {
        super.send(message);
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    private final String slackChannel;

    SlackNotifierDecorator(Notifier wrapped, String slackChannel) {
        super(wrapped);
        this.slackChannel = slackChannel;
    }

    public void send(String message) {
        super.send(message);
        System.out.println("Slack #" + slackChannel + ": " + message);
    }
}

public class Exercise5_Decorator {
    public static void main(String[] args) {
        // Email only
        Notifier emailOnly = new EmailNotifier("alice@example.com");

        // Email + SMS
        Notifier emailAndSms = new SMSNotifierDecorator(
                new EmailNotifier("bob@example.com"), "+1-555-0100");

        // Email + SMS + Slack
        Notifier allChannels = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier("ops@example.com"), "+1-555-0200"),
                "alerts");

        System.out.println("=== Email only ===");
        emailOnly.send("Server started");

        System.out.println("\n=== Email + SMS ===");
        emailAndSms.send("Disk usage 85%");

        System.out.println("\n=== Email + SMS + Slack ===");
        allChannels.send("CRITICAL: DB connection lost");
    }
}
