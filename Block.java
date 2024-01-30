public class Block {
    private final Vector position;
    public static final double EXTENT = 0.5;
    private static final double HALF_EXTENT = EXTENT * 0.5;
    private final Vector[] bounds;
    private final int color;

    public Block(Vector position, int color) {
        this.position = position;
        bounds = new Vector[] {
                new Vector(position.getX() - HALF_EXTENT, position.getY() - HALF_EXTENT, position.getZ() - HALF_EXTENT),
                new Vector(position.getX() + HALF_EXTENT, position.getY() + HALF_EXTENT, position.getZ() + HALF_EXTENT)
        };
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Vector getPosition() {
        return position.copy();
    }

    public Vector[] getBounds() {
        return bounds;
    }

    public double intersects(Ray ray) {
        double x_min = (bounds[ray.sign[0]].getX() - ray.origin.getX()) / ray.direction.getX();
        double x_max = (bounds[1 - ray.sign[0]].getX() - ray.origin.getX()) / ray.direction.getX();
        double y_min = (bounds[ray.sign[1]].getY() - ray.origin.getY()) / ray.direction.getY();
        double y_max = (bounds[1 - ray.sign[1]].getY() - ray.origin.getY()) / ray.direction.getY();
        if(x_min > y_max || y_min > x_max) {
            return -1;
        }
        x_min = Math.max(y_min, x_min);
        x_max = Math.min(x_max, y_max);
        double z_min = (bounds[ray.sign[2]].getZ() - ray.origin.getZ()) / ray.direction.getZ();
        double z_max = (bounds[1 - ray.sign[2]].getZ() - ray.origin.getZ()) / ray.direction.getZ();
        if((x_min > z_max) || (z_min > x_max)) {
            return -1;
        }
        double distance = Math.min(Math.max(x_min, z_min), Math.min(x_max, z_max));
        return (distance > 0 && distance < Screen.MAX_DISTANCE) ? distance : -1;
    }

}
