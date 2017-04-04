package part2.pattern.activeobject;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class SleepCommandTest extends TestCase {

    public SleepCommandTest(String name) {
        super(name);
    }

    private boolean commandExecuted = false;

    @Test
    public void testSleep() {
        Command wakeup = new Command() {
            public void execute() {
                commandExecuted = true;
            }
        };
        ActiveObjectEngine engine = new ActiveObjectEngine();
        SleepCommand command = new SleepCommand(wakeup, engine, 1000);
        engine.addCommand(command);
        long start = System.currentTimeMillis();
        engine.run();
        long stop = System.currentTimeMillis();
        long sleepTime = stop - start;
        assertTrue("SleepTime " + sleepTime + " excepted = 1000", sleepTime == 1000L);
        assertTrue("Command Executed", commandExecuted);
    }

}