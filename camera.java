
//not to be graphed
public class camera extends graphable{
    private double angleW;
    private double angleL;
    private double FOV_W;
    private double FOV_L;

    public camera (double tX, double tY, double tZ, double ang1, double ang2, double f, double f2){
        super(tX, tY, tZ);
        angleW = ang1;
        angleL = ang2;
        FOV_W = f;
        FOV_L = f2;
    }

    public void setAngleW (double newAng){
        angleW = newAng;
    }

    public void setAngleL (double newAng){
        angleL = newAng;
    }

    public void setPoint (double nX, double nY, double nZ){
        x = nX;
        y = nY;
        z = nZ;
    }
}
