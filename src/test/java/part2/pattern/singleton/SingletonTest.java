package part2.pattern.singleton;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Created by Polylanger on 2017/4/5.
 */
public class SingletonTest extends TestCase {
    @Test
    public void testGetInstance() throws Exception {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        assertSame(s1, s2);
    }

    public void testNoPublicConstructors() throws ClassNotFoundException {
        Class singleton = Class.forName("part2.pattern.singleton.Singleton");
        Constructor[] constructors = singleton.getConstructors();
        assertEquals("Singleton has public constructors", 0, constructors.length);
    }
}