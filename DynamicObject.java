public class DynamicObject {
    private final Vector position;
    private final Vector forward = new Vector(1, 0, 0);
    private Vector right = new Vector(0, -1, 0);
    private Vector up = new Vector(0, 0, 1);
    private final double speed;

    public DynamicObject(Vector position, double speed) {
        this.position = position;
        this.speed = speed;
    }

    public void move(Direction direction) {
        if(direction == Direction.FORWARD) {
            Vector step = forward.copy();
            step.ignoreHeight();
            step.normalize();
            step.mul(speed);
            position.add(step);
        } else if(direction == Direction.BACK) {
            Vector step = forward.copy();
            step.ignoreHeight();
            step.normalize();
            step.mul(speed);
            position.sub(step);
        } else if(direction == Direction.LEFT) {
            Vector step = right.copy();
            step.ignoreHeight();
            step.normalize();
            step.mul(speed);
            position.sub(step);
        } else if(direction == Direction.RIGHT) {
            Vector step = right.copy();
            step.ignoreHeight();
            step.normalize();
            step.mul(speed);
            position.add(step);
        } else if(direction == Direction.UP) {
            position.add(new Vector(0, 0, speed));
        } else if(direction == Direction.DOWN) {
            position.sub(new Vector(0, 0, speed));
        }
    }

    public void rotate(double yaw, double pitch) {
        forward.rotate(yaw, pitch);
        forward.normalize();
        right = forward.cross(up);
        right.normalize();
        up = right.cross(forward);
        up.normalize();
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

    public double getSpeed() {
        return speed;
    }
}
