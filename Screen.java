import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {
    private static final double max = 5;
    private static final double deep = 0.5;
    private static final Block block = new Block(3, 0, 0);
    private final Camera camera;

    public Screen(Camera camera) {
        this.camera = camera;
    }

    private Color getPixel(Vector origin, Vector direction) {
        double distance = block.intersects(origin, direction);
        if(distance != -1 && distance < max) {
            int color = (int) ((1.0 - (distance / max)) * 255);
            if(color < 0 || color > 255) {
                return Color.BLACK;
            }
            return new Color(color, color, color);
        }
        return Color.BLACK;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final double aspect = (double) getWidth() / getHeight();
        for(int i = 0; i < getHeight(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                double x = (2.0 * j / (getWidth() - 1) - 1.0) * aspect * deep;
                double y = (1.0 - 2.0 * i / (getHeight() - 1)) * deep;
                Vector right = camera.getRight();
                right.mul(x);

                Vector up = camera.getUp();
                up.mul(y);

                Vector direction = camera.getForward();
                direction.add(right);
                direction.add(up);
                direction.normalize();
                g.setColor(getPixel(camera.getPosition(), direction));
                g.fillRect(j, i, 1, 1);
            }
        }
        g.setColor(Color.magenta);
        g.fillRect(getWidth() / 2 - 9, getHeight() / 2, 20, 1);
        g.setColor(Color.magenta);
        g.fillRect(getWidth() / 2, getHeight() / 2 - 10, 2, 20);
    }
}
