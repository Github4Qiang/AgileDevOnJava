package part2.pattern.activeobject;

import java.util.LinkedList;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class ActiveObjectEngine {
    LinkedList<Command> itsCommands = new LinkedList<Command>();

    public void addCommand(Command c) {
        itsCommands.add(c);
    }

    public void run() {
        while (!itsCommands.isEmpty()) {
            Command c = itsCommands.removeFirst();
            c.execute();
        }
    }
}
