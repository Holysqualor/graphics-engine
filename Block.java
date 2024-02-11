public class Block {
    private final float[][] bounds;

    public Block(float x, float y, float z) {
        bounds = new float[][] {
                {x, y, z},
                {x + 1, y + 1, z + 1}
        };
    }

    public Vector getFaceOfCollision(Vector collision) {
        if(Math.abs(collision.getX() - getMinX()) < 1e-5) {
            return new Vector(-1, 0, 0);
        }
        if(Math.abs(collision.getX() - getMaxX()) < 1e-5) {
            return new Vector(1, 0, 0);
        }
        if(Math.abs(collision.getY() - getMinY()) < 1e-5) {
            return new Vector(0, -1, 0);
        }
        if(Math.abs(collision.getY() - getMaxY()) < 1e-5) {
            return new Vector(0, 1, 0);
        }
        if(Math.abs(collision.getZ() - getMinZ()) < 1e-5) {
            return new Vector(0, 0, -1);
        }
        if(Math.abs(collision.getZ() - getMaxZ()) < 1e-5) {
            return new Vector(0, 0, 1);
        }
        return new Vector(0,0,0);
    }

    public float intersects(Ray ray) {
        float xMin = (bounds[ray.sign[0]][0] - ray.origin.getX()) / ray.direction.getX();
        float xMax = (bounds[1 - ray.sign[0]][0] - ray.origin.getX()) / ray.direction.getX();
        float yMin = (bounds[ray.sign[1]][1] - ray.origin.getY()) / ray.direction.getY();
        float yMax = (bounds[1 - ray.sign[1]][1] - ray.origin.getY()) / ray.direction.getY();
        if(xMin > yMax || yMin > xMax) {
            return -1;
        }
        xMin = Math.max(yMin, xMin);
        xMax = Math.min(xMax, yMax);
        float zMin = (bounds[ray.sign[2]][2] - ray.origin.getZ()) / ray.direction.getZ();
        float zMax = (bounds[1 - ray.sign[2]][2] - ray.origin.getZ()) / ray.direction.getZ();
        if((xMin > zMax) || (zMin > xMax)) {
            return -1;
        }
        float distance = Math.min(Math.max(xMin, zMin), Math.min(xMax, zMax));
        return (distance > 0 && distance < Settings.DRAW_DISTANCE) ? distance : -1.0f;
    }

    public Vector getPosition() {
        return new Vector(bounds[0][0], bounds[0][1], bounds[0][2]);
    }

    public float getMinX() {
        return bounds[0][0];
    }

    public float getMinY() {
        return bounds[0][1];
    }

    public float getMinZ() {
        return bounds[0][2];
    }

    public float getMaxX() {
        return bounds[1][0];
    }

    public float getMaxY() {
        return bounds[1][1];
    }

    public float getMaxZ() {
        return bounds[1][2];
    }
}
