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
                camera.move(Direction.FORWARD);
                break;
            case KeyEvent.VK_S:
                camera.move(Direction.BACK);
                break;
            case KeyEvent.VK_A:
                camera.move(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
                camera.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                camera.move(Direction.UP);
                break;
            case KeyEvent.VK_SHIFT:
                camera.move(Direction.DOWN);
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
