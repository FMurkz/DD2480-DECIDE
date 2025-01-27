package decideProgram;

public class Main {
    //To demo
    public static void main(String[] args) {
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

    }
}
