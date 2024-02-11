public class Camera extends Movable {
    private static final float START_SPEED = 0.3f;

    public Camera() {
        super(new Vector(0.0f,0.0f,0.0f), START_SPEED);
    }
}
