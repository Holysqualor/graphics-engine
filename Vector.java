public class Vector {
    private float x, y, z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Ray ray, float distance) {
        x = ray.origin.getX() + ray.direction.getX() * distance;
        y = ray.origin.getY() + ray.direction.getY() * distance;
        z = ray.origin.getZ() + ray.direction.getZ() * distance;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Vector vector = (Vector) obj;
        return Float.compare(x, vector.x) == 0 && Float.compare(y, vector.y) == 0 && Float.compare(z, vector.z) == 0;
    }

    public Vector negate() {
        return new Vector(-x, -y, -z);
    }

    public Vector copy() {
        return new Vector(x, y, z);
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void mul(float k) {
        x *= k;
        y *= k;
        z *= k;
    }

    public void normalize() {
        float len = (float) Math.sqrt(x * x + y * y + z * z);
        if(len != 0) {
            x /= len;
            y /= len;
            z /= len;
        }
    }

    public Vector cross(Vector other) {
        return new Vector(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }

    public void rotate(Vector other, float theta) {
        float cosTheta = (float) Math.cos(theta);
        float sinTheta = (float) Math.sin(theta);

        float dotProduct = x * other.x + y * other.y + z * other.z;
        Vector crossProduct = cross(other);

        x = cosTheta * x + (1 - cosTheta) * dotProduct * other.x + sinTheta * crossProduct.x;
        y = cosTheta * y + (1 - cosTheta) * dotProduct * other.y + sinTheta * crossProduct.y;
        z = cosTheta * z + (1 - cosTheta) * dotProduct * other.z + sinTheta * crossProduct.z;
        normalize();
    }

    public boolean isOrthogonal(Vector other) {
        return (int) (x * other.x + y * other.y + z * other.z + 0.5) == 0;
    }

    void ignoreHeight() {
        z = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
