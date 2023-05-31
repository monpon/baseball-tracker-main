import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class CubeProjection extends Canvas {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int CUBE_DISTANCE = 10;
    private static final int FOV_ANGLE = 45;
    private static final int LINE_WIDTH = 3;
    private static final int[][][] FACES = {
            // Bottom
            {
                    { -1, 1, -1 },
                    { 1, 1, -1 },
                    { 1, 1, 1 },
                    { -1, 1, 1 }
            },
            // Top
            {
                    { -1, -1, -1 },
                    { 1, -1, -1 },
                    { 1, -1, 1 },
                    { -1, -1, 1 }
            },
            // Front
            {
                    { -1, -1, 1 },
                    { 1, -1, 1 },
                    { 1, 1, 1 },
                    { -1, 1, 1 }
            },
            // Back
            {
                    { -1, -1, -1 },
                    { 1, -1, -1 },
                    { 1, 1, -1 },
                    { -1, 1, -1 }
            }
    };

    private CubeProjection() {
        JFrame frame = new JFrame("Cube Projection");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CubeProjection();
    }

    private double transform2DTo3D(double xy, double z) {
        double angleRadians = Math.toRadians(FOV_ANGLE);
        return xy / (z * Math.tan(angleRadians / 2));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);

        double mouseXratio = (double) getMousePosition().x / WIDTH * Math.PI;
        double mouseYratio = (double) getMousePosition().y / HEIGHT * Math.PI;

        for (int[][] face : FACES) {
            int[][] face2D = new int[face.length][2];
            for (int i = 0; i < face.length; i++) {
                double x = face[i][0];
                double y = face[i][1];
                double z = face[i][2];
                double rotatedX = Math.cos(mouseYratio) * Math.cos(mouseXratio) * x +
                        (Math.cos(mouseYratio) * Math.sin(mouseXratio) * Math.sin(0) - Math.sin(mouseYratio) * Math.cos(0)) * y +
                        (Math.cos(mouseYratio) * Math.sin(mouseXratio) * Math.cos(0) + Math.sin(mouseYratio) * Math.sin(0)) * z;
                double rotatedY = Math.sin(mouseYratio) * Math.cos(mouseXratio) * x +
                        (Math.sin(mouseYratio) * Math.sin(mouseXratio) * Math.sin(0) + Math.cos(mouseYratio) * Math.cos(0)) * y +
                        (Math.sin(mouseYratio) * Math.sin(mouseXratio) * Math.cos(0) - Math.cos(mouseYratio) * Math.sin(0)) * z;
                double rotatedZ = -Math.sin(mouseXratio) * x +
                        Math.cos(mouseXratio) * Math.sin(0) * y +
                        Math.cos(mouseXratio) * Math.cos(0) * z;
                double transformedX = transform2DTo3D(rotatedX, rotatedZ);
                double transformedY = transform2DTo3D(rotatedY, rotatedZ);
                int screenX = (int) (transformedX * WIDTH + WIDTH / 2);
                int screenY = (int) (transformedY * HEIGHT + HEIGHT / 2);
                face2D[i] = new int[]{screenX, screenY};
            }
            drawPolygon(g, face2D);
        }
    }

    private void drawPolygon(Graphics g, int[][] points) {
        g.drawPolygon(points[0], points[1], points.length);
    }
}