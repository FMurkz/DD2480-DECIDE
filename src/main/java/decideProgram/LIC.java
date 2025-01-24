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
      * Calculates the perpendicular distance from a point (x0, y0) 
      * to the line formed by two points (x1, y1) and (x2, y2).
    */
     public static double perpendicularDistance(double x1, double y1, double x2, double y2, double x0, double y0) {
        double A = y2 - y1;
        double B = -(x2 - x1);
        double C = (x2 * y1) - (x1 * y2);

        return Math.abs(A * x0 + B * y0 + C) / Math.sqrt(A * A + B * B);
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

    /**
     * LIC2: There exists at least one set of three consecutive data points which form an angle such that the angle is either smaller than pi-epsilon or larger than pi + epsilon
     * @param x
     * @param y
     * @param numPoints
     * @param epsilon
     * @return True if LIC2 is satisfied, false otherwise
     */
    public boolean condition2(double[] x, double[] y, int numPoints, double epsilon) {
        if (numPoints < 3 || epsilon < 0 || epsilon >= Math.PI) {
            return false; } // Condition cant be met if we have fewer than 3 points
            for (int i = 0; i < numPoints - 2; i++) {
                // Extract three consecutive points
                double x1 = x[i], y1 = y[i];
                double x2 = x[i + 1], y2 = y[i + 1]; // Vertex
                double x3 = x[i + 2], y3 = y[i + 2];
    
                // Check if the first or last point coincides with the vertex
                if ((x1 == x2 && y1 == y2) || (x3 == x2 && y3 == y2)) {
                    continue; // Skip this set as the angle is undefined
                }
    
                //vectors
                double v1x = x1 - x2, v1y = y1 - y2; // Vector from vertex to first point
                double v2x = x3 - x2, v2y = y3 - y2; // Vector from vertex to last point
    
                //dot product
                double dotProduct = v1x * v2x + v1y * v2y;
    
                //the magnitudes of the vectors
                double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
                double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);
    
                if (mag1 == 0 || mag2 == 0) {
                    continue; // Skip if any vector has zero length
                }
    
                // Compute the angle using the cosine rule
                double angle = Math.acos(dotProduct / (mag1 * mag2));
    
                // Check if the angle falls outside the range [PI - EPSILON, PI + EPSILON]
                if (angle < (Math.PI - epsilon) || angle > (Math.PI + epsilon)) {
                    return true; // Condition is met
                }
            }
    
            return false;
        }
    

    public boolean condition3(double[] x, double[] y, double area1) {
        for (int i = 0; i < x.length - 2; i++) {
            double area = Math.abs(x[i] * (y[i+1] - y[i+2]) + x[i+1] * (y[i+2] - y[i]) + x[i+2] * (y[i] - y[i+1])) / 2;
            if (area > area1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition4() {
        return true;
    }

    public boolean condition5() {
        return true;
    }


    /**
     * Checks if at least one set of N_PTS consecutive points has a distance greater than DIST
     * from the line joining the first and last points. If the first and last points are identical,
     * the distance is calculated from the coincident point to the others.
     * 
     * @param xArray  Array of x coordinates.
     * @param yArray  Array of y coordinates.
     * @param npts    Number of consecutive points to check.
     * @param dist    Distance threshold.
     * @return        True if the condition is met, otherwise false.
    */
    public boolean condition6(double[] xArray, double[] yArray, int npts, double dist) {
        if (xArray.length != yArray.length) {
            throw new IllegalArgumentException("xArray and yArray must have the same length");
        }
        int numPoints = xArray.length;
    
        if (npts < 3 || numPoints < 3 || npts > xArray.length) {
            return false;
        }
    
        if (dist < 0) {
            throw new IllegalArgumentException("DIST cannot be negative");
        }      
    
        for (int i = 0; i <= numPoints - npts; i++) {
            double[] npts_xPoints = new double[npts];
            double[] npts_yPoints = new double[npts];
    
            System.arraycopy(xArray, i, npts_xPoints, 0, npts);
            System.arraycopy(yArray, i, npts_yPoints, 0, npts);
    
            double firstPoint_x = npts_xPoints[0], firstPoint_y = npts_yPoints[0];
            double lastPoint_x = npts_xPoints[npts - 1], lastPoint_y = npts_yPoints[npts - 1];
    
            for (int j = 1; j < npts - 1; j++) {
                double currentPoint_x = npts_xPoints[j], currentPoint_y = npts_yPoints[j];
                double distance = perpendicularDistance(firstPoint_x, firstPoint_y, lastPoint_x, lastPoint_y, currentPoint_x, currentPoint_y);
    
                if (distance > dist) {
                    return true;
                }
            }
        }
    
        return false;
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
