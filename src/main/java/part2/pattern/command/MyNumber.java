package part2.pattern.command;

import java.util.ArrayList;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class MyNumber {

    private ArrayList<Command> commands = new ArrayList<Command>();

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MyNumber(int number) {
        this.number = number;
    }

    public MyNumber add(int number) {
        AddCommand command = new AddCommand(this, number);
        command.execute();
        commands.add(command);
        return this;
    }

    public MyNumber minus(int number) {
        MinusCommand command = new MinusCommand(this, number);
        command.execute();
        commands.add(command);
        return this;
    }

    public MyNumber multiple(int number) {
        MultipleCommand command = new MultipleCommand(this, number);
        command.execute();
        commands.add(command);
        return this;
    }

    public MyNumber devide(int number) {
        DevideCommand command = new DevideCommand(this, number);
        command.execute();
        commands.add(command);
        return this;
    }

    public MyNumber undo() {
        commands.remove(commands.size() - 1).undo();
        return this;
    }
}
