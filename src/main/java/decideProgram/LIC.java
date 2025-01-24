package decideProgram;

public class LIC {
    
    /* Helper methods */

    /**
     * Helper method to compute the radius of the minimal enclosing circle for a given triplet of points.
     * @param p1 The first point.
     * @param p2 The second point.
     * @param p3 The third point.
     * @return The radius of the minimal enclosing circle.
     */
    private double minimalEnclosingCircleRadius(double[] p1, double[] p2, double[] p3) {
        double x1 = p1[0], y1 = p1[1];
        double x2 = p2[0], y2 = p2[1];
        double x3 = p3[0], y3 = p3[1];
    
        // Checking collinearity using triangle area
        double area = 0.5 * Math.abs(
            (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)
        );
        if (area == 0) { // Collinear points
            // must use the longest pairwise distance as the diameter
            double d12 = Math.hypot(x2 - x1, y2 - y1);
            double d23 = Math.hypot(x3 - x2, y3 - y2);
            double d13 = Math.hypot(x3 - x1, y3 - y1);
            return Math.max(d12, Math.max(d23, d13)) / 2.0;
        }
    
        // Non-collinear: must compute circumradius
        double denominator = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        double circumX = ( (x1*x1 + y1*y1) * (y2 - y3) + 
                           (x2*x2 + y2*y2) * (y3 - y1) + 
                           (x3*x3 + y3*y3) * (y1 - y2) ) / denominator;
        double circumY = ( (x1*x1 + y1*y1) * (x3 - x2) + 
                           (x2*x2 + y2*y2) * (x1 - x3) + 
                           (x3*x3 + y3*y3) * (x2 - x1) ) / denominator;
        return Math.hypot(x1 - circumX, y1 - circumY);
    }
    
    

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

    public boolean condition1(double[] xArray, double[] yArray, double radius1) {

        if (xArray.length != yArray.length) throw new IllegalArgumentException("xArray and yArray must have the same length");

        if (radius1 < 0) throw new IllegalArgumentException("Radius1 must be greater than or equal to 0");

        if (xArray.length < 3) return false;

        for (int i = 0; i < xArray.length - 2; i++) {
            double[] p1 = {xArray[i], yArray[i]};
            double[] p2 = {xArray[i+1], yArray[i+1]};
            double[] p3 = {xArray[i+2], yArray[i+2]};
        
            double radius = minimalEnclosingCircleRadius(p1, p2, p3); // Will add this helper method later.
            if (radius > radius1) { 
                return true;
            }
        }
        return false;
      
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
