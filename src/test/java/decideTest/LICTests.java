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
     * LIC 7:
     * Test case where there exists at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1
     */
    @Test
    public void test_condition7_Valid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 4};
        double[] yList = new double[]{0, 1, 5};
        int numPoints = 3;
        int kPts = 1;
        int length1 = 2;
        boolean result = lic.condition7(xList, yList, numPoints, kPts, length1);
        assertTrue(result, "Expected true because there exists at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1");
    }

    /**
     * LIC 7:
     * Test case where there doesn't exists one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1
     */
    @Test
    public void test_condition7_Invalid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 2};
        double[] yList = new double[]{0, 1, 2};
        int numPoints = 3;
        int kPts = 2;
        int length1 = 1;
        boolean result = lic.condition7(xList, yList, numPoints, kPts, length1);
        assertFalse(result, "Expected false because there doesn't exists one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1");
    }

    /**
     * LIC 7:
     * Test case where there are less than 3 data points
     */
    @Test
    public void test_condition7_Less_than_3_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1};
        double[] yList = new double[]{0, 1};
        int numPoints = 2;
        int kPts = 1;
        int length1 = 1;
        boolean result = lic.condition7(xList, yList, numPoints, kPts, length1);
        assertFalse(result, "Expected false because there are less than 3 data points");
    }

}