package part2.pattern.monostate;

/**
 * Created by Polylanger on 2017/4/5.
 */
public class Locked extends Turnstile {

    public void pushCoin(){
        state = UNLOCKED;
        lock(false);
        alarm(false);
        deposit();
    }

    public void pass(){
        alarm(true);
    }

}
