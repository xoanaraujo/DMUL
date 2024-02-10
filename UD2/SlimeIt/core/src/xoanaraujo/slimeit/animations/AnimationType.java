package xoanaraujo.slimeit.entity.animations;

public enum AnimationType {
    GREEN_NINJA_IDDLE("entity/character/greenninja/green_ninja.atlas", "idle", 0, 0.2f),
    GREEN_NINJA_WALK("entity/character/greenninja/green_ninja.atlas", "walk", 0, 0.2f),
    GREEN_NINJA_ITEM("entity/character/greenninja/green_ninja.atlas", "item", 0, 0.2f),
    GREEN_NINJA_DEAD("entity/character/greenninja/green_ninja.atlas", "dead", 0, 0.2f),
    GREEN_NINJA_ATTACK("entity/character/greenninja/green_ninja.atlas", "attack", 0, 0.2f),
    GREEN_NINJA_SPECIAL1("entity/character/greenninja/green_ninja.atlas", "special1", 0, 0.2f),
    GREEN_NINJA_SPECIAL2("entity/character/greenninja/green_ninja.atlas", "special2", 0, 0.2f);

    private final String path;
    private final String atlasKey;
    private final int index;
    private final float timePerFrame;

    AnimationType(String path, String atlasKey, int rowIndex, float timePerFrame) {
        this.path = path;
        this.atlasKey = atlasKey;
        this.index = rowIndex;
        this.timePerFrame = timePerFrame;
    }

    public String getPath() {
        return path;
    }

    public String getAtlasKey() {
        return atlasKey;
    }

    public int getIndex() {
        return index;
    }

    public float getTimePerFrame() {
        return timePerFrame;
    }
}