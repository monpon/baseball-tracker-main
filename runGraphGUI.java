import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 

public class runGraphGUI extends JFrame {

    private runGraph rG;
    private final int DOT_SIZE = 5;

    public runGraphGUI(runGraph rG2) {
        super("3D Representation of Baseball");
        
        rG = rG2;
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    
    void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawLine(x1, y1, x2, y2);
        
    }
    
    void drawDot(Graphics g, int x, int y) {
        Graphics g2d = (Graphics2D) g;
        g2d.drawOval(x, y, DOT_SIZE, DOT_SIZE);
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        graphList a = rG.getGraphList();
        
        ArrayList<graphableLine> lineList = a.getLineList();

        for (graphableLine i : lineList){
            drawLine(g, (int) i.getX(), (int) i.getY(), (int) i.getEX(), (int) i.getEY());
        }

        ArrayList<graphable> pointList = a.getPointList();

        for (graphable i : pointList){
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