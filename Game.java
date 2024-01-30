import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame {
    private static final int scrWidth = 1280;
    private static final int scrHeight = 720;
    private static final double sensitivity = 50;

    public Game() {
        super("3D Render");
        Camera camera = new Camera();
        camera.getScene().add(new Block(new Vector(2, 0, 0), Color.green.getRGB()));
        Screen screen = new Screen(scrWidth, scrHeight, camera);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClicked(e, camera);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMoved(e, camera);
            }
        };

        setSize(scrWidth, scrHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        add(screen);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage("");
        Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), "blankCursor");
        setCursor(cursor);

        Timer timer = new Timer(16, e -> screen.repaint());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                timer.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });

        addKeyListener(new KeyboardController(camera, this));
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        addMouseWheelListener(e -> {
            if(e.getWheelRotation() < 0) {
                camera.takeNextBlock();
            } else {
                camera.takePrevBlock();
            }
        });

        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    private void handleMouseClicked(MouseEvent e, Camera camera) {
        int key = e.getButton();
        if(key == MouseEvent.BUTTON1) {
            camera.breakBlock();
        } else if(key == MouseEvent.BUTTON3) {
            camera.putBlock();
        }
    }

    private void handleMouseMoved(MouseEvent e, Camera camera) {
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;
        int deltaX = e.getX() - halfWidth;
        int deltaY = e.getY() - halfHeight;
        double aspect = (double) getWidth() / getHeight();
        if(deltaX != 0) {
            camera.rotate(Rotation.HORIZONTAL, Math.toRadians(sensitivity * deltaX / halfWidth * aspect));
        }
        if(deltaY != 0) {
            camera.rotate(Rotation.VERTICAL, Math.toRadians(sensitivity * deltaY / halfHeight) * aspect);
        }
        try {
            Robot robot = new Robot();
            robot.mouseMove(getX() + halfWidth, getY() + halfHeight);
        } catch (AWTException awtException) {
            System.out.println(awtException.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);

    }
}
