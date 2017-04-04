package part2.pattern.command;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class MinusCommand implements Command {
    private MyNumber number;
    private int minus;

    public MinusCommand(MyNumber number, int minus) {
        this.number = number;
        this.minus = minus;
    }

    public void execute() {
        number.setNumber(number.getNumber() - minus);
    }

    public void undo() {
        number.setNumber(number.getNumber() + minus);
    }
}
