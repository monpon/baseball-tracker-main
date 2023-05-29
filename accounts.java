import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * A class that represents a singular bat, and handles the accessing of them
 */
public class accounts {

    private Stack<String> accountStack = new Stack<String>();

    public accounts (){
        
        //access accountList.txt, store all values in a stack
    }



    public void displayAccounts (){

    }

    /**
     * Displays the bat for this account
     */
    public void viewAccount (){


    }
    
    public void logNew (){

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
}
