public class Camera extends DynamicObject {
    public Camera(double x, double y, double z) {
        super(new Vector(x,y,z), 0.1);
    }

    public Camera() {
        super(new Vector(0,0,0), 0.1);
    }
}
