
public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    public Vector(double h, double v) {
        x = Math.cos(h) * Math.cos(v);
        y = Math.sin(h) * Math.cos(v);
        z = Math.sin(v);
    }

    public Vector(double h) {
        x = Math.cos(h);
        y = Math.sin(h);
        z = 0;
    }

    public void add(Vector vector) {
        x += vector.getX();
        y += vector.getY();
        z += vector.getZ();
    }

    public void setSpeed(double speed) {
        x *= speed;
        y *= speed;
        z *= speed;
    }

    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPosition(Vector other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
