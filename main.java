import java.util.ArrayList;

public class main {
    public static void main (String args[]){
        //run calculations off coord.txt

        camera c = new camera (5, -5, 10, 0, -30, 90, 90);
        
        
        //create camera
        //create baseball

        //open parameters

        //constantly run update graph. 

        ArrayList<graphable> baseballDots = new ArrayList<>(); //add values to graph here..

        runGraph rG = new runGraph(c, baseballDots);

        runGraphGUI a = new runGraphGUI(rG);

        new runGraphGUI(rG).setVisible(true);


        //runGraph, run runGraph.updateGraph() everytime 
        //arrows are shifted for angle / camera movement
    }
}
