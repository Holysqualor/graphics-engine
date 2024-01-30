import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import javax.swing.*;

public class Screen extends JPanel {
    public static final double MAX_DISTANCE = 10;
    public static int SKY = Color.CYAN.getRGB();
    private static final double DEEP = 0.5;

    private final BufferedImage image;
    private final Camera camera;
    private final int width, height;
    private final double aspect;

    public Screen(int width, int height, Camera camera) {
        this.camera = camera;
        this.width = width;
        this.height = height;
        aspect = (double) width / height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private int getPixel(Ray ray) {
        int color = SKY;
        double distance = Double.POSITIVE_INFINITY;
        for(Block block : camera.getScene()) {
            double collision = block.intersects(ray);
            if(collision != -1 && collision < distance) {
                distance = collision;
                color = block.getColor();
            }
        }
        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        IntStream.range(0, height).parallel().forEach(i -> IntStream.range(0, width).parallel().forEach(j -> {
            double x = (2.0 * j / width - 1.0) * aspect * DEEP;
            double y = (1.0 - 2.0 * i / height) * DEEP;
            Vector right = camera.getRight();
            right.mul(x);
            Vector up = camera.getUp();
            up.mul(y);
            Vector direction = camera.getForward();
            direction.add(right);
            direction.add(up);
            direction.normalize();
            image.setRGB(j, i, getPixel(new Ray(camera.getPosition(), direction)));
        }));
        g.drawImage(image, 0,0,this);
        g.setColor(Color.WHITE);
        g.fillRect(width / 2 - 9, height / 2, 20, 2);
        g.fillRect(width / 2, height / 2 - 9, 2, 20);
    }
}
