        /**
 * Stores the X, Y, and Z axis along with all the lines
 * as seen in matplotlib- essentially the base 3d graph function
 */
public class plot {
    

    //can have plot change size based on constructors
    private final double INTERVAL_ = 1;
    private final double DISTANCE_ = 10;

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
        XAxis = new graphableLine[numWide + 1];
        YAxis = new graphableLine[numLong + 1];
        ZAxis = new graphableLine[numWide + numLong + numTall*2 + 4];

        double temp = 0;
        for (int i = 0; i < numWide + 1; i++){
            XAxis[i] = new graphableLine(temp, 0, 0, temp, DISTANCE_, 0);
            temp += INTERVAL_;
        }


        temp = 0;
        for (int i = 0; i < numLong + 1; i++){
            YAxis[i] = new graphableLine(0, temp, 0, DISTANCE_, temp, 0);
            temp += INTERVAL_;
        }
        
        //z axis

        
        int counter = 0;

        temp = 0;
        for (int i = 0; i < numWide + 1; i++){
            ZAxis[counter] = new graphableLine (temp, 0, 0, temp, 0, DISTANCE_);
            temp += INTERVAL_;
            counter++;
        }

        temp = 0;
        for (int i = 0; i < numLong + 1; i++){
            ZAxis[counter] = new graphableLine (0, temp, 0, 0, temp, DISTANCE_);
            temp += INTERVAL_;
            counter++;
        }

        //sideways z axis things
        temp = 0;
        for (int i = 0; i < numTall + 1; i++){
            ZAxis[counter] = new graphableLine (0, 0, temp, DISTANCE_, 0, temp);
            temp += INTERVAL_;
            counter++;
        }


        temp = 0;
        for (int i = 0; i < numTall + 1; i++){
            ZAxis[counter] = new graphableLine(0, 0, temp, 0, DISTANCE_, temp);
            temp += INTERVAL_;
            counter++;
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
