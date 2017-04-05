package part2;

import junit.textui.TestRunner;


/**
 * Created by Polylanger on 2017/3/27.
 */
public class Runner {

    private static final String MY_NUMBER_TEST = "part2.pattern.command.MyNumberTest";
    private static final String SLEEP_COMMAND_TEST = "part2.pattern.activeobject.SleepCommandTest";
    public static final String QUICK_ENTRY_MEDIATOR = "part2.pattern.mediator.QuickEntryMediatorTest";
    public static final String SINGLETON = "part2.pattern.singleton.SingletonTest";
    public static final String TURNSTILE = "part2.pattern.monostate.TurnstileTest";

    public static void main(String[] args) {
        TestRunner.main(new String[]{TURNSTILE});
    }

}
