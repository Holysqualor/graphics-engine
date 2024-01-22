public enum Direction {
    FORWARD(1, 0, 0),
    BACK(-1, 0, 0),
    LEFT(0, 1, 0),
    RIGHT(0, -1, 0),
    UP(0, 0, 1),
    DOWN(0, 0, -1);

    private final Vector vector;

    Direction(double x, double y, double z) {
        vector = new Vector(x, y, z);
    }

    public Vector getVector() {
        return vector;
    }
}
