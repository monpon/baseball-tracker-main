/**
 * Stores the X, Y, and Z axis along with all the lines
 * as seen in matplotlib- essentially the base 3d graph function
 */
public class plot {
    

    //can have plot change size based on constructors
    private final double INTERVAL_ = 50;
    private final double DISTANCE_ = 50;

    private int numWide;
    private int numLong;
    private int numTall;

    private graphableLine[] XAxis;
    private graphableLine[] YAxis;
    private graphableLine[] ZAxis;
    
    /**
     * No-args constructor for plot. It sets every value to 10.
     */
    public plot (){
        numWide = 10;
        numLong = 10;
        numTall = 10;
        
        defineAxisArr();
    }
    
    /**
     * Main constructor for plot, setting each size 
     * of the plot to be a given value
     * @param w The number of X axis lines
     * @param l The number of Y axis lines
     * @param t The number of Z axis lines
     */
    public plot (int w, int l, int t){
        numWide = w;
        numLong = l;
        numTall = t;
        
        defineAxisArr();
    }
    
    private void defineAxisArr (){
        XAxis = new graphableLine[numWide];
        YAxis = new graphableLine[numLong];
        ZAxis = new graphableLine[numTall];

        double temp = 0;
        for (int i = 0; i < numWide; i++){
            XAxis[i] = new graphableLine(temp, 0, 0, temp, DISTANCE_, 0);
            temp += INTERVAL_;
        }

        temp = 0;
        for (int i = 0; i < numWide; i++){
            YAxis[i] = new graphableLine(0, temp, 0, DISTANCE_, temp, 0);
            temp += INTERVAL_;
        }

        //TODO z axis do i want to do whole background?
        temp = 0;
        for (int i = 0; i < numWide; i++){
            ZAxis[i] = new graphableLine(0, temp, 0, DISTANCE_, temp, 0);
            temp += INTERVAL_;
        }
        
    }
    
    /**
     * Getter method for the XAxis array
     * @return The X-Axis
     */
    public graphableLine[] getXAxis (){
        return XAxis;
    }

    /**
     * Getter method for the YAxis array
     * @return The Y-Axis
     */
    public graphableLine[] getYAxis (){
        return YAxis;
    }

    /**
     * Getter method for the ZAxis array
     * @return The Z-Axis
     */
    public graphableLine[] getZAxis (){
        return ZAxis;
    }

}
