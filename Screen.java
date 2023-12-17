import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {
    private static final double rayStep = 0.01;
    private static final double maxDist = 10;
    private final int pixelSize;
    private final Player player;

    public Screen(Player player) {
        this.player = player;
        pixelSize = 10;
    }

    private Color renderPixel(double h, double v) {
        Vector ray = new Vector(player), dir = new Vector(h, v);
        dir.setSpeed(rayStep);
        for(double i = 0; i < maxDist; i += rayStep) {
            if(player.getWorld().contains(ray) != null) {
                double brightness = 1.0 - (i / maxDist);
                return new Color((int) (brightness * 255), (int) (brightness * 255), (int) (brightness * 255));
            }
            ray.add(dir);
        }
        return Color.BLACK;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int h, v = (player.getVertical() + getHeight() / 2 / pixelSize + 360) % 360;
        for(int i = 0; i < getHeight(); i += pixelSize) {
            h = (player.getHorizontal() + getWidth() / 2 / pixelSize + 360) % 360;
            for(int j = 0; j < getWidth(); j += pixelSize) {
                g.setColor(renderPixel(Math.toRadians(h), Math.toRadians(v)));
                g.fillRect(j, i, pixelSize, pixelSize);
                h = (h + 359) % 360;
            }
            v = (v + 359) % 360;
        }
        g.setColor(Color.magenta);
        g.fillRect(getWidth() / 2 - 9, getHeight() / 2, 20, 1);
        g.setColor(Color.magenta);
        g.fillRect(getWidth() / 2, getHeight() / 2 - 10, 2, 20);
    }
}