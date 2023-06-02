import java.util.ArrayList;

public class main {
    public static void main (String args[]){
        //run calculations off coord.txt
        //create calc object

        camera c = new camera (0, 16, 4, 0.7999999999999987, 6.200000000000002);
        
        
        //create camera
        //create baseball
        
        //open parameters

        ArrayList<graphable> baseballDots = new ArrayList<>(); //add values to graph here..
        
        baseballDots.add(new graphable(5, 5, 5));

        //add all calc.getEquations into baseballDots, so it should just be a bunch of 3d points
        
        
        
        //dk if this is necessary to be completely honest
        
        new runGraphGUI(c, baseballDots).setVisible(true);

        //arrows are shifted for angle / camera movement
    }
}
