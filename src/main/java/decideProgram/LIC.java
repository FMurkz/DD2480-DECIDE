package decideProgram;

public class LIC {

    public boolean condition0() {
        return true;
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

    /**
     * Checks if there exists at least one set of two consecutive data points
     * (X[i], Y[i]) and (X[j], Y[j]), where j = i + 1, such that the difference
     * between X[j] and X[i] is less than 0.
     * 
     * @param xArray The x coordinates.
     * @param yArray The y coordinates.
     * @return true if condition is met, false otherwise.
     * @throws IllegalArgumentException if the arrays' lengths do not match.
     */
    public boolean condition5(double[] xArray, double[] yArray) {

        if (xArray.length != yArray.length) {
            throw new IllegalArgumentException("xArray and yArray arrays must have the same length.");
        }

        for (int i = 0; i < xArray.length - 1; i++) {
            int j = i + 1;
            if (xArray[j] - xArray[i] < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean condition6() {
        return true;
    }

    public boolean condition7() {
        return true;
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
