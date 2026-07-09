import java.util.ArrayDeque;
import java.util.Deque;

// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class Light {
    private final String location;

    Light(String location) { this.location = location; }

    public void turnOn()  { System.out.println(location + " light: ON");  }
    public void turnOff() { System.out.println(location + " light: OFF"); }
}

// Concrete commands
class LightOnCommand implements Command {
    private final Light light;
    LightOnCommand(Light light) { this.light = light; }

    public void execute() { light.turnOn();  }
    public void undo()    { light.turnOff(); }
}

class LightOffCommand implements Command {
    private final Light light;
    LightOffCommand(Light light) { this.light = light; }

    public void execute() { light.turnOff(); }
    public void undo()    { light.turnOn();  }
}

// Invoker with undo history
class RemoteControl {
    private Command lastCommand;
    private final Deque<Command> history = new ArrayDeque<>();

    public void pressButton(Command command) {
        command.execute();
        history.push(command);
        lastCommand = command;
    }

    public void pressUndo() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        Command cmd = history.pop();
        System.out.print("Undo -> ");
        cmd.undo();
    }
}

public class Exercise9_Command {
    public static void main(String[] args) {
        Light livingRoom = new Light("Living room");
        Light bedroom    = new Light("Bedroom");

        Command livingOn  = new LightOnCommand(livingRoom);
        Command livingOff = new LightOffCommand(livingRoom);
        Command bedroomOn = new LightOnCommand(bedroom);

        RemoteControl remote = new RemoteControl();

        remote.pressButton(livingOn);
        remote.pressButton(bedroomOn);
        remote.pressButton(livingOff);

        System.out.println("\n--- Undo last 2 ---");
        remote.pressUndo();
        remote.pressUndo();
    }
}
