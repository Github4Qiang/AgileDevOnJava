package part2.pattern.command;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Polylanger on 2017/4/4.
 */
public class MyNumberTest extends TestCase {
    @Test
    public void testUndo() throws Exception {
        MyNumber number = new MyNumber(100);
        number.add(10).minus(20).multiple(2).devide(10);
        assertEquals(18, number.getNumber());
        number.undo();
        assertEquals(180, number.getNumber());
        number.undo();
        assertEquals(90, number.getNumber());
        number.undo();
        assertEquals(110, number.getNumber());
        number.undo();
        assertEquals(100, number.getNumber());
    }

}