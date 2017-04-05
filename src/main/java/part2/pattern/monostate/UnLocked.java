package part2.pattern.monostate;

/**
 * Created by Polylanger on 2017/4/5.
 */
public class UnLocked extends Turnstile {

    public void pushCoin() {
        refund();
    }

    public void pass() {
        lock(true);
        state = LOCKED;
    }

}
