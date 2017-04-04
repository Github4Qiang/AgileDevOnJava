package part2.pattern.command;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class AddCommand implements Command {
    private MyNumber number;
    private int addend;

    public AddCommand(MyNumber number, int addend) {
        this.number = number;
        this.addend = addend;
    }

    public void execute() {
        number.setNumber(number.getNumber() + addend);
    }

    public void undo() {
        number.setNumber(number.getNumber() - addend);
    }
}
