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
        double[] xList = new double[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        double[] yList = new double[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
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

}