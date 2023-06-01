import java.util.ArrayList;
import java.awt.event.*;

/**
 * Object that represents the running of a graph. Handles 3d to 2d calculations
 */
public class runGraph implements KeyListener
{
    private camera c;
    private ArrayList<graphable> gList; //list of points to graph

    private ArrayList<graphable> g; //points to put in graphlist
    private ArrayList<graphableLine> gL; //lines to put in graphlist

    private graphList lGraphList;

    private final double DISTANCE_FIXED = 12;
    private final double WIDTH_OF_BALL = 2.85;
    private final double PIXEL_WIDTH_OF_BALL_AT_DISTANCE_FIXED_AWAY = 131.636;
    private final double focalLength = 55.4256842;

    private double a1 = -1;
    private double a2 = 0.4;

    private double F = 55;

    //takes any graphable object and plots it, using camera

    //create a plot object, graph each axis


    //given camera (which has 3d coords) and an angle, and a fixed FOV, 
    //and a list of graphable objects (also having 3d coords)
    //translate that into a 2d window

    /**
     * Main constructor for the runGraph class
     * @param iC The given camera input, which is a camera object
     * @param graphList The graphlist input which is a list of things that we want to graph
     */
    public runGraph (camera iC, ArrayList<graphable> graphList){
        c = iC;

        gList = new ArrayList<>();

        for (graphable i : graphList) {
            gList.add(i);   
        }
        

        g = new ArrayList<graphable>();
        gL = new ArrayList<graphableLine>();

        for (graphable i : gList){
            g.add(plotPoint(i));
        }

        

        //gL.add(plotLine(new graphableLine(0, 0, 0, 0, 500, 0)));
        //gL.add(plotLine(new graphableLine(50, 0, 0, 50, 500, 0)));
        
        lGraphList = new graphList(g, gL);

        //plotGraph();
        gL.add(plotLine(new graphableLine(0, 0, 0, 0, 0, 50)));
        gL.add(plotLine(new graphableLine(0, 0, 0, 0, 50, 0)));
        gL.add(plotLine(new graphableLine(0, 0, 0, 50, 0, 0)));

        lGraphList = new graphList(g, gL);
    }

    /**
     * Getter method for graphlist
     * @return The graphList 
     */
    public graphList getGraphList (){
        return lGraphList;
    }

    private void plotGraph (){

        //graphing axis
        plot p = new plot(10, 10, 10);
        
        for (graphableLine i : p.getXAxis()){
            gL.add(plotLine(i));
        }
        
        for (graphableLine i : p.getYAxis()){
            gL.add(plotLine(i));
        }
        
        for (graphableLine i : p.getZAxis()){
            gL.add(plotLine(i));
        }
        
        //graphing graphList
        
        //in 2d valuesA

    }
    
    private double[][] matrixMult(double[][] m1, double[][] m2)
    {
        double prodX = m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0] + m1[0][2] * m2[2][0];
        double prodY = m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1] + m1[0][2] * m2[2][1];
        double prodZ = m1[0][0] * m2[0][2] + m1[0][1] * m2[1][2] + m1[0][2] * m2[2][2];
        double[][] product = {{prodX, prodY, prodZ}};
        return product;
    }
    
    private graphable plotPoint (graphable a){ 

        //take a point P(x,y,z) in 3d space. 
        //use rotation matrix to move it to a point with angleW and angleL away in line of camera
        
        //then use formula on the points

        graphable r = new graphable(0, 0);

        double[] out = new double[2];

        double x0 = a.getX();
        double y0 = a.getY(); //chnged it from - to positive?
        double z0 = a.getZ();

        double x1 = x0 * Math.cos(a1) - y0 * Math.sin(a1) - c.getX();
        double y1 = x0 * Math.sin(a1) + y0 * Math.cos(a1);

        double y2 = y1 * Math.cos(a2) - z0 * Math.sin(a2) - c.getY();
        double z1 = y1 * Math.sin(a2) + z0 * Math.cos(a2) - c.getZ();

        if (y2 > 0 ){
            out[0] = F * x1/y2;
            out[1] = F * z1/y2;
            
            r = new graphable(out[0], out[1]);
        }


        // double cp = Math.cos(c.getAngleW());
        // double sp = Math.sin(c.getAngleW());
        // double ct = Math.cos(c.getAngleL());
        // double st = Math.sin(c.getAngleL());

        // double[][] pointMatrix = {{a.getX() - c.getX(), a.getY() - c.getY(), a.getZ() - c.getZ()}}; 
                                
        // double[][] rotationW = {{cp, sp, 0}, 
        //                         {-sp, cp, 0},
        //                         {0, 0 , 1}};
                                
        // double[][] rotationL = {{ct, 0, -st}, 
        //                         {0, 1, 0},
        //                         {st, 0, ct}};

        // double[][] newPointMatrix = matrixMult(pointMatrix, rotationW);
        // double[][] finalPointMatrix = matrixMult(newPointMatrix, rotationL);

        // double X = pointMatrix[0][0];
        // double Y = pointMatrix[0][1];
        // double Z = pointMatrix[0][2];
        
        // //a is the fov - justin you can sent the fov
        // out[0] = X/(Y * Math.tan(0.785398163));
        // out[1] = Z/(Y * Math.tan(0.785398163));

       
        // double x = focalLength * (a.getX() - c.getX()) / (a.getZ() - c.getZ());
        // double y = focalLength * (a.getY() - c.getY()) / (a.getZ() - c.getZ());

        //calculating trigs functions for further use
        //need to find l - the distance between the camera and the screen



        // double[] out = new double[2]; 

        // double l = 1/Math.tan(0.785398163);

        // double cp = Math.cos(c.getAngleW());
        // double sp = Math.sin(c.getAngleW());
        // double ct = Math.cos(c.getAngleL());
        // double st = Math.sin(c.getAngleL());

        
        // double dx = a.getX() - c.getX();
        // double dy = a.getY() - c.getY();
        // double dz = a.getZ() - c.getZ(); 
        // double S, X, Y, Z;

        // S = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) /
        //      ( - (ct * cp * dx) - (ct * sp * dy) + (st * dz));

        // if (S < 0 || S > l)
        // {
        //     return r;
        // }

        // X = l * ((S * dx) - (ct * cp));
        // Y = l * ((ct * sp) - (S * dy));
        // Z = l * ((S * dz) + st);

        // out[0] = (sp * X) + (cp * Y) + (400);
        // out[1] = (250) - (((cp*X)-(sp*Y))*st - (Z*ct));

        //r = new graphable(out[0], out[1]);


        return r;

        // r = new graphable(out[0], out[1]);
        // return r;

        // graphable r = new graphable((cp * X) + (sp * Y), (X * sp + Y * cp) * st + Z * ct);
        // // graphable r = new graphable(x, y);
        
        // g.add(r)
    } 

    private graphableLine plotLine (graphableLine a){

        graphable r1 = new graphable(a.getX(), a.getY(), a.getZ());
        graphable r2 = new graphable(a.getEX(), a.getEY(), a.getEZ());

        graphableLine r = new graphableLine(plotPoint(r1).getX(), plotPoint(r1).getY(),
                                            plotPoint(r2).getX(), plotPoint(r2).getY());
        
        return r;

        /* 
        double x = focalLength * (a.getX() - c.getX()) / (a.getZ() - c.getZ());
        double y = focalLength * (a.getY() - c.getY()) / (a.getZ() - c.getZ());

        
        double ex = focalLength * (a.getEX() - c.getX()) / (a.getEZ() - c.getZ());
        double ey = focalLength * (a.getEY() - c.getY()) / (a.getEZ() - c.getZ());
        
        graphableLine r = new graphableLine(x, y, ex, ey);
        gL.add(r);
        */


        // double cp = Math.cos(c.getAngleW());
        // double sp = Math.sin(c.getAngleW());
        // double ct = Math.cos(c.getAngleL());
        // double st = Math.sin(c.getAngleL());

        // double dx = a.getX() - c.getX();
        // double dy = a.getY() - c.getY();
        // double dz = a.getZ() - c.getZ(); 
        // double S, X, Y, Z;

        // double edx = a.getEX() - c.getX();
        // double edy = a.getEY() - c.getY();
        // double edz = a.getEZ() - c.getZ(); 
        // double eS, eX, eY, eZ;

        // S = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) / ( - (ct * cp * dx) - (ct * sp * dy) + (st * dz));
        // X = c.getX() + S * (a.getX() - c.getX());
        // Y = c.getY() + S * (a.getY() - c.getY());
        // Z = c.getZ() + S * (a.getZ() - c.getZ());
        
        // eS = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) / ( - (ct * cp * edx) - (ct * sp * edy) + (st * edz));
        // eX = c.getX() + eS * (a.getEX() - c.getX());
        // eY = c.getY() + eS * (a.getEY() - c.getY());
        // eZ = c.getZ() + eS * (a.getEZ() - c.getZ());

        // graphableLine r = new graphableLine((cp * X) + (sp * Y), (X * sp + Y * cp) * st + Z * ct,  
        //                                     (cp * eX) + (sp * eY), (eX * sp + eY * cp) * st + eZ * ct);

        // System.out.println(r);
        // gL.add(r);
    }

    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();
        if (keyCode == 37){
            a1 += -10;
            System.out.println("Changed a1 by -10");
        }
        if (keyCode == 39){
            a1 += 10;
            System.out.println("Changed a1 by 10");
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
