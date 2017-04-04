package refactor;

/**
 * Created by Polylanger on 2017/3/26.
 */
public class PrimeGenerator {

    private static boolean[] crossOut;
    private static int[] result;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue >= 2) { // The only valid case
            uncrossIntegersUpto(maxValue);
            crossOutMultiples();
            putUncrossedIntegerIntoResult();
            return result;
        } else // maxValue < 2
            return new int[0]; // return null array if bad input
    }

    private static void putUncrossedIntegerIntoResult() {
        result = new int[numberOfUncrossedIntegers()];
        // move the result into the result
        for (int i = 2, j = 0; i < crossOut.length; i++) {
            if (notCrossed(i)) result[j++] = i; // if prime
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < crossOut.length; i++) {
            if (notCrossed(i)) count++; // bump count
        }
        return count;
    }

    private static boolean notCrossed(int i) {
        return !crossOut[i];
    }

    private static void crossOutMultiples() {
        int maxPrimeFactor = calcMaxPrimeFactor();
        for (int i = 2; i <= maxPrimeFactor; i++) {
            if (notCrossed(i))
                crossOutMultipleOf(i);
        }
    }

    private static int calcMaxPrimeFactor() {
        double maxPrimeFactor = Math.sqrt(crossOut.length);
        return (int) maxPrimeFactor;
    }

    private static void crossOutMultipleOf(int i) {
        for (int multiple = 2 * i; multiple < crossOut.length; multiple += i) {
            crossOut[multiple] = true; // mutiple is not prime
        }
    }

    private static void uncrossIntegersUpto(int maxValue) {
        crossOut = new boolean[maxValue + 1];
        for (int i = 2; i < crossOut.length; i++) {
            crossOut[i] = false;
        }
    }

}
