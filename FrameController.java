import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrameController  extends KeyAdapter {
    private final JFrame frame;

    public FrameController(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            SwingUtilities.invokeLater(() -> {
                frame.dispose();
                System.exit(0);
            });
        }
    }
}
