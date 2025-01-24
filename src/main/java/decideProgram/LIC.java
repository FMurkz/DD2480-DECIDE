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
