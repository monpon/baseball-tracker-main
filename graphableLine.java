


public class graphableLine extends graphable {
    //treat x, y, and z as start

    private double endX;
    private double endY;
    private double endZ;

    public graphableLine (double tX, double tY, double tZ, double eX, double eY, double eZ){
        super(tX, tY, tZ);
        endX = eX;
        endY = eY;
        endZ = eZ;
    }

    public graphableLine (double tX, double tY, double eX, double eY){
        super(tX, tY);
        endX = eX;
        endY = eY;
        endZ = 0;
    }

    public double getEX (){
        return endX;
    }

    public double getEY (){
        return endY;
    }
    
    public double getEZ (){
        return endZ;
    }
    
}
