public class Camera extends DynamicObject {
    private static final double START_SPEED = 0.1;

    public Camera(double x, double y, double z) {
        super(new Vector(x,y,z), START_SPEED);
    }

    public Camera() {
        super(new Vector(0,0,0), START_SPEED);
    }
}
