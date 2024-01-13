import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    public static final int width = 120;
    public static final int height = 30;

    public static final double aspect = (double) width / height;
    public static final double pixel = 9.0 / 17.0;

    public static void main(String[] args) {
        Camera camera = new Camera(new Vector(0,0,0));
        //camera.rotate(0, Math.toRadians(90));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Win10Pro\\Desktop\\screen.txt"));
            render(writer, camera);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void render(BufferedWriter writer, Camera camera) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                double x = (2.0 * j / (width - 1) - 1.0) * aspect * pixel;
                double y = 1.0 - 2.0 * i / (height - 1);
                Vector right = camera.getRight();
                right.mul(x);

                Vector up = camera.getUp();
                up.mul(y);

                Vector direction = camera.getForward();
                direction.add(right);
                direction.add(up);
                direction.normalize();

                try {
                    writer.write(getPixel(camera.getPosition(), direction));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                writer.write('\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static char getPixel(Vector origin, Vector direction) {
        final double step = 0.01;
        final double max = 5;
        direction.mul(step);
        for(double i = 0; i < max; i += step) {
            origin.add(direction);
            if(origin.compare(new Vector(5, 0, 0))) {
                if(i < max / 5) return '@';
                if(i < max / 4) return 'W';
                if(i < max / 3) return 'x';
                if(i < max / 2) return 'v';
                return 'n';
            }
        }
        return ' ';
    }
}
