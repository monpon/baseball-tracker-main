//not correct, thing u need to graph isnt 3d but instead a 2d object TODO >:(
import java.util.ArrayList;

public class graphList {
    
    //stored not in graphables but in 2ds
    private ArrayList<graphable> g;
    private ArrayList<graphableLine> gL;

    public graphList (ArrayList<graphable> g1, ArrayList<graphableLine> gL1){
        g = g1;
        gL = gL1;
    }

    public ArrayList<graphable> getPointList () {
        return g;
    }

    public ArrayList<graphableLine> getLineList () {
        return gL;
    }
}
