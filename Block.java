public class Block {
    private final Vector position;
    public static final double HALF_EXTENT = 0.5;
    private final Vector[] bounds;

    public Block(double x, double y, double z) {
        position = new Vector(x,y,z);
        bounds = new Vector[] {
                new Vector(x - HALF_EXTENT, y - HALF_EXTENT, z - HALF_EXTENT),
                new Vector(x + HALF_EXTENT, y + HALF_EXTENT, z + HALF_EXTENT)
        };
    }

    public Block(Vector position) {
        this.position = position;
        bounds = new Vector[] {
                new Vector(position.getX() - HALF_EXTENT, position.getY() - HALF_EXTENT, position.getZ() - HALF_EXTENT),
                new Vector(position.getX() + HALF_EXTENT, position.getY() + HALF_EXTENT, position.getZ() + HALF_EXTENT)
        };
    }

    public Vector getPosition() {
        return position.copy();
    }

    public Vector[] getBounds() {
        return bounds;
    }

    public double intersects(Ray ray) {
        double x_min = (bounds[ray.sign[0]].getX() - ray.orig.getX()) * ray.dir.getX();
        double x_max = (bounds[1 - ray.sign[0]].getX() - ray.orig.getX()) * ray.dir.getX();
        double y_min = (bounds[ray.sign[1]].getY() - ray.orig.getY()) * ray.dir.getY();
        double y_max = (bounds[1 - ray.sign[1]].getY() - ray.orig.getY()) * ray.dir.getY();
        if(x_min > y_max || y_min > x_max) {
            return -1;
        }
        x_min = Math.max(y_min, x_min);
        x_max = Math.min(x_max, y_max);
        double z_min = (bounds[ray.sign[2]].getZ() - ray.orig.getZ()) * ray.dir.getZ();
        double z_max = (bounds[1 - ray.sign[2]].getZ() - ray.orig.getZ()) * ray.dir.getZ();
        if((x_min > z_max) || (z_min > x_max)) {
            return -1;
        }
        double distance = Math.min(Math.max(x_min, z_min), Math.min(x_max, z_max));
        return (distance > 0) ? distance : -1;
    }

}
