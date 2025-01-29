package decideTest;

import decideProgram.Decide;
import decideProgram.Parameters;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DECIDETests {

    @Test
    public void test_computeCMV_valid() {
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        
        // Initialize the Parameters object with valid values
        Parameters params = new Parameters(
            2.0,   // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );
        
        int numPoints = 5;

       
        boolean[] cmv = Decide.computeCMV(x, y, params, numPoints);  
        boolean[] expected = {true, true, true, false, false, false, false, true, true, true, false, false, false, false, false};

       
        assertArrayEquals(expected, cmv, "Conditions did not match expected results");
    }

    @Test
     public void test_computeCMV_invalid() {
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        
        // Initialize the Parameters object with valid values
        Parameters params = new Parameters(
            2.0,   // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );
        
        int numPoints = 5;

        boolean[] cmv = Decide.computeCMV(x, y, params, numPoints);  
        
        boolean[] expected = {true, true, true, true, false, false, false, true, true, true, false, false, false, false, false};
        assertFalse(Arrays.equals(expected, cmv), "Conditions matched when they should not have.");
    }

    @Test
    public void test_PUM_NOTUSED(){
        boolean[] cmv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0;
            }
        }
        boolean[][] pum = Decide.computePUM(cmv, lcm);
        boolean[][] expected = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                expected[i][j] = true;
            }
        }
        assertArrayEquals(expected, pum);
    }

    @Test
    public void test_PUM_ANDD(){
        boolean[] cmv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 1;
            }
        }
        boolean[][] pum = Decide.computePUM(cmv, lcm);
        boolean[][] expected = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                expected[i][j] = true;
            }
        }
        assertArrayEquals(expected, pum);
    }

    @Test
    public void test_calculateFUV() {
        // Initialize test data for PUM and PUV
        boolean[][] pum = {
            {true, true, false},
            {true, true, true},
            {false, true, true}
        };
        boolean[] puv = {true, false, true};

        // Call the calculateFUV function
        boolean[] fuv = Decide.calculateFUV(pum, puv);

        // Expected output based on the inputs
        boolean[] expected = {false, true, false};

        // Assert the results
        assertArrayEquals(expected, fuv, "FUV did not match the expected output");
    }

    
    @Test
    public void test_determineLaunch() {
        boolean[] fuv = {true, false, true};
        Decide decide = new Decide();
        boolean res = decide.determineLaunch(fuv);
        assertFalse(res);
    }

    @Test
    public void test_DECIDE_Launch_NOTUSED(){
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0;
            }
        }
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Parameters params = new Parameters(
            2.0,   // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );
        int numPoints = 5;


        // Call the DECIDE function
        boolean res = Decide.DECIDE(numPoints, x, y, lcm, puv, params);

       assertTrue(res);
    }
  @Test
    public void test_DECIDE_Launch_withANDDORR(){
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0;
            }
        }
        lcm[1][0]=1; 
        lcm[1][1]=1;
        lcm[1][2]=2;
        lcm[1][3]=2;
        boolean[] puv = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Parameters params = new Parameters(
            2.0,   // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );
        int numPoints = 5;


        // Call the DECIDE function
        boolean res = Decide.DECIDE(numPoints, x, y, lcm, puv, params);

       assertTrue(res);
    }
    @Test
    public void test_DECIDE_Launch_Failure_withANDsDORR() {
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16}; // Quadratic function ensures some LICs fail
        int[][] lcm = new int[15][15];

        // Set NOTUSED (0) for all first
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0; // Default: NOTUSED
            }
        }

        // Define some ANDD and ORR conditions
        lcm[1][0] = 1; // ANDD - Requires both LIC1 and LIC0 to be true
        lcm[1][1] = 1; // ANDD - Requires LIC1 to be true
        lcm[1][2] = 2; // ORR - At least one of LIC1 or LIC2 must be true
        lcm[1][3] = 2; // ORR - At least one of LIC1 or LIC3 must be true

        // Ensure at least one `PUV[i] = true`, so `FUV[i]` depends on `PUM[i]`
        boolean[] puv = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        // Modify parameters so that at least one LIC fails (making CMV[1] false)
        Parameters params = new Parameters(
            100.0,  // length1 (Too large, so LIC0 fails)
            0.2,    // epsilon
            0.5,    // radius1 (Too small, so LIC1 fails)
            100.0,  // area1 (Too large, so LIC3 fails)
            3,      // qpts
            2,      // quads
            1.0,    // dist
            3,      // npts
            1,      // kpts
            1,      // aptS
            1,      // bpts
            1,      // cpts
            1,      // dpts
            2,      // epts
            3,      // fpts
            1,      // gpts
            3.0,    // length2
            2.0,    // radius2
            15.0    // area2
        );
        int numPoints = 5;

        // Call the DECIDE function
        boolean res = Decide.DECIDE(numPoints, x, y, lcm, puv, params);

        // Expecting launch decision to be NO
        assertFalse(res);
    }
    @Test
    public void test_DECIDE_Invalid_Negative_Length1() {
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        int numPoints = x.length;

        // Define LCM (Logical Connector Matrix) with all NOTUSED
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0; // Default: NOTUSED
            }
        }

        // Define PUV (Preliminary Unlocking Vector)
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        // Define invalid parameters (negative length1)
        Parameters params = new Parameters(
            -1.0,  // Invalid: Negative length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );

        // Verify that an exception is thrown due to invalid input
        assertThrows(IllegalArgumentException.class, () -> {
            Decide.DECIDE(numPoints, x, y, lcm, puv, params);
        });
    }

    @Test
    public void test_DECIDE_Invalid_x_y_Not_Same_Length() {
        // Initialize test data
        double[] x = {0, 1, 2, 3}; //Not same as y
        double[] y = {0, 1, 4, 9, 16};
        int numPoints = x.length;

        // Define LCM (Logical Connector Matrix) with all NOTUSED
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0; // Default: NOTUSED
            }
        }

        // Define PUV (Preliminary Unlocking Vector)
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        // Define invalid parameters (negative length1)
        Parameters params = new Parameters(
            1.0,  // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );

        // Verify that an exception is thrown due to invalid input
        assertThrows(IllegalArgumentException.class, () -> {
            Decide.DECIDE(numPoints, x, y, lcm, puv, params);
        });
    }

    @Test
    public void test_DECIDE_Invalid_numPoints_Not_Equal_To_x_And_y() {
        // Initialize test data
        double[] x = {0, 1, 2, 3, 4};
        double[] y = {0, 1, 4, 9, 16};
        int numPoints = 1;

        // Define LCM (Logical Connector Matrix) with all NOTUSED
        int[][] lcm = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = 0; // Default: NOTUSED
            }
        }

        // Define PUV (Preliminary Unlocking Vector)
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        // Define invalid parameters (negative length1)
        Parameters params = new Parameters(
            2.0,  // length1
            0.2,   // epsilon
            1.5,   // radius1
            10.0,  // area1
            3,     // qpts
            2,     // quads
            1.0,   // dist
            3,     // npts
            1,     // kpts
            1,     // aptS
            1,     // bpts
            1,     // cpts
            1,     // dpts
            2,     // epts
            3,     // fpts
            1,     // gpts
            3.0,   // length2
            2.0,   // radius2
            15.0   // area2
        );

        // Verify that an exception is thrown due to invalid input
        assertThrows(IllegalArgumentException.class, () -> {
            Decide.DECIDE(numPoints, x, y, lcm, puv, params);
        });
    }
}