package assignment1;

public class Parameters {
    public double length1;         // Length in LICs 0, 7, 12
    public double epsilon;         // Deviation from PI in LICs 2, 9
    public double radius1;         // Radius in LICs 1, 8, 13
    public double area1;           // Area in LICs 3, 10, 14
    public int qpts;               // No. of consecutive points in LIC 4 
    public int quads;              // No. of quadrants in LIC 4
    public double dist;            // Distance in LIC 6 
    public int npts;               // No. of consecutive pts . in LIC 6
    public int kpts;               // No. of int. pts. in LICs 7, 12
    public int aptS;               // No. of int. pts. in LICs 8, 13
    public int bpts;               // No. of int. pts. in LICs 8, 13
    public int cpts;               // No. of int. pts. in LIC  9
    public int dpts;               // No. of int. pts. in LIC 9 
    public int epts;               // No. of int. pts. in LICs 10, 14
    public int fpts;               // No. of int. pts. in LICs 10, 14
    public int gpts;               // No. of int. pts. in LIC 11
    public double length2;         // Maximum length in LIC 12
    public double radius2;         // Maximum radius in LIC 13 
    public double area2;           // Maximum area in LIC 14


    //Constructor to initialize all the variables for the class
    public Parameters(double length1, double epsilon, double radius1, double area1,
                        int qpts, int quads, double dist, int npts, int kpts, int aptS,
                        int bpts, int cpts, int dpts, int epts, int fpts, int gpts,
                        double length2, double radius2, double area2) {
        this.length1 = length1;
        this.epsilon = epsilon;
        this.radius1 = radius1;
        this.area1 = area1;
        this.qpts = qpts;
        this.quads = quads;
        this.dist = dist;
        this.npts = npts;
        this.kpts = kpts;
        this.aptS = aptS;
        this.bpts = bpts;
        this.cpts = cpts;
        this.dpts = dpts;
        this.epts = epts;
        this.fpts = fpts;
        this.gpts = gpts;
        this.length2 = length2;
        this.radius2 = radius2;
        this.area2 = area2;
    }

}
