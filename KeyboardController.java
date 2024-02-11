import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    private final Camera camera;
    private final JFrame frame;

    KeyboardController(Camera camera, JFrame frame) {
        this.camera = camera;
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                camera.moveForward();
                break;
            case KeyEvent.VK_S:
                camera.moveBackward();
                break;
            case KeyEvent.VK_A:
                camera.moveLeft();
                break;
            case KeyEvent.VK_D:
                camera.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                camera.moveUp();
                break;
            case KeyEvent.VK_SHIFT:
                camera.moveDown();
                break;
            case KeyEvent.VK_ESCAPE:
                SwingUtilities.invokeLater(() -> {
                    frame.dispose();
                    System.exit(0);
                });
                break;
        }
    }
}
