public class TexturePack {
    private final ConcreteTexture[] list = new ConcreteTexture[Texture.values().length];

    public TexturePack() {
        int index = 0;
        list[index++] = new ConcreteTexture("grass");
    }

    public ConcreteTexture getTexture(Texture texture) {
        return list[texture.ordinal()];
    }
}
