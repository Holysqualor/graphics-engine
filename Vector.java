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

    public boolean compare(Vector other) {
        return Math.round(x) == Math.round(other.x) && Math.round(y) == Math.round(other.y) && Math.round(z) == Math.round(other.z);
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

    public void rotate(double yaw, double pitch) {
        double[][] Z = {
                {Math.cos(yaw), -Math.sin(yaw), 0},
                {Math.sin(yaw), Math.cos(yaw), 0},
                {0, 0, 1}
        };
        double[][] Y = {
                {Math.cos(-pitch), 0, Math.sin(-pitch)},
                {0, 1, 0},
                {-Math.sin(-pitch), 0, Math.cos(-pitch)}
        };
        double[][] R = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    R[i][j] += Z[i][k] * Y[k][j];
                }
            }
        }
        Vector rotated = new Vector(
                R[0][0] * x + R[0][1] * y + R[0][2] * z,
                R[1][0] * x + R[1][1] * y + R[1][2] * z,
                R[2][0] * x + R[2][1] * y + R[2][2] * z
        );
        x = rotated.x;
        y = rotated.y;
        z = rotated.z;
    }

    void ignoreHeight() {
        z = 0;
    }
}
