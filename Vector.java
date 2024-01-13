public class Vector {
    private double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector copy() {
        return new Vector(x, y, z);
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void sub(Vector other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public void mul(double k) {
        x *= k;
        y *= k;
        z *= k;
    }

    public void normalize() {
        double length = Math.sqrt(x * x + y * y + z * z);
        if(length != 0) {
            x /= length;
            y /= length;
            z /= length;
        }
    }

    public Vector cross(Vector other) {
        return new Vector(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }

    public void rotate(Vector other, double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        double dotProduct = x * other.x + y * other.y + z * other.z;
        Vector crossProduct = cross(other);

        x = cosTheta * x + (1 - cosTheta) * dotProduct * other.x + sinTheta * crossProduct.x;
        y = cosTheta * y + (1 - cosTheta) * dotProduct * other.y + sinTheta * crossProduct.y;
        z = cosTheta * z + (1 - cosTheta) * dotProduct * other.z + sinTheta * crossProduct.z;
        normalize();
    }

    void ignoreHeight() {
        z = 0;
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
