package part2.pattern.monostate;

/**
 * Created by Polylanger on 2017/4/5.
 */
public class Turnstile {

    private static boolean locked = true;

    private static boolean alarming = false;

    private static int coins = 0;

    private static int refunds = 0;
    protected final static Turnstile LOCKED = new Locked();
    protected final static Turnstile UNLOCKED = new UnLocked();
    protected static Turnstile state = LOCKED;

    public void reset() {
        lock(true);
        alarm(false);
        coins = 0;
        refunds = 0;
        state = LOCKED;
    }

    public void pushCoin() {
        state.pushCoin();
    }

    public static boolean isAlarming() {
        return alarming;
    }

    public static boolean isLocked() {
        return locked;
    }

    public void pass() {
        state.pass();
    }

    protected void lock(boolean shouldLock) {
        locked = shouldLock;
    }

    protected void alarm(boolean shouldAlarm) {
        alarming = shouldAlarm;
    }

    public int getCoins() {
        return coins;
    }

    public int getRefunds() {
        return refunds;
    }

    public void deposit() {
        coins++;
    }

    public void refund() {
        refunds++;
    }

}
