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
        boolean result = lic.condition0(xList, yList, length1);
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
     * Test case where there are no data points
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

    /**
     * LIC1
     * Test case where all three-point circles have radius ≤ RADIUS1
     */
    @Test
    public void test_condition1_Invalid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 0.5}; // Smaller equilateral triangle
        double[] yList = {0, 0, Math.sqrt(3) / 2}; // Circumradius ≈ 0.577
        double RADIUS1 = 1.0;
        boolean result = lic.condition1(xList, yList, RADIUS1);
        assertFalse(result, "Expected false: all circles fit within RADIUS1");
    }

    /**
     * LIC1
     * Test case with collinear points (radius = farthest distance/2)
     */
    @Test
    public void test_condition1_Collinear() {
        LIC lic = new LIC();
        double[] xList = {0, 2, 4}; // Collinear points (distance 4 between first/last)
        double[] yList = {0, 0, 0};
        double RADIUS1 = 1.9; // Required radius = 2.0 (4/2)
        boolean result = lic.condition1(xList, yList, RADIUS1);
        assertTrue(result, "Expected true: required radius (2.0) > RADIUS1 (1.9)");
    }

    /**
     * LIC1
     * Test case with exactly 3 points and radius = RADIUS1
     */
    @Test
    public void test_condition1_ExactRadius() {
        LIC lic = new LIC();
        double[] xList = {0, 2, 4}; // Collinear points
        double[] yList = {0, 0, 0};
        double RADIUS1 = 2.0; // Exactly matches required radius
        boolean result = lic.condition1(xList, yList, RADIUS1);
        assertFalse(result, "Expected false: radius equals RADIUS1");
    }

    /**
     * LIC3
     * Test case where the area of the triangle formed by three points is greater than AREA1
     */
    @Test
    public void test_condition3_Valid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 3, 0};
        double[] yList = new double[]{0, 0, 4};
        double area1 = 3.0;
        boolean result = lic.condition3(xList, yList, area1);
        assertTrue(result, "Expected true because the area of the triangle formed by the points is greater than AREA1");
    }

    /**
     * LIC3
     * Test case where the area of the triangle formed by three points is smaller than AREA1
     */
    @Test
    public void test_condition3_InValid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 0};
        double[] yList = new double[]{0, 0, 1};
        double area1 = 1.0;
        boolean result = lic.condition3(xList, yList, area1);
        assertFalse(result, "Expected false because the area of the triangle formed by the points is less than or equal to AREA1");
    }

    /**
     * LIC3
     * Test case where there are zero data points
     */
    @Test
    public void test_condition3_Zero_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{}; // Zero points
        double[] yList = new double[]{};
        double area1 = 3.0;
        boolean result = lic.condition3(xList, yList, area1);
        assertFalse(result, "Expected false because there are no data points to form a triangle");
    }

    /**
     * LIC3
     * Test case where there are 2 data points
     */
    @Test
    public void test_condition3_Two_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1};  // Less than three points
        double[] yList = new double[]{0, 1};
        double area1 = 1.0;
        boolean result = lic.condition3(xList, yList, area1);
        assertFalse(result, "Expected false because there are fewer than three data points to form a triangle");
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

    /**
     * LIC 7:
     * Test case where KPTS is less than 1
     */
    @Test
    public void test_condition7_KPTS_less_than_1() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 2};
        double[] yList = new double[]{0, 1, 2};
        int numPoints = 3;
        int kPts = 0;
        int length1 = 1;
        assertThrows(IllegalArgumentException.class, () -> lic.condition7(xList, yList, numPoints, kPts, length1), "Expected IllegalArgumentException because KPTS is less than 1");
    }

    /**
     * LIC 12:
     * Test case where there exists at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1 and one set of data points separated by exactly KPTS consecutive intervening points that are a distance less than the length2
     */
    @Test
    public void test_condition12_Valid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 4, 5};
        double[] yList = {0, 0, 0, 0};
        int kPts = 1;
        int length1 = 2;
        int length2 = 5;
        int numPoints = 4;
        assertTrue(lic.condition12(xList, yList, numPoints, kPts, length1, length2), "Expected true because there exists at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1 and one set of data points separated by exactly KPTS consecutive intervening points that are a distance less than the length2");
    }

    /**
     * LIC 12:
     * Test case where there doesn't exist at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1 and one set of data points separated by exactly KPTS consecutive intervening points that are a distance less than the length2
     */
    @Test
    public void test_condition12_Invalid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3};
        double[] yList = {0, 0, 0, 0};
        int kPts = 1;
        int length1 = 2;
        int length2 = 2;
        int numPoints = 4;
        assertFalse(lic.condition12(xList, yList, numPoints, kPts, length1, length2), "Expected false because there doesn't exist at least one set of two data points separated by exactly KPTS consecutive intervening points that are a distance greater than the length1 and one set of data points separated by exactly KPTS consecutive intervening points that are a distance less than the length2");
    }

    /**
     * LIC 12:
     * Test case where there are less than 3 data points
     */
    @Test
    public void test_condition12_Less_than_3_datapoints() {
        LIC lic = new LIC();
        double[] xList = {0, 1};
        double[] yList = {0, 0};
        int kPts = 1;
        int length1 = 2;
        int length2 = 5;
        int numPoints = 2;
        assertFalse(lic.condition12(xList, yList, numPoints, kPts, length1, length2), "Expected false because there are less than 3 data points");
    }
}

