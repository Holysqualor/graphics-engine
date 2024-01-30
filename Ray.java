public class Ray {
    public final Vector origin;
    public final Vector direction;
    public final int[] sign;

    public Ray(Vector origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
        sign = new int[] {this.direction.getX() < 0 ? 1 : 0, this.direction.getY() < 0 ? 1 : 0, this.direction.getZ() < 0 ? 1 : 0};
    }
}
