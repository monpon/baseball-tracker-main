import java.util.ArrayList;

public class main {
    public static void main (String args[]){
        //run calculations off coord.txt
        //create calc object

        camera c = new camera (5, 5, 0, 0, 0);
        
        
        //create camera
        //create baseball
        
        //open parameters

        ArrayList<graphable> baseballDots = new ArrayList<>(); //add values to graph here..

        //add all calc.getEquations into baseballDots, so it should just be a bunch of 3d points
        
        runGraph rG = new runGraph(c, baseballDots);
        
        
        new runGraphGUI(rG).setVisible(true);

        //arrows are shifted for angle / camera movement
    }
}
