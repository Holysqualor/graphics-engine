import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveController extends KeyAdapter {
    private final Camera camera;
    private static final double sensitivity = 50;

    MoveController(Camera camera) {
        this.camera = camera;
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
            case KeyEvent.VK_UP:
                camera.rotate(Direction.UP, Math.toRadians(sensitivity / 10));
                break;
            case KeyEvent.VK_DOWN:
                camera.rotate(Direction.DOWN, Math.toRadians(sensitivity / 10));
                break;
            case KeyEvent.VK_LEFT:
                camera.rotate(Direction.LEFT, Math.toRadians(sensitivity / 10));
                break;
            case KeyEvent.VK_RIGHT:
                camera.rotate(Direction.RIGHT, Math.toRadians(sensitivity / 10));
                break;
        }
    }
}
