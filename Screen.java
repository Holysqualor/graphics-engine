import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import javax.swing.*;

public class Screen extends JPanel {
    public static int SKY = Color.CYAN.getRGB();

    private final BufferedImage image;
    private final Camera camera;
    private final int width, height;
    private final float[] xSign, ySign;

    public Screen(int width, int height, Camera camera) {
        this.camera = camera;
        this.width = width;
        this.height = height;
        float aspect = (float) width / height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        xSign = new float[width];
        ySign = new float[height];
        for(int i = 0; i < width; i++) {
            xSign[i] = (2f * i / width - 1f) * aspect * Settings.FOV;
        }
        for(int i = 0; i < height; i++) {
            ySign[i] = (1f - 2f * i / height) * Settings.FOV;
        }
    }

    private int getPixel(Ray ray) {
        int color = SKY;
        double distance = Double.POSITIVE_INFINITY;
        for(Block block : camera.getScene()) {
            double collision = block.intersects(ray);
            if(collision != -1 && collision < distance) {
                distance = collision;
                color = Color.PINK.getRGB();
            }
        }
        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        IntStream.range(0, height).parallel().forEach(i -> IntStream.range(0, width).parallel().forEach(j -> {
            Vector right = camera.getRight();
            right.mul(xSign[j]);
            Vector up = camera.getUp();
            up.mul(ySign[i]);
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
