package decideTest;
import decideProgram.LIC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LICTests {

    /**
     * LIC0
     * Test case where the distance between two points is greater than length1
     */
    @Test
    public void test_condition0_Valid() {
        LIC lic = new LIC();
        double[] xList = new double[]{1, 3};
        double[] yList = new double[]{1, 4};
        int length1 = 2;
        boolean result = lic.condition0(xList, yList,length1);
        assertTrue(result, "Expected true because the distance between two points is greater than length1");
    }

    /**
     * LIC0
     * Test case where the distance between two points is less than length1
     */
    @Test
    public void test_condition0_Invalid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] yList = new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int length1 = 10;
        boolean result = lic.condition0(xList, yList, length1);
        assertFalse(result, "Expected false because the distance is always less than length1");
    }

    /**
     * LIC0
     * Test case where the there are no data points
     */
    @Test
    public void test_condition0_No_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{};
        double[] yList = new double[]{};
        int length1 = 2;
        boolean result = lic.condition0(xList, yList, length1);
        assertFalse(result, "Expected false because there are no data points");
    }

    /**
     * LIC0
     * Test case where there is only one data point
     */
    @Test
    public void test_condition0_One_datapoint() {
        LIC lic = new LIC();
        double[] xList = new double[]{0};
        double[] yList = new double[]{0};
        int length1 = 2;
        boolean result = lic.condition0(xList, yList, length1);
        assertFalse(result, "Expected false because there is only one data point");
    }


    /**
     * LIC1
     * Test case where three consecutive points form a circle with radius > RADIUS1
     */
    @Test
    public void test_condition1_Valid() {
        LIC lic = new LIC();
        double[] xList = {0, 2, 1}; // Equilateral triangle (side length 2)
        double[] yList = {0, 0, Math.sqrt(3)}; // Circumradius ≈ 1.1547
        double RADIUS1 = 1.0;
        boolean result = lic.condition1(xList, yList, RADIUS1);
        assertTrue(result, "Expected true: circle radius exceeds RADIUS1");
    }


}