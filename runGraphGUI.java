import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
/**
 * Class that deals with all the GUI portions of our code
 */
public class runGraphGUI extends JFrame {

    private runGraph rG;
    private final int DOT_SIZE = 5;

    /**
     * Constructor for the runGraphGUI class
     * @param rG2 A runGraph object, which should have all the points to be graphed
     */
    public runGraphGUI(camera c, ArrayList<graphable> baseballDots) {
        super("3D Representation of Baseball");

        rG = new runGraph(c, baseballDots);
        
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    
    void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawLine(x1 + 400, y1 + 250, x2 + 400, y2 + 250);
        
    }
    
    void drawDot(Graphics g, int x, int y) {
        Graphics g2d = (Graphics2D) g;
        g2d.fillOval(x, y, DOT_SIZE, DOT_SIZE);
    }

    /**
     * The default paint function that is called every time the window is shifted, and etc.
     */
    public void paint(Graphics g) {
        super.paint(g);

        graphList a = rG.getGraphList();
        
        ArrayList<graphableLine> lineList = a.getLineList();

        System.out.println(lineList);

        for (graphableLine i : lineList){
            System.out.println("Drawing line at " + i.getX() + " " + i.getY() + " " + i.getEX() + " " + i.getEY());
            drawLine(g, (int) i.getX(), (int) i.getY(), (int) i.getEX(), (int) i.getEY());
        }

        ArrayList<graphable> pointList = a.getPointList();

        for (graphable i : pointList){
            System.out.println("Drawing point at " + i);
            drawDot(g, (int) i.getX(), (int) i.getY());
        }

    }
    
    /* 
    public static void main(String[] args) {
        
        runGraphGUI a = new runGraphGUI(r);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new runGraphGUI().setVisible(true);
            }
        });

        

    }
    */
}