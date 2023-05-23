import java.util.ArrayList;

public class runGraph {

    private camera c;
    private ArrayList<graphable> gList;

    private ArrayList<graphable> g;
    private ArrayList<graphableLine> gL;

    private graphList lGraphList;

    //takes any graphable object and plots it, using camera

    //create a plot object, graph each axis


    //given camera (which has 3d coords) and an angle, and a fixed FOV, 
    //and a list of graphable objects (also having 3d coords)
    //translate that into a 2d window

    public runGraph (camera iC, ArrayList<graphable> graphList){
        c = iC;
        gList = graphList;
    }

    public graphList getGraphList (){
        return lGraphList;
    }

    public void plotGraph (){

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
        //put math here TODO

        
        //instead of adding a to g, add a new graphable object that is in 2d (and doesnt have a z)
        g.add(a);
    }

    private void plotLine (graphableLine a){
        //TODO put math here..
        gL.add(a);
    }


}
