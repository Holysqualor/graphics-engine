public enum Direction {
    FORWARD(Block.EXTENT, 0, 0),
    BACK(-Block.EXTENT, 0, 0),
    LEFT(0, Block.EXTENT, 0),
    RIGHT(0, -Block.EXTENT, 0),
    UP(0, 0, Block.EXTENT),
    DOWN(0, 0, -Block.EXTENT);

    private final Vector vector;

    Direction(double x, double y, double z) {
        vector = new Vector(x, y, z);
    }

    public Vector getVector() {
        return vector;
    }
}
