import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static int SCREEN_WIDTH = 640;
    public static int SCREEN_HEIGHT = 480;

    public static int prevMouseX = 0;
    public static int prevMouseY = 0;
    public static JFrame frame = new JFrame("Graphics");
    public static Player player = new Player();

    public static void main(String[] args) {
        World world = new World();
        player.setWorld(world);
        world.put(new Vector(5, 0, 0));
        world.put(new Vector(5, 1, 0));
        world.put(new Vector(5, -1, 0));
        world.put(new Vector(5, 0, 1));
        world.put(new Vector(5, 0, 2));
        Screen screen = new Screen(player);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClicked(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMoved(e);
            }
        };
        frame.setSize(SCREEN_WIDTH + 14, SCREEN_HEIGHT + 37);
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
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyboard(e.getKeyCode());
            }
        });
        frame.addMouseListener(adapter);
        frame.addMouseMotionListener(adapter);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

    private static void handleKeyboard(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                player.stepForward();
                break;
            case KeyEvent.VK_S:
                player.stepBack();
                break;
            case KeyEvent.VK_A:
                player.stepLeft();
                break;
            case KeyEvent.VK_D:
                player.stepRight();
                break;
            case KeyEvent.VK_SPACE:
                player.stepUp();
                break;
            case KeyEvent.VK_SHIFT:
                player.stepDown();
                break;
            case KeyEvent.VK_ESCAPE:
                SwingUtilities.invokeLater(() -> {
                    frame.dispose();
                    System.exit(0);
                });
                break;
        }
    }

    private static void handleMouseMoved(MouseEvent e) {
        int mouseY = e.getY();
        int mouseX = e.getX();
        int deltaY = mouseY - frame.getHeight() / 2;
        int deltaX = mouseX - frame.getWidth() / 2;
        if (deltaY < 0) {
            player.turnUp();
        } else if (deltaY > 0) {
            player.turnDown();
        }
        if (deltaX < 0) {
            player.turnLeft();
        } else if (deltaX > 0) {
            player.turnRight();
        }
        try {
            Robot robot = new Robot();
            robot.mouseMove(frame.getX() + frame.getWidth() / 2, frame.getY() + frame.getHeight() / 2);
        } catch (AWTException awtException) {
            System.out.println(awtException.getMessage());
        }
        prevMouseY = frame.getY() + frame.getHeight() / 2;
        prevMouseX = frame.getX() + frame.getWidth() / 2;
    }

    private static void handleMouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            player.dropBlock();
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            player.removeBlock();
        }
    }
}
