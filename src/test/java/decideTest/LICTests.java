package decideTest;
import decideProgram.LIC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LICTests {

    @Test
    public void testCondition0() {
        LIC lic = new LIC();
        assertTrue(lic.condition0(), "condition0 should return true");
    }

   /**
     * LIC 5: Valid test case.
     * Verifies that condition 5 returns true when X[j] - X[i] < 0.
     */
    @Test
    public void testCondition5_valid() {
        LIC lic = new LIC();

        // X[3] - X[2] = 4.0 - 2.0 = 2.0 (not satisfying the condition)
        double[] X = {1.0, 3.0, 2.0, 4.0};
        double[] Y = {5.0, 6.0, 7.0, 8.0};
        assertTrue(lic.condition5(X, Y), "Condition 5 should return true when X[j] - X[i] < 0");
    }

    /**
     * LIC 5: Invalid test case.
     * Verifies that condition 5 returns false when X[j] - X[i] is never < 0.
     */
    @Test
    public void testCondition5_invalid() {
        LIC lic = new LIC();

        // X[2] - X[1] = 3.0 - 2.0 = 1.0, X[3] - X[2] = 4.0 - 3.0 = 1.0
        double[] X2 = {1.0, 2.0, 3.0, 4.0};
        double[] Y2 = {5.0, 6.0, 7.0, 8.0};
        assertFalse(lic.condition5(X2, Y2), "Condition 5 should return false when no X[j] - X[i] < 0");
    }

    /**
 * Test case for Condition 5 in the LIC implementation.
 * This test ensures that an IllegalArgumentException is thrown when the X and Y arrays 
 * have different lengths.
 */
@Test
public void testCondition5_ArrayLengthMismatch() {
    LIC lic = new LIC();

    // X and Y arrays with different lengths (X has 3 elements, Y has 2)
    double[] X = {1.0, 2.0, 3.0};
    double[] Y = {5.0, 6.0};

    // Expecting an IllegalArgumentException to be thrown due to length mismatch
    assertThrows(IllegalArgumentException.class, () -> {
        lic.condition5(X, Y);
    }, "X and Y arrays must have the same length.");
}

}