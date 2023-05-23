
/**
 * Class that represents a 3d object. 
 */
public class graphable {
    
    
    private double x;
    private double y;
    private double z;
    
    public graphable (double tX, double tY, double tZ){
        x = tX;
        y = tY;
        z = tZ;
    }
    
    public graphable (double tX, double tY){
        x = tX;
        y = tY;
        z = 0;
    }

    public double getX (){
        return x;
    }

    public double getY (){
        return y;
    }
    
    public double getZ (){
        return z;
    }

}