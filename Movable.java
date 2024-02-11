import java.util.LinkedList;
import java.util.List;

public class Movable {
    private final Vector position;
    private final Vector forward = new Vector(1f, 0f, 0f);
    private final Vector right = new Vector(0f, -1f, 0f);
    private final Vector up = new Vector(0f, 0f, 1f);
    private static final Vector zAxis = new Vector(0f,0f,1f);
    private final List<Block> scene = new LinkedList<>();
    private final float speed;

    public Movable(Vector position, float speed) {
        this.position = position;
        this.speed = speed;
    }

    public List<Block> getScene() {
        return scene;
    }

    public void moveForward() {
        Vector step = forward.copy();
        step.ignoreHeight();
        step.normalize();
        step.mul(speed);
        position.add(step);
    }

    public void moveBackward() {
        Vector step = forward.negate();
        step.ignoreHeight();
        step.normalize();
        step.mul(speed);
        position.add(step);
    }

    public void moveLeft() {
        Vector step = right.negate();
        step.ignoreHeight();
        step.normalize();
        step.mul(speed);
        position.add(step);
    }

    public void moveRight() {
        Vector step = right.copy();
        step.ignoreHeight();
        step.normalize();
        step.mul(speed);
        position.add(step);
    }

    public void moveUp() {
        position.add(new Vector(0,0, speed));
    }

    public void moveDown() {
        position.add(new Vector(0,0, -speed));
    }

    public void turn(Rotation rotation, double angel) {
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
        Ray ray = new Ray(getPosition(), getForward());
        Block target = null;
        float minDistance = Float.MAX_VALUE;
        for(Block block : scene) {
            float distance = block.intersects(ray);
            if(distance != -1 && distance < minDistance) {
                minDistance = distance;
                target = block;
            }
        }
        if(target != null) {
            Vector collisionPoint = new Vector(ray, minDistance);
            Vector blockPosition = target.getPosition();
            blockPosition.add(target.getFaceOfCollision(collisionPoint));
            scene.add(new Block(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()));
        }
    }

    public void breakBlock() {
        Ray ray = new Ray(getPosition(), getForward());
        Block target = null;
        float minDistance = Float.MAX_VALUE;
        for(Block block : scene) {
            float distance = block.intersects(ray);
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
