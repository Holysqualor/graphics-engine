public class Block {
    private static final double halfExtent = 0.5;
    private final Vector min;
    private final Vector max;

    public Block(double x, double y, double z) {
        min = new Vector(x - halfExtent, y - halfExtent, z - halfExtent);
        max = new Vector(x + halfExtent, y + halfExtent, z + halfExtent);
    }

    public double intersects(Vector origin, Vector direction) {
        double t_min = (direction.getX() != 0) ? (min.getX() - origin.getX()) / direction.getX() : Double.NEGATIVE_INFINITY;
        double t_max = (direction.getX() != 0) ? (max.getX() - origin.getX()) / direction.getX() : Double.POSITIVE_INFINITY;

        double t;

        if(t_min > t_max) {
            t = t_min;
            t_min = t_max;
            t_max = t;
        }

        double ty_min = (direction.getY() != 0) ? (min.getY() - origin.getY()) / direction.getY() : Double.NEGATIVE_INFINITY;
        double ty_max = (direction.getY() != 0) ? (max.getY() - origin.getY()) / direction.getY() : Double.POSITIVE_INFINITY;

        if(ty_min > ty_max) {
            t = ty_min;
            ty_min = ty_max;
            ty_max = t;
        }

        if(t_min > ty_max || ty_min > t_max) {
            return -1;
        }

        if(ty_min > t_min) {
            t_min = ty_min;
        }

        if(ty_max < t_max) {
            t_max = ty_max;
        }

        double tz_min = (direction.getZ() != 0) ? (min.getZ() - origin.getZ()) / direction.getZ() : Double.NEGATIVE_INFINITY;
        double tz_max = (direction.getZ() != 0) ? (max.getZ() - origin.getZ()) / direction.getZ() : Double.POSITIVE_INFINITY;

        if(tz_min > tz_max) {
            t = tz_min;
            tz_min = tz_max;
            tz_max = t;
        }

        if(t_min > tz_max || tz_min > t_max) {
            return -1;
        }
        return t_min;
    }

}
