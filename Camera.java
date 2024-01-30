public class Camera extends Movable {
    private static final double START_SPEED = 0.3;

    public Camera() {
        super(new Vector(0,0,0), START_SPEED);
    }
}
