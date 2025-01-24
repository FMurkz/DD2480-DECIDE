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

    }