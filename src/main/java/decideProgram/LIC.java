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

/**
     * LIC4: There exists at least one set of Q PTS consecutive data points that lie in more than QUADS quadrants. Where there is ambiguity as to which quadrant contains a given point, 
     * priority of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0) is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point 
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
     * @param x
     * @param y
     * @param numPoints
     * @param qPts
     * @param quads
     * @return
     */
    public boolean condition4(double[] x, double[] y, int numPoints, int qPts, int quads) {
        if (numPoints < qPts || qPts < 2 || quads < 1 || quads > 3) {
            return false; // Condition cannot be met if constraints are violated. Check if qPts is within valid bounds
        }

        // Iterate over all possible sets of Q_PTS consecutive points
        for (int i = 0; i <= numPoints - qPts; i++) {
            boolean[] quadrantVisited = new boolean[4]; // Boolean array to track quadrants
            int uniqueQuadrants = 0;

            // Check the quadrant of each point in this subset
            for (int j = i; j < i + qPts; j++) {
                int quadrant = getQuadrant(x[j], y[j]);

                if (!quadrantVisited[quadrant - 1]) { // If this quadrant hasn't been visited yet
                    quadrantVisited[quadrant - 1] = true;
                    uniqueQuadrants++;
                }

                // If the number of unique quadrants exceeds QUADS, return true
                if (uniqueQuadrants > quads) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Helperfunction for condition4 that checks which quadrant each point in the set of Q is in
     * @param x
     * @param y
     * @return
     */
    private int getQuadrant(double x, double y) {
        if (x >= 0 && y > 0) return 1;  // Quadrant I
        if (x < 0 && y >= 0) return 2;  // Quadrant II
        if (x <= 0 && y < 0) return 3;  // Quadrant III
        if (x > 0 && y <= 0) return 4;  // Quadrant IV
        return 1; // Default case, (0,0) as Quadrant I
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


    public boolean condition8(double[] xArray, double[] yArray, double RADIUS1, int A_PTS, int B_PTS) {
        if (xArray.length != yArray.length) throw new IllegalArgumentException("xArray and yArray must have the same length");
        if (RADIUS1 < 0) throw new IllegalArgumentException("RADIUS1 must be greater than or equal to 0");
        if (A_PTS < 1 || B_PTS < 1) throw new IllegalArgumentException("A_PTS and B_PTS must be at least 1");
        if (A_PTS + B_PTS > xArray.length - 3) throw new IllegalArgumentException("A_PTS + B_PTS must be less than or equal to the length of xArray minus 3");



    int numPoints = xArray.length;
    
       
    for (int i = 0; i <= numPoints - (A_PTS + B_PTS + 3); i++) {
        int j = i + A_PTS + 1; 
        int k = j + B_PTS + 1; 

        double[] p1 = {xArray[i], yArray[i]};
        double[] p2 = {xArray[j], yArray[j]};
        double[] p3 = {xArray[k], yArray[k]};

        
        double radius = minimalEnclosingCircleRadius(p1, p2, p3);
        if (radius > RADIUS1) { 
            return true; 
            }
        }
        return false;
    }

    /**
     * LIC9: There exists at least one set of three data points separated by exactly C_PTS and D_PTS consecutive intervening points, which form an angle. The second points of the set of three points is always the vertex of the angle. If either the first point or the last point (or both) coincide with the vertex,
     * the angle is undefined and the LIC is not satisfied by those three points. 
     * When NUMPOINTS < 5, the condition is not met.
     * @param x
     * @param y
     * @param numPoints
     * @param epsilon
     * @param cPts
     * @param dPts
     * @return
     */
    public boolean condition9(double[] x, double[] y, int numPoints, double epsilon, int cPts, int dPts) {
        if (numPoints < 5 || epsilon < 0 || epsilon >= Math.PI || cPts < 1 || dPts < 1 || (cPts + dPts > numPoints - 3)) {
            return false; // Condition cannot be met if requirements aren't satisfied
        }

        for (int i = 0; i < numPoints - (cPts + dPts) - 2; i++) {
            // Select three points with given separation
            double x1 = x[i], y1 = y[i];
            double x2 = x[i + cPts + 1], y2 = y[i + cPts + 1]; // Vertex
            double x3 = x[i + cPts + dPts + 2], y3 = y[i + cPts + dPts + 2];

            // Check if the first or last point coincides with the vertex
            if ((x1 == x2 && y1 == y2) || (x3 == x2 && y3 == y2)) {
                continue; // Skip this as the angle is undefined
            }

            // Compute vectors
            double v1x = x1 - x2, v1y = y1 - y2; // Vector from vertex to first point
            double v2x = x3 - x2, v2y = y3 - y2; // Vector from vertex to last point

            // Compute the dot product
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


    /**
     * LIC 10:
     * There exists at least one set of three data points, separated by exactly 
     * E_PTS and F_PTS consecutive intervening points, respectively, 
     * that are the vertices of a triangle with area greater than AREA1.
     * The condition is not met when NUMPOINTS < 5.
     */
    public boolean condition10(double[] x, double[] y, int epts, int fpts, double area1) {
        if (x.length < 5) return false;  
    
        for (int i = 0; i < x.length - epts - fpts - 2; i++) {
            double area = Math.abs(x[i] * (y[i + epts + 1] - y[i + epts + fpts + 2]) + 
                                   x[i + epts + 1] * (y[i + epts + fpts + 2] - y[i]) + 
                                   x[i + epts + fpts + 2] * (y[i] - y[i + epts + 1])) / 2;
            if (area > area1) {
                return true; 
            }
        }
        return false;  
    }

    public boolean condition11(double[] xArray, double[] yArray, int gPts) {
        if (xArray.length != yArray.length) {
            throw new IllegalArgumentException("xArray and yArray must have the same length");
        }
        int numPoints = xArray.length;
    
        if (gPts < 1 || gPts > numPoints - 2) {
            return false;
        }
        for (int i = 0; i < numPoints - gPts - 1; i++) {
            
            int j = i + gPts + 1;
            if (xArray[j] < xArray[i]) {
                return true; 
            }
        }
    
        return false; 
    }
    

    public boolean condition12() {
        return true;
    }

    public boolean condition13(double[] xArray, double[] yArray, double RADIUS1, double RADIUS2, int A_PTS, int B_PTS) {
        
        if (xArray.length != yArray.length) throw new IllegalArgumentException("xArray and yArray must have the same length");
        if (RADIUS1 < 0 || RADIUS2 < 0) throw new IllegalArgumentException("RADIUS1 and RADIUS2 must be greater than or equal to 0");
        if (A_PTS < 1 || B_PTS < 1) throw new IllegalArgumentException("A_PTS and B_PTS must be at least 1");

        int numPoints = xArray.length;
        if (numPoints < 5) return false;

        boolean foundPartA = false;
        boolean foundPartB = false;
            
       
        for (int i = 0; i <= numPoints - (A_PTS + B_PTS + 3); i++) {
            int j = i + A_PTS + 1;
            int k = j + B_PTS + 1;

            double[] p1 = {xArray[i], yArray[i]};
            double[] p2 = {xArray[j], yArray[j]};
            double[] p3 = {xArray[k], yArray[k]};

       
            double radius = minimalEnclosingCircleRadius(p1, p2, p3);

            // Checking Part A: radius > RADIUS1 
            if (radius > RADIUS1) {
                foundPartA = true;
            }

            // Checking Part B: radius ≤ RADIUS2 
            if (radius <= RADIUS2) {
                foundPartB = true;
            }

            
            if (foundPartA && foundPartB) {
                break;
            }
        }

        
        return foundPartA && foundPartB;
    
    }
        
    /**
     * LIC 14:
     * There exists at least one set of three data points, separated by exactly 
     * E_PTS and F_PTS consecutive intervening points, respectively, 
     * that are the vertices of a triangle with area greater than AREA1.
     * Additionally, there exists at least one set of three data points 
     * (which can be the same or different) that form a triangle with area less than AREA2.
     * The condition is not met when NUMPOINTS < 5.
     */
    public boolean condition14(double[] x, double[] y, int epts, int fpts, double area1, double area2) {
        if (x.length < 5) return false;  
        boolean cond1 = false;  
        boolean cond2 = false;  
        for (int i = 0; i < x.length - epts - fpts - 2; i++) {
            double area = Math.abs(x[i] * (y[i + epts + 1] - y[i + epts + fpts + 2]) + 
                                x[i + epts + 1] * (y[i + epts + fpts + 2] - y[i]) + 
                                x[i + epts + fpts + 2] * (y[i] - y[i + epts + 1])) / 2;
            if (area > area1) {
                cond1 = true;  
                break;  
            }
        }
        for (int i = 0; i < x.length - epts - fpts - 2; i++) {
            double area = Math.abs(x[i] * (y[i + epts + 1] - y[i + epts + fpts + 2]) + 
                                x[i + epts + 1] * (y[i + epts + fpts + 2] - y[i]) + 
                                x[i + epts + fpts + 2] * (y[i] - y[i + epts + 1])) / 2;
            if (area < area2) {
                cond2 = true;  
                break;  
            }
        }
        return cond1 && cond2;
    }

}
