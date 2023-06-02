import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class calc {
    private final double TIME_BETWEEN_FRAMES = 1/240;
    private final double DISTANCE_FIXED = 12;
    private final double WIDTH_OF_BALL = 2.85;
    private final double PIXEL_WIDTH_OF_BALL_AT_DISTANCE_FIXED_AWAY = 131.636;
    private final double focalLength = PIXEL_WIDTH_OF_BALL_AT_DISTANCE_FIXED_AWAY * DISTANCE_FIXED/WIDTH_OF_BALL;
    
    //outputs to coords.txt
    //f, [[x, y, s], [dx, dy, ds]]
    //What I need from Monesh
        //pixelHeight of the ball from the ground at initial pos and 2nd frame pos
        //need pixelWidth1 and pixelWidth2 of the ball in the two different frames

    
    private graphable[] graphablePoints;
    
    public calc (){
        ArrayList<String> values = accessFile();
        
        ArrayList<String> outputs = new ArrayList<>();
        double x_velocity_inPixels = Double.parseDouble(values.get(0).substring(15, 16)) / TIME_BETWEEN_FRAMES;
        double z_velocity_inPixels = Double.parseDouble(values.get(0).substring(18, 19)) / TIME_BETWEEN_FRAMES;
        double y_velocity_inPixels = Double.parseDouble(values.get(0).substring(21, 22)) / TIME_BETWEEN_FRAMES;

        double theta = z_velocity_inPixels/x_velocity_inPixels;
        double alpha = z_velocity_inPixels/y_velocity_inPixels;
        double xval = x_velocity_inPixels * Math.cos(theta);
        double zval = z_velocity_inPixels * Math.sin(theta) - 0.5 * 9.81;

        double yval = y_velocity_inPixels * Math.cos(alpha);
        
        //As a function of t - sum on run a forloop or smth
        String out1 = xval + "* t";
        String out2 = zval + "* t^2";
        outputs.add(out1);
        outputs.add(out2);
    }

    private ArrayList<String> accessFile() {
        ArrayList<String> strArray = new ArrayList<String>();
        
        try {
            File myObj = new File("coord.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                strArray.add(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return strArray;
    }
    


    //creating an object, constructor looks at text file. 

    //then uses calc.getEquations(_____) that outputs an array of type graphable

    //just wanna let u know that the calc.getEquations(y) is an angle and not a set of points

}



