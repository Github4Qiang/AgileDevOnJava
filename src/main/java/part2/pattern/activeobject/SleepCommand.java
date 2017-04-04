package part2.pattern.activeobject;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class SleepCommand implements Command {

    private Command wakeupCommand = null;
    private ActiveObjectEngine engine = null;
    private long sleepTime = 0;
    private long startTime = 0;
    private boolean started = false;

    public SleepCommand(Command wakeupCommand, ActiveObjectEngine engine, long sleepTime) {
        this.wakeupCommand = wakeupCommand;
        this.engine = engine;
        this.sleepTime = sleepTime;
    }

    public void execute() {
        long currentTime = System.currentTimeMillis();
        if (!started) {
            started = true;
            startTime = currentTime;
            engine.addCommand(this);
        } else if ((currentTime - startTime) < sleepTime) {
            engine.addCommand(this);
        } else {
            engine.addCommand(wakeupCommand);
        }
    }
}
