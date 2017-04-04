package part2.pattern.command;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class MultipleCommand implements Command {
    private MyNumber number;
    private int multiplier;

    public MultipleCommand(MyNumber number, int multiplier) {
        this.number = number;
        this.multiplier = multiplier;
    }

    public void execute() {
        number.setNumber(number.getNumber() * multiplier);
    }

    public void undo() {
        number.setNumber(number.getNumber() / multiplier);
    }
}
