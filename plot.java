/**
 * Stores the X, Y, and Z axis along with all the lines
 * as seen in matplotlib- essentially the base 3d graph function
 */
public class plot {
    

    //can have plot change size based on constructors
    private final double INTERVAL_ = 0.5;
    private final double DISTANCE_ = 0.5;

    private int numWide;
    private int numLong;
    private int numTall;

    private graphableLine[] XAxis;
    private graphableLine[] YAxis;
    private graphableLine[] ZAxis;
    
    public plot (){
        numWide = 10;
        numLong = 10;
        numTall = 10;
        
        defineAxisArr();
    }
    
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
            YAxis[i] = new graphableLine(0, temp, 0, DISTANCE_, temp, 0);
            temp += INTERVAL_;
        }
        
    }
    
    //now i have to define all the longitude/latitude/depthitude axis.
    public graphableLine[] getXAxis (){
        return XAxis;
    }

    public graphableLine[] getYAxis (){
        return YAxis;
    }

    public graphableLine[] getZAxis (){
        return ZAxis;
    }

}
