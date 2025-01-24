package decideProgram;

public class LIC {

    /**
     * LIC 0: There exists at least one set of two 
     * consecutive data points that are a distance greater 
     * than the length, LENGTH1, apart.
     */
    public boolean condition0(double[] xArray, double[] yArray, int length1) {

        if (xArray.length!=yArray.length) throw new IllegalArgumentException("xArray and yArray must have the same length");

        if (length1 < 0) throw new IllegalArgumentException("Length1 must be greater than or equal to 0");

        if (xArray.length < 2) return false;

        for (int i = 0; i < xArray.length - 1; i++) {
            double distance = Math.sqrt(Math.pow(xArray[i + 1] - xArray[i], 2) + Math.pow(yArray[i + 1] - yArray[i], 2));
            if (distance > length1) return true;
        }
        return false;
    }

    public boolean condition1() {
        return true;
    }

    public boolean condition2() {
        return true;
    }

    public boolean condition3() {
        return true;
    }

    public boolean condition4() {
        return true;
    }

    public boolean condition5() {
        return true;
    }

    public boolean condition6() {
        return true;
    }

    /**
     * LIC 7: 
     * There exists at least one set of two data points separated by exactly
     * K PTS consecutive intervening points that are a distance greater than the length, LENGTH1, apart. 
     * The condition is not met when NUMPOINTS < 3.
     */
    public boolean condition7(double[] xArray, double[] yArray, int numPoints, int kPts, int length1) {
        if (xArray.length!=yArray.length) throw new IllegalArgumentException("xArray and yArray must have the same length");
        if (numPoints < 3) return false;
        if (kPts <= 0) throw new IllegalArgumentException("kPts must be greater than or equal to 1");

        for (int i = 0; i < numPoints - kPts - 1; i++) {
            double distance = Math.sqrt(
                Math.pow(xArray[i + kPts + 1] - xArray[i], 2) + 
                Math.pow(yArray[i + kPts + 1] - yArray[i], 2)
            );
            if (distance > length1) return true; // Condition satisfied
        }
    
        return false; // Condition not satisfied
    }


    public boolean condition8() {
        return true;
    }

    public boolean condition9() {
        return true;
    }

    public boolean condition10() {
        return true;
    }

    public boolean condition11() {
        return true;
    }

    public boolean condition12() {
        return true;
    }

    public boolean condition13() {
        return true;
    }

    public boolean condition14() {
        return true;
    }
}
