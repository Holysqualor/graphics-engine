public class Player extends Vector {
    private int h = 0;
    private int v = 0;
    private World world;

    private static final int Angel = 2;
    private static final double Speed = 0.1;

    public Player() {
        super(0, 0, 0);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void stepForward() {
        Vector newPosition = new Vector(Math.toRadians(h));
        newPosition.setSpeed(Speed);
        newPosition.add(this);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }

    public void stepBack() {
        Vector newPosition = new Vector(Math.toRadians(h));
        newPosition.setSpeed(Speed);
        newPosition.setPosition(-newPosition.getX(), -newPosition.getY(), 0);
        newPosition.add(this);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }

    public void stepLeft() {
        Vector newPosition = new Vector(Math.toRadians(h));
        newPosition.setSpeed(Speed);
        newPosition.setPosition(-newPosition.getY(), newPosition.getX(), 0);
        newPosition.add(this);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }

    public void stepRight() {
        Vector newPosition = new Vector(Math.toRadians(h));
        newPosition.setSpeed(Speed);
        newPosition.setPosition(newPosition.getY(), -newPosition.getX(), 0);
        newPosition.add(this);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }

    public int getHorizontal() {
        return h;
    }

    public int getVertical() {
        return v;
    }

    public void turnLeft() {
        h = (h + Angel) % 360;
    }

    public void turnRight() {
        h = (h - Angel + 360) % 360;
    }

    public void turnUp() {
        v = (v + Angel) % 360;
    }

    public void turnDown() {
        v = (v - Angel + 360) % 360;
    }

    public World getWorld() {
        return world;
    }

    public void dropBlock() {
        Vector object = new Vector(this);
        object.add(new Vector(Math.toRadians(h), Math.toRadians(v)));
        object.setPosition(Math.round(object.getX()), Math.round(object.getY()), Math.round(object.getZ()));
        if(world.contains(object) == null) {
            world.put(object);
        }
    }

    public void removeBlock() {
        Vector object = new Vector(this);
        object.add(new Vector(Math.toRadians(h), Math.toRadians(v)));
        object.setPosition(Math.round(object.getX()), Math.round(object.getY()), Math.round(object.getZ()));
        world.remove(world.contains(object));
    }

    public void stepUp() {
        Vector newPosition = new Vector(this);
        newPosition.setPosition(getX(), getY(), getZ() + Speed);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }

    public void stepDown() {
        Vector newPosition = new Vector(this);
        newPosition.setPosition(getX(), getY(), getZ() - Speed);
        if(world.contains(newPosition) == null) {
            setPosition(newPosition);
        }
    }
}
