import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Vector> world = new ArrayList<>();

    public Vector contains(Vector object) {
        for(Vector o : world) {
            if(Math.round(o.getX()) == Math.round(object.getX()) && Math.round(o.getY()) == Math.round(object.getY()) && Math.round(o.getZ()) == Math.round(object.getZ())) {
                return o;
            }
        }
        return null;
    }

    public void put(Vector object) {
        world.add(object);
    }

    public void remove(Vector object) {
        world.remove(object);
    }
}