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
     * LIC2
     * Test case with too few points
     */
    @Test
    public void test_condition2_NotEnoughPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 1};
        double[] yList = {0, 1};
        int numPoints = 2;
        double epsilon = 0.5;

        boolean result = lic.condition2(xList, yList, numPoints, epsilon);
        assertFalse(result, "Expected false because there are fewer than 3 points.");
    }

    /**
     * LIC2
     * Test case where the angle is < PI - EPSILON
     */
    @Test
    public void test_condition2_AngleLessThan() {
        LIC lic = new LIC();
        double[] xList = {0, 0, 1}; // Right turn, sharp angle
        double[] yList = {0, 1, 0}; 
        int numPoints = 3;
        double epsilon = 0.5; // PI - 0.5 should be a relatively large angle
        
        boolean result = lic.condition2(xList, yList, numPoints, epsilon);
        assertTrue(result, "Expected true because the angle is less than PI - EPSILON.");
    }
    /**
     * LIC2
     * Test case where the angle is > PI + EPSILON
     */
    @Test
    public void test_condition2_AngleMoreThan() {
        LIC lic = new LIC();
        double[] xList = {0, -1, 1}; // Almost straight line, obtuse angle
        double[] yList = {0, 0, 0};  
        int numPoints = 3;
        double epsilon = 0.1; // Slightly above PI

        boolean result = lic.condition2(xList, yList, numPoints, epsilon);
        assertTrue(result, "Expected true because the angle is greater than PI + EPSILON.");
    }

    /**
     * LIC2
     * Test case where one of the points coincides with the vertex
     */
    @Test
    public void test_condition2_CoincidingPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 0, 0}; // All points at the same location
        double[] yList = {0, 0, 0}; 
        int numPoints = 3;
        double epsilon = 0.5;

        boolean result = lic.condition2(xList, yList, numPoints, epsilon);
        assertFalse(result, "Expected false because the angle is undefined due to coinciding points.");
    }



    /**
     * LIC13
     * Test case where both conditions are met:
     * 1. At least one triplet has radius > RADIUS1.
     * 2. At least one triplet has radius ≤ RADIUS2.
     */
    @Test
    public void test_condition13_Valid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 10, 1, 2};
        double[] yList = {0, 0, 0, 0, 0, 0, 0};
        double RADIUS1 = 1.0; // Part A: 1.5 > 1.0 
        double RADIUS2 = 2.0; // Part B: 1.5 ≤ 2.0 
        int A_PTS = 2, B_PTS = 2;
        boolean result = lic.condition13(xList, yList, RADIUS1, RADIUS2, A_PTS, B_PTS);
        assertTrue(result, "Expected true: both conditions met");
    }


    /**
     * LIC13
     * Test case where Part A fails (no triplet > RADIUS1)
     */
    @Test
    public void test_condition13_InvalidPartA() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 4}; 
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 3.0; // Part A fails
        double RADIUS2 = 1.0; // Part B passes 
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition13(xList, yList, RADIUS1, RADIUS2, A_PTS, B_PTS);
        assertFalse(result, "Expected false: Part A fails");
    }

    /**
     * LIC13
     * Test case where Part B fails (no triplet ≤ RADIUS2)
     */
    @Test
    public void test_condition13_InvalidPartB() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 6}; 
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 2.0; // Part A passes 
        double RADIUS2 = 2.5; // Part B fails 
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition13(xList, yList, RADIUS1, RADIUS2, A_PTS, B_PTS);
        assertFalse(result, "Expected false: Part B fails");
    }

    /**
     * LIC13
     * Test case with insufficient points (NUMPOINTS < 5)
     */
    @Test
    public void test_condition13_InsufficientPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3}; // 4 points (needs ≥5)
        double[] yList = {0, 0, 0, 0};
        double RADIUS1 = 1.0, RADIUS2 = 2.0;
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition13(xList, yList, RADIUS1, RADIUS2, A_PTS, B_PTS);
        assertFalse(result, "Expected false: <5 points");
    }


    /**
     * LIC13
     * Test case with coinciding points (radius = 0)
     */
    @Test
    public void test_condition13_CoincidentPoints() {
        LIC lic = new LIC();
        double[] xList = {5, 1, 5, 1, 5}; // Triplet 0-2-4: all points (5,0)
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 0.0; // Part A: 0.0 > 0.0 
        double RADIUS2 = 0.0; // Part B: 0.0 ≤ 0.0 
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition13(xList, yList, RADIUS1, RADIUS2, A_PTS, B_PTS);
        assertFalse(result, "Expected false: Part A fails (radius 0)");
    }

    /**
     * LIC8
     * Test case where three spaced points form a circle with radius > RADIUS1
     */
    @Test
    public void test_condition8_Valid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 10}; // Points 0, 2, 4 (A_PTS=1, B_PTS=1)
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 4.0; // Distance between points 0 and 4 is 10 → radius = 5.0
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition8(xList, yList, RADIUS1, A_PTS, B_PTS);
        assertTrue(result, "Expected true: radius 5.0 > RADIUS1 4.0");
    }


    /**
     * LIC8
     * Test case where all triplets fit within RADIUS1
     */
    @Test
    public void test_condition8_Invalid() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 4}; // Points 0, 2, 4 (A_PTS=1, B_PTS=1)
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 3.0; // Distance between 0 and 4 is 4 → radius = 2.0
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition8(xList, yList, RADIUS1, A_PTS, B_PTS);
        assertFalse(result, "Expected false: radius 2.0 ≤ RADIUS1 3.0");
    }


    /**
     * LIC8
     * Test case with collinear points (radius = farthest distance/2)
     */
    @Test
    public void test_condition8_Collinear() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 8}; // Points 0, 2, 4 (distance 8)
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 3.9; // Radius = 4.0 (8/2)
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition8(xList, yList, RADIUS1, A_PTS, B_PTS);
        assertTrue(result, "Expected true: radius 4.0 > RADIUS1 3.9");
    }

    /**
     * LIC8
     * Test case with exact radius (radius = RADIUS1)
     */
    @Test
    public void test_condition8_ExactRadius() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 6}; // Points 0, 2, 4 (distance 6)
        double[] yList = {0, 0, 0, 0, 0};
        double RADIUS1 = 3.0; // Exactly matches radius (6/2)
        int A_PTS = 1, B_PTS = 1;
        boolean result = lic.condition8(xList, yList, RADIUS1, A_PTS, B_PTS);
        assertFalse(result, "Expected false: radius equals RADIUS1");
    }

    /**
     * LIC9
     * Test case where the three chosen points form a clear valid angle outside [PI - epsilon, PI + epsilon]
     */
    @Test
    public void test_condition9_ValidCase() {
        LIC lic = new LIC();
        double[] xList = {0, 2, 4, 6, 3}; // The last point bends inward
        double[] yList = {0, 0, 0, 0, 3}; // Last point is lifted up, creating an angle
        int numPoints = 5;
        double epsilon = 0.5;
        int cPts = 1, dPts = 1;

        boolean result = lic.condition9(xList, yList, numPoints, epsilon, cPts, dPts);
        assertTrue(result, "Expected true because the last point lifts up, creating an angle outside [PI - epsilon, PI + epsilon].");
    }

    /**
     * LIC9
     * Test case with too few points (numPoints < 5)
     */
    @Test
    public void test_condition9_NotEnoughPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3};  // Only 4 points
        double[] yList = {0, 1, 2, 3};  
        int numPoints = 4;
        double epsilon = 0.5;
        int cPts = 1, dPts = 1;

        boolean result = lic.condition9(xList, yList, numPoints, epsilon, cPts, dPts);
        assertFalse(result, "Expected false because there are fewer than 5 points.");
    }

    /**
     * LIC9
     * Test case where one of the points coincides with the vertex (angle undefined)
     */
    @Test
    public void test_condition9_CoincidingPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 0, 0, 1, 2}; // Middle point (vertex) coincides with first
        double[] yList = {0, 0, 0, 0, 0}; 
        int numPoints = 5;
        double epsilon = 0.5;
        int cPts = 1, dPts = 1;

        boolean result = lic.condition9(xList, yList, numPoints, epsilon, cPts, dPts);
        assertFalse(result, "Expected false because the angle is undefined due to coinciding points.");
    }
    /**
     * LIC9
     * Test case where CPTS is negative
     */
    @Test
    public void test_condition9_NegativeCPTS_DPTS() {
        LIC lic = new LIC();
        double[] xList = {0, 1, 2, 3, 4};
        double[] yList = {0, 0, 0, 0, 0};
        int numPoints = 5;
        double epsilon = 0.5;
        int cPts = -1, dPts = 1; // Invalid negative input
    
        boolean result = lic.condition9(xList, yList, numPoints, epsilon, cPts, dPts);
        assertFalse(result, "Expected false because C_PTS is negative.");
    }
    /**
     * LIC4
     * Test case where there are not enough points (numPoints < qPts)
     */
    @Test
    public void test_condition4_NotEnoughPoints() {
        LIC lic = new LIC();
        double[] xList = {0, 1};  
        double[] yList = {0, 1};  
        int numPoints = 2;
        int qPts = 3;
        int quads = 2;

        boolean result = lic.condition4(xList, yList, numPoints, qPts, quads);
        assertFalse(result, "Expected false because there are fewer than qPts points.");
    }
    /**
     * LIC4
     * Test case where all points are on the origin (0,0)
     */
    @Test
    public void test_condition4_AllPointsOrigin() {
        LIC lic = new LIC();
        double[] xList = {0, 0, 0};  
        double[] yList = {0, 0, 0};  
        int numPoints = 3;
        int qPts = 3;
        int quads = 1;

        boolean result = lic.condition4(xList, yList, numPoints, qPts, quads);
        assertFalse(result, "Expected false because all points are in the same location (quadrant I).");
    }
    /**
     * LIC4
     * Test case where all points are on the x-axis
     */
    @Test
    public void test_condition4_AllOnXAxis() {
        LIC lic = new LIC();
        double[] xList = {-2, -1, 0, 1, 2};  // Moves from Quadrant II -> I
        double[] yList = {0, 0, 0, 0, 0};  
        int numPoints = 5;
        int qPts = 5;
        int quads = 1;

        boolean result = lic.condition4(xList, yList, numPoints, qPts, quads);
        assertTrue(result, "Expected true because the points cross multiple quadrants (II -> I).");
    }
    /**
     * LIC4
     * Test case where all points are on the y-axis
     */
    @Test
    public void test_condition4_AllOnYAxis() {
        LIC lic = new LIC();
        double[] xList = {0, 0, 0, 0, 0};  // Stays on the y-axis
        double[] yList = {-2, -1, 0, 1, 2};  // Moves from Quadrant III -> I
        int numPoints = 5;
        int qPts = 5;
        int quads = 1;

        boolean result = lic.condition4(xList, yList, numPoints, qPts, quads);
        assertTrue(result, "Expected true because the points cross multiple quadrants (III -> I).");
    }

    /**
     * LIC 10:
     * Test case where the area of the triangle formed by three points separated by exactly E_PTS and F_PTS is greater than AREA1
     */
    @Test
    public void test_condition10_Valid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 3, 0, 6, 9}; 
        double[] yList = new double[]{0, 0, 4, 0, 4};
        int epts = 1;  
        int fpts = 1;
        double area1 = 3.0;
        boolean result = lic.condition10(xList, yList, epts, fpts, area1);
        assertTrue(result, "Expected true because the area of the triangle formed by the points is greater than AREA1");
    }

    /**
     * LIC 10:
     * Test case where the area of the triangle formed by three points separated by exactly E_PTS and F_PTS is smaller than AREA1
     */
    @Test
    public void test_condition10_Invalid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 0, 3, 4};  
        double[] yList = new double[]{0, 0, 1, 0, 1};  
        int epts = 1;  
        int fpts = 1;  
        double area1 = 2.0; 
        boolean result = lic.condition10(xList, yList, epts, fpts, area1);
        assertFalse(result, "Expected false because the area of the triangle formed by the points is less than or equal to AREA1");
    }

    /**
     * LIC 10:
     * Test case where there are fewer than 5 data points
     */
    @Test
    public void test_condition10_four_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 1, 2, 3};
        double[] yList = new double[]{0, 0, 0, 0}; 
        int epts = 1; 
        int fpts = 1;  
        double area1 = 3.0;  
        boolean result = lic.condition10(xList, yList, epts, fpts, area1);
        assertFalse(result, "Expected false because there are not enough points to form a valid triangle");
    }

    /**
     * LIC 10:
     * Test case where there are zero data points
     */
    @Test
    public void test_condition10_zero_datapoints() {
        LIC lic = new LIC();
        double[] xList = new double[]{};
        double[] yList = new double[]{}; 
        int epts = 1; 
        int fpts = 1;  
        double area1 = 3.0;  
        boolean result = lic.condition10(xList, yList, epts, fpts, area1);
        assertFalse(result, "Expected false because there are not enough points to form a valid triangle");
    }

    /**
     * LIC 14:
     * Test case where the area of the triangle formed by three points separated by exactly E_PTS and F_PTS is greater than AREA1
     */
    @Test
    public void test_condition14_Valid() {
        LIC lic = new LIC();
        double[] xList = new double[]{0, 3, 0, 6, 9};  
        double[] yList = new double[]{0, 0, 4, 0, 4};  
        int epts = 1;  
        int fpts = 1;
        double area1 = 3.0; 
        double area2 = 20.0;
        boolean result = lic.condition14(xList, yList, epts, fpts, area1, area2);
        assertTrue(result, "Expected true because one triangle's area is greater than AREA1 and another's is smaller than AREA2");
    }

}

