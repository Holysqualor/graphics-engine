import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Screen extends JPanel {
    public static final double MAX_DISTANCE = 5;
    private static final double DEEP = 0.5;
    private final Camera camera;

    public Screen(Camera camera) {
        this.camera = camera;
    }

    private Color getPixel(Ray ray) {
        double distance = -1;
        for(Block block : camera.getScene()) {
            double collision = block.intersects(ray);
            if(distance == -1) {
                distance = collision;
            } else if(collision != -1 && collision < distance) {
                distance = collision;
            }
        }
        if(distance == -1) {
            return Color.BLACK;
        }
        int color = (int) ((1.0 - (distance / MAX_DISTANCE)) * 255);
        if(color < 0 || color > 255) {
            return Color.BLACK;
        }
        return new Color(color, 0, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth(), height = getHeight();
        BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final double aspect = (double) width / height;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                double x = (2.0 * j / (width - 1) - 1.0) * aspect * DEEP;
                double y = (1.0 - 2.0 * i / (height - 1)) * DEEP;
                Vector right = camera.getRight();
                right.mul(x);

                Vector up = camera.getUp();
                up.mul(y);

                Vector direction = camera.getForward();
                direction.add(right);
                direction.add(up);
                direction.normalize();
                bufferImage.setRGB(j, i, getPixel(new Ray(camera.getPosition(), direction)).getRGB());
            }
        }
        g.drawImage(bufferImage, 0, 0, this);
        g.setColor(Color.BLUE);
        g.fillRect(getWidth() / 2 - 9, getHeight() / 2, 20, 1);
        g.fillRect(getWidth() / 2, getHeight() / 2 - 10, 2, 20);
    }
}
