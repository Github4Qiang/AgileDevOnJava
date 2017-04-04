package part2.pattern.activeobject;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class DelayTyper implements Command {

    private long itsDelay;
    private char itsChar;
    private static ActiveObjectEngine engine = new ActiveObjectEngine();
    private static boolean stop = false;

    public DelayTyper(long itsDelay, char itsChar) {
        this.itsDelay = itsDelay;
        this.itsChar = itsChar;
    }


    public void execute() {
        System.out.print(itsChar);
        if (!stop) {
            engine.addCommand(new SleepCommand(this, engine, itsDelay));
        }
    }

    public static void main(String[] args) {
        engine.addCommand(new DelayTyper(100, '1'));
        engine.addCommand(new DelayTyper(300, '3'));
        engine.addCommand(new DelayTyper(500, '5'));
        engine.addCommand(new DelayTyper(700, '7'));
        engine.addCommand(new SleepCommand(new Command() {
            public void execute() {
                stop = true;
            }
        }, engine, 20000));

        engine.run();
    }
}
