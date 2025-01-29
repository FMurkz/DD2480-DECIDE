package decideProgram;

public class Decide {

    /**
     * Computes the Conditions Met Vector (CMV) based on the given parameters and points.
     * Each element in the CMV corresponds to whether a specific Launch Interceptor Condition (LIC) is satisfied.
     *
     * @param x        The x-coordinates of the data points.
     * @param y        The y-coordinates of the data points.
     * @param params   Parameters required for evaluating the LICs.
     * @param numPoints The number of data points provided.
     * @return A boolean array representing the CMV, where each element is true if the corresponding LIC is satisfied.
     */
    public static boolean[] computeCMV(double[] x, double[] y, Parameters params, int numPoints) {
        LIC lic = new LIC();
        boolean[] cmv = new boolean[15];
        if (x == null || y == null || params == null) {
            throw new IllegalArgumentException("Input arrays and parameters cannot be null");
        }
        if (x.length != y.length) {
            throw new IllegalArgumentException("x and y arrays must have the same length");
        }
        if (numPoints < 2 || numPoints > 100) {
            throw new IllegalArgumentException("numPoints must be in range [2, 100]");
        }

        cmv[0] = lic.condition0(x, y, params.length1);
        cmv[1] = lic.condition1(x, y, params.radius1);
        cmv[2] = lic.condition2(x, y, numPoints, params.epsilon);
        cmv[3] = lic.condition3(x, y, params.area1);
        cmv[4] = lic.condition4(x, y, numPoints, params.qpts, params.quads);
        cmv[5] = lic.condition5(x, y);
        cmv[6] = lic.condition6(x, y, params.npts, params.dist);
        cmv[7] = lic.condition7(x, y, numPoints, params.kpts, params.length1);
        cmv[8] = lic.condition8(x, y, params.radius1, params.aptS, params.bpts);
        cmv[9] = lic.condition9(x, y, numPoints, params.epsilon, params.cpts, params.dpts);
        cmv[10] = lic.condition10(x, y, params.epts, params.fpts, params.area1);
        cmv[11] = lic.condition11(x, y, params.gpts);
        cmv[12] = lic.condition12(x, y, numPoints, params.kpts, params.length1, params.length2);
        cmv[13] = lic.condition13(x, y, params.radius1, params.radius2, params.aptS, params.bpts);
        cmv[14] = lic.condition14(x, y, params.epts, params.fpts, params.area1, params.area2);

        return cmv;
    }

    /**
     * Computes the Preliminary Unlocking Matrix (PUM) by combining CMV values
     * based on the Logical Connector Matrix (LCM).
     *
     * @param cmv The Conditions Met Vector (CMV).
     * @param lcm The Logical Connector Matrix (LCM), which specifies logical relationships between LICs.
     * @return A 2D boolean array representing the PUM, where each entry indicates whether the corresponding LIC combination is satisfied.
     */
    public static boolean[][] computePUM(boolean[] cmv, int[][] lcm) {
        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == j) {
                    pum[i][j] = true; 
                    continue;
                }
                if (lcm[i][j] == 0) { // NOTUSED
                    pum[i][j] = true;
                }
                else if (lcm[i][j] == 1) { // ANDD
                    pum[i][j] = cmv[i] && cmv[j];
                } 
                else if (lcm[i][j] == 2) { // ORR
                    pum[i][j] = cmv[i] || cmv[j];
                }
            }
        }
        return pum;
    }

    /**
     * Calculates the Final Unlocking Vector (FUV) by evaluating the PUM and Preliminary Unlocking Vector (PUV).
     * An element in the FUV is true if the corresponding PUV element is false or all PUM entries in the row are true.
     *
     * @param pum The Preliminary Unlocking Matrix (PUM).
     * @param puv The Preliminary Unlocking Vector (PUV), specifying the LICs relevant for the launch decision.
     * @return A boolean array representing the FUV, where each element indicates the final unlocking status for a LIC.
     */
    public static boolean[] calculateFUV(boolean[][] pum, boolean[] puv) {
        boolean[] fuv = new boolean[puv.length];
        for (int i = 0; i < fuv.length; i++) {
            if (!puv[i]) {
                fuv[i] = true;
            } else {
                fuv[i] = true;
                for (int j = 0; j < fuv.length; j++) {
                    if (!pum[i][j]) {
                        fuv[i] = false;
                        break;
                    }
                }
            }
        }
        return fuv;
    }

    /**
     * Determines whether the interceptor launch condition is satisfied based on the Final Unlocking Vector (FUV).
     * The launch is allowed only if all elements in the FUV are true.
     *
     * @param fuv The Final Unlocking Vector (FUV).
     * @return True if the interceptor launch condition is met, false otherwise.
     */
    public static boolean determineLaunch(boolean[] fuv) {
        for (boolean val : fuv) {
            if (!val) {
                return false;
            }
        }
        return true;
    }


    /**
     * The main decision-making function that integrates all computations to determine whether an interceptor should be launched.
     * Outputs "YES" if the launch condition is satisfied, otherwise outputs "NO".
     *
     * @param numPoints The number of data points provided.
     * @param x         The x-coordinates of the data points.
     * @param y         The y-coordinates of the data points.
     * @param lcm       The Logical Connector Matrix (LCM).
     * @param puv       The Preliminary Unlocking Vector (PUV).
     * @param parameters Parameters required for evaluating LICs.
     * @return True if the interceptor launch condition is met, false otherwise.
     */
    public static boolean DECIDE(int numPoints, double[] x, double[] y, int[][] lcm, boolean[] puv, Parameters parameters){
        if(numPoints!=x.length || numPoints!=y.length){
            throw new IllegalArgumentException("The number of points must be equal to the length of x and y arrays");
        }
        boolean[] cmv = computeCMV(x, y, parameters, numPoints);
        boolean[][] pum = computePUM(cmv, lcm);
        boolean[] fuv = calculateFUV(pum, puv);
        boolean launch = determineLaunch(fuv);
        if (launch) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        return launch;
    }
}