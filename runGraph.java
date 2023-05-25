import java.util.ArrayList;

/**
 * Object that represents the running of a graph
 */
public class runGraph {

    private camera c;
    private ArrayList<graphable> gList; //list of points to graph

    private ArrayList<graphable> g; //points to put in graphlist
    private ArrayList<graphableLine> gL; //lines to put in graphlist

    private graphList lGraphList;

    //takes any graphable object and plots it, using camera

    //create a plot object, graph each axis


    //given camera (which has 3d coords) and an angle, and a fixed FOV, 
    //and a list of graphable objects (also having 3d coords)
    //translate that into a 2d window

    
    public runGraph (camera iC, ArrayList<graphable> graphList){
        c = iC;
        gList = graphList;

        g = new ArrayList<graphable>();
        gL = new ArrayList<graphableLine>();

        plotGraph();
    }

    public graphList getGraphList (){
        return lGraphList;
    }

    private void plotGraph (){

        //graphing axis
        plot p = new plot(10, 10, 10);
        graphableLine a[] = p.getXAxis();
        
        for (graphableLine i : a){
            plotLine(i);
        }
        a = p.getYAxis();
        for (graphableLine i : a){
            plotLine(i);
        }        
        a = p.getZAxis();
        for (graphableLine i : a){
            plotLine(i);
        }
        
        //graphing graphList
        for (graphable i : gList){
            plotPoint(i);
        }
        
        //in 2d values
        lGraphList = new graphList(g, gL);

    }

    private void plotPoint (graphable a){

        //calculating trigs functions for further use
        double cp = Math.cos(c.getAngleW());
        double sp = Math.sin(c.getAngleW());
        double ct = Math.cos(c.getAngleL());
        double st = Math.sin(c.getAngleL());

        double dx = a.getX() - c.getX();
        double dy = a.getY() - c.getY();
        double dz = a.getZ() - c.getZ(); 
        double S, X, Y, Z;

        S = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) / ( - (ct * cp * dx) - (ct * sp * dy) + (st * dz));
        X = c.getX() + S * (a.getX() - c.getX());
        Y = c.getY() + S * (a.getY() - c.getY());
        Z = c.getZ() + S * (a.getZ() - c.getZ());

        graphable r = new graphable((cp * X) + (sp * Y), (X * sp + Y * cp) * st + Z * ct);
        
        g.add(r);
    }

    private void plotLine (graphableLine a){

        double cp = Math.cos(c.getAngleW());
        double sp = Math.sin(c.getAngleW());
        double ct = Math.cos(c.getAngleL());
        double st = Math.sin(c.getAngleL());

        double dx = a.getX() - c.getX();
        double dy = a.getY() - c.getY();
        double dz = a.getZ() - c.getZ(); 
        double S, X, Y, Z;

        double edx = a.getEX() - c.getX();
        double edy = a.getEY() - c.getY();
        double edz = a.getEZ() - c.getZ(); 
        double eS, eX, eY, eZ;

        S = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) / ( - (ct * cp * dx) - (ct * sp * dy) + (st * dz));
        X = c.getX() + S * (a.getX() - c.getX());
        Y = c.getY() + S * (a.getY() - c.getY());
        Z = c.getZ() + S * (a.getZ() - c.getZ());

        eS = ((((ct * cp * ct * cp))) + (ct * sp * ct * sp) + (st * st)) / ( - (ct * cp * edx) - (ct * sp * edy) + (st * edz));
        eX = c.getX() + eS * (a.getEX() - c.getX());
        eY = c.getY() + eS * (a.getEY() - c.getY());
        eZ = c.getZ() + eS * (a.getEZ() - c.getZ());

        graphableLine r = new graphableLine((cp * X) + (sp * Y), (X * sp + Y * cp) * st + Z * ct,  (cp * eX) + (sp * eY), (eX * sp + eY * cp) * st + eZ * ct);

        gL.add(r);
    }


}
