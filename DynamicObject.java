public class DynamicObject {
    private final Vector position;
    private final Vector forward = new Vector(1, 0, 0);
    private final Vector right = new Vector(0, -1, 0);
    private final Vector up = new Vector(0, 0, 1);
    private static final Vector oz = new Vector(0,0,1);
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

    public void rotate(Direction direction, double angel) {
        if(direction == Direction.LEFT) {
            forward.rotate(oz, -angel);
            right.rotate(oz, -angel);
            up.rotate(oz, -angel);
        } else if(direction == Direction.RIGHT) {
            forward.rotate(oz, angel);
            right.rotate(oz, angel);
            up.rotate(oz, angel);
        } else if(direction == Direction.UP) {
            forward.rotate(right, -angel);
            up.rotate(right, -angel);
        } else if(direction == Direction.DOWN) {
            forward.rotate(right, angel);
            up.rotate(right, angel);
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

    public double getSpeed() {
        return speed;
    }
}
