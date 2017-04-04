package refactor;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.junit.Test;

/**
 * Created by Polylanger on 2017/3/26.
 */
public class PrimeGeneratorTest extends TestCase {
    @Test
    public void testPrimes() throws Exception {
        int[] nullArray = PrimeGenerator.generatePrimes(0);
        assertEquals(nullArray.length, 0);

        int[] minArray = PrimeGenerator.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);

        int[] threeArray = PrimeGenerator.generatePrimes(3);
        assertEquals(threeArray.length, 2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);

        int[] centArray = PrimeGenerator.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }

    @Test
    public void testExhaustive() {
        for (int i = 2; i < 500; i++) {
            vertifyPrimeList(PrimeGenerator.generatePrimes(i));
        }
    }

    private void vertifyPrimeList(int[] list) {
        for (int j = 0; j < list.length; j++) {
            vertifyPrime(j);
        }
    }

    private void vertifyPrime(int n) {
        for (int factor = 2; factor < n; factor++) {
            assert (n % factor != 0);
        }
    }

    public PrimeGeneratorTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        TestRunner.main(new String[]{"refactor.PrimeGeneratorTest"});
    }

}