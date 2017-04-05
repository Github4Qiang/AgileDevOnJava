package part2.pattern.monostate;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Polylanger on 2017/4/5.
 */
public class TurnstileTest extends TestCase {

    private Turnstile turnstile = new Turnstile();
    private Turnstile turnstile1 = new Turnstile();


    public TurnstileTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        turnstile.reset();
        super.setUp();
    }

    public void testInit() throws Exception {
        assertTrue(Turnstile.isLocked());
        assertTrue(!Turnstile.isAlarming());
    }

    public void testCoin() throws Exception {
        turnstile.pushCoin();
        assertTrue(!Turnstile.isLocked());
        assertTrue(!Turnstile.isAlarming());
        assertEquals(1, turnstile1.getCoins());
    }

    public void testCoinAndPass() throws Exception {
        turnstile.pushCoin();
        turnstile.pass();

        assertEquals(1, turnstile1.getCoins());
        assertTrue(Turnstile.isLocked());
        assertTrue(!Turnstile.isAlarming());
    }

    public void testTwoCoins() throws Exception {
        turnstile.pushCoin();
        turnstile.pushCoin();

        assertEquals(1, turnstile1.getRefunds());
        assertEquals(1, turnstile1.getCoins());
        assertTrue(!Turnstile.isLocked());
        assertTrue(!Turnstile.isAlarming());
    }

    public void testPass() throws Exception {
        turnstile.pass();

        assertTrue(Turnstile.isAlarming());
        assertTrue(Turnstile.isLocked());
    }

    public void testTwoOperation() throws Exception {
        turnstile.pushCoin();
        turnstile.pass();
        turnstile.pushCoin();

        assertTrue(!Turnstile.isLocked());
        assertEquals(2, turnstile.getCoins());
        turnstile.pass();
        assertTrue(Turnstile.isLocked());
    }

    public void testCancelAlarm() throws Exception {
        turnstile.pass();
        turnstile.pushCoin();
        turnstile.pass();
        assertTrue(!Turnstile.isAlarming());
        assertTrue(Turnstile.isLocked());
    }
}