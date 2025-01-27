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
   
}
