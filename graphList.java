import java.util.ArrayList;

/**
 * An object that represents a list of both graphable and graphableLine objects
 * To be used as a container and is called during the GUI paint() function
 */
public class graphList {
    
    //stored not in graphables but in 2ds
    private ArrayList<graphable> g;
    private ArrayList<graphableLine> gL;

    /**
     * Main constructor for graphList. Takes two arrays and sets them to the local fields
     * @param g1 The graphable list
     * @param gL1 The graphableLine list
     */
    public graphList (ArrayList<graphable> g1, ArrayList<graphableLine> gL1){
        g = g1;
        gL = gL1;
    }

    /**
     * Getter method for the graphable list
     * @return The graphable list
     */
    public ArrayList<graphable> getPointList () {
        return g;
    }

    /**
     * Getter method for the graphableLine list
     * @return The graphable line list
     */
    public ArrayList<graphableLine> getLineList () {
        return gL;
    }
}
