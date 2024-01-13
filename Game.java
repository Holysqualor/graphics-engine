import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game {
    public static final int scrWidth = 640;
    public static final int scrHeight = 480;

    public static void main(String[] args) {
        Camera camera = new Camera();
        Screen screen = new Screen(camera);
        JFrame frame = new JFrame("Graphics");
        frame.setSize(scrWidth + 14, scrHeight + 37);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(screen);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage("");
        Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), "blankCursor");
        frame.setCursor(cursor);

        Timer timer = new Timer(16, e -> screen.repaint());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                timer.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });

        frame.addKeyListener(new MoveController(camera));
        frame.addKeyListener(new FrameController(frame));

        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }
}
