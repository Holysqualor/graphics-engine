import java.util.ArrayList;

public class DynamicObject {
    private final Vector position;
    private final Vector forward = new Vector(1, 0, 0);
    private final Vector right = new Vector(0, -1, 0);
    private final Vector up = new Vector(0, 0, 1);
    private static final Vector zAxis = new Vector(0,0,1);
    private static final double EPSILON = 1e-5;
    private final ArrayList<Block> scene = new ArrayList<>();
    private final double speed;


    public DynamicObject(Vector position, double speed) {
        this.position = position;
        this.speed = speed;
    }

    public ArrayList<Block> getScene() {
        return scene;
    }

    public void move(Direction direction) {
        Vector step;
        switch(direction) {
            case FORWARD:
                step = forward.copy();
                break;
            case BACK:
                step = forward.negate();
                break;
            case LEFT:
                step = right.negate();
                break;
            case RIGHT:
                step = right.copy();
                break;
            case UP:
                position.add(new Vector(0, 0, speed));
                return;
            case DOWN:
                position.add(new Vector(0, 0, -speed));
                return;
            default:
                return;
        }
        step.ignoreHeight();
        step.normalize();
        step.mul(speed);
        position.add(step);
    }

    public void rotate(Rotation rotation, double angel) {
        if(rotation == Rotation.HORIZONTAL) {
            forward.rotate(zAxis, angel);
            right.rotate(zAxis, angel);
            up.rotate(zAxis, angel);
        } else if(rotation == Rotation.VERTICAL) {
            Vector rotated = up.copy();
            rotated.rotate(right, angel);
            if(rotated.isOrthogonal(zAxis)) {
                return;
            }
            forward.rotate(right, angel);
            up.rotate(right, angel);
        }
    }

    public void putBlock() {
        double minDistance = Double.MAX_VALUE;
        Ray ray = new Ray(getPosition(), getForward());
        Block target = null;
        for(Block block : scene) {
            double distance = block.intersects(ray);
            if(distance != -1 && distance < minDistance) {
                minDistance = distance;
                target = block;
            }
        }
        if(target != null) {
            double x = position.getX() + minDistance * forward.getX();
            double y = position.getY() + minDistance * forward.getY();
            double z = position.getZ() + minDistance * forward.getZ();
            Vector[] bounds = target.getBounds();
            Vector blockPosition = target.getPosition();
            Direction face;
            if(Math.abs(x - bounds[0].getX()) < EPSILON) {
                face = Direction.BACK;
            } else if(Math.abs(x - bounds[1].getX()) < EPSILON) {
                face = Direction.FORWARD;
            } else if(Math.abs(y - bounds[0].getY()) < EPSILON) {
                face = Direction.RIGHT;
            } else if(Math.abs(y - bounds[1].getY()) < EPSILON) {
                face = Direction.LEFT;
            } else if(Math.abs(z - bounds[0].getZ()) < EPSILON) {
                face = Direction.DOWN;
            } else if(Math.abs(z - bounds[1].getZ()) < EPSILON) {
                face = Direction.UP;
            } else {
                return;
            }
            blockPosition.add(face.getVector());
            scene.add(new Block(blockPosition));
        }
    }

    public void breakBlock() {
        double minDistance = Double.MAX_VALUE;
        Ray ray = new Ray(getPosition(), getForward());
        Block target = null;
        for(Block block : scene) {
            double distance = block.intersects(ray);
            if(distance != -1 && distance < minDistance) {
                minDistance = distance;
                target = block;
            }
        }
        if(target != null) {
            scene.remove(target);
        }
    }

    public Vector getPosition() {
        return position.copy();
    }

    public Vector getForward() {
        return forward.copy();
    }

    public Vector getRight() {
        return right.copy();
    }

    public Vector getUp() {
        return up.copy();
    }
}
