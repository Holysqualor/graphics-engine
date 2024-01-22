public class Ray {
    public final Vector orig;
    public final Vector dir;
    public final int[] sign;

    public Ray(Vector origin, Vector direction) {
        orig = origin;
        dir = direction.inverted();
        sign = new int[] {dir.getX() < 0 ? 1 : 0, dir.getY() < 0 ? 1 : 0, dir.getZ() < 0 ? 1 : 0};
    }
}
