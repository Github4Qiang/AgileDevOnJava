package part2.pattern.command;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class DevideCommand implements Command {
    private MyNumber number;
    private int devisor;

    public DevideCommand(MyNumber number, int devisor) {
        this.number = number;
        this.devisor = devisor;
    }

    public void execute() {
        number.setNumber(number.getNumber() / devisor);
    }

    public void undo() {
        number.setNumber(number.getNumber() * devisor);
    }
}
