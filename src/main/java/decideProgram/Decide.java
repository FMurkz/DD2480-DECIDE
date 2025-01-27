package decideProgram;

public class Decide {

    public static boolean[] computeCMV(double[] x, double[] y, Parameters params, int numPoints) {
        LIC lic = new LIC();
        boolean[] cmv = new boolean[15];

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

    public static boolean determineLaunch(boolean[] fuv) {
        for (boolean val : fuv) {
            if (!val) {
                return false;
            }
        }
        return true;
    }

    public static boolean DECIDE(int numPoints, double[] x, double[] y, int[][] lcm, boolean[] puv, Parameters parameters){
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