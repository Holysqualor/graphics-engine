import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConcreteTexture {
    private final int[][] side;
    private final int[][] bottom;
    private final int[][] top;
    private int width, height;

    public ConcreteTexture(String name) {
        side = loadMesh("textures/" + name + "/side.png");
        bottom = loadMesh("textures/" + name + "/bottom.png");
        top = loadMesh("textures/" + name + "/top.png");
    }

    private int[][] loadMesh(String pathname) {
        try {
            BufferedImage image = ImageIO.read(new File(pathname));
            width = image.getWidth();
            height = image.getHeight();
            int[][] mesh = new int[height][width];
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    mesh[y][x] = image.getRGB(x, y);
                }
            }
            return mesh;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    public int getPixel(int x, int y, Vector face) {
        y = height - y - 1;
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return Color.RED.getRGB();
        }
        if(face.getX() != 0 || face.getY() != 0) {
            return side[y][x];
        }
        if(face.getZ() == 1) {
            return top[y][x];
        }
        return bottom[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
