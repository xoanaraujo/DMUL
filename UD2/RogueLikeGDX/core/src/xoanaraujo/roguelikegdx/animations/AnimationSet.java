package xoanaraujo.roguelikegdx.animations;

public enum AnimationSet {
    GREEN_NINJA("green_ninja", "actor/character/greenninja/green_ninja.atlas",
            AnimationType.IDLE_DOWN,
            AnimationType.IDLE_UP,
            AnimationType.IDLE_LEFT,
            AnimationType.IDLE_RIGHT,
            AnimationType.WALK_DOWN,
            AnimationType.WALK_UP,
            AnimationType.WALK_LEFT,
            AnimationType.WALK_RIGHT,
            AnimationType.ATTACK_DOWN,
            AnimationType.ATTACK_UP,
            AnimationType.ATTACK_LEFT,
            AnimationType.ATTACK_RIGHT,
            AnimationType.JUMP_DOWN,
            AnimationType.JUMP_UP,
            AnimationType.JUMP_LEFT,
            AnimationType.JUMP_RIGHT,
            AnimationType.ITEM,
            AnimationType.DEAD,
            AnimationType.SPECIAL1,
            AnimationType.SPECIAL2),
    BLUE_SAMURAI("blue_samurai", "actor/character/bluesamurai/blue_samurai.atlas",
            AnimationType.IDLE_DOWN,
            AnimationType.IDLE_UP,
            AnimationType.IDLE_LEFT,
            AnimationType.IDLE_RIGHT,
            AnimationType.WALK_DOWN,
            AnimationType.WALK_UP,
            AnimationType.WALK_LEFT,
            AnimationType.WALK_RIGHT,
            AnimationType.ATTACK_DOWN,
            AnimationType.ATTACK_UP,
            AnimationType.ATTACK_LEFT,
            AnimationType.ATTACK_RIGHT,
            AnimationType.JUMP_DOWN,
            AnimationType.JUMP_UP,
            AnimationType.JUMP_LEFT,
            AnimationType.JUMP_RIGHT,
            AnimationType.ITEM,
            AnimationType.DEAD,
            AnimationType.SPECIAL1,
            AnimationType.SPECIAL2),

    GRAY_NINJA("gray_ninja", "actor/character/grayninja/gray_ninja.atlas",
            AnimationType.IDLE_DOWN,
            AnimationType.IDLE_UP,
            AnimationType.IDLE_LEFT,
            AnimationType.IDLE_RIGHT,
            AnimationType.WALK_DOWN,
            AnimationType.WALK_UP,
            AnimationType.WALK_LEFT,
            AnimationType.WALK_RIGHT,
            AnimationType.ATTACK_DOWN,
            AnimationType.ATTACK_UP,
            AnimationType.ATTACK_LEFT,
            AnimationType.ATTACK_RIGHT,
            AnimationType.JUMP_DOWN,
            AnimationType.JUMP_UP,
            AnimationType.JUMP_LEFT,
            AnimationType.JUMP_RIGHT,
            AnimationType.ITEM,
            AnimationType.DEAD,
            AnimationType.SPECIAL1,
            AnimationType.SPECIAL2),
    BLUE_NINJA("blue_ninja", "actor/character/blueninja/blue_ninja.atlas",
            AnimationType.IDLE_DOWN,
            AnimationType.IDLE_UP,
            AnimationType.IDLE_LEFT,
            AnimationType.IDLE_RIGHT,
            AnimationType.WALK_DOWN,
            AnimationType.WALK_UP,
            AnimationType.WALK_LEFT,
            AnimationType.WALK_RIGHT,
            AnimationType.ATTACK_DOWN,
            AnimationType.ATTACK_UP,
            AnimationType.ATTACK_LEFT,
            AnimationType.ATTACK_RIGHT,
            AnimationType.JUMP_DOWN,
            AnimationType.JUMP_UP,
            AnimationType.JUMP_LEFT,
            AnimationType.JUMP_RIGHT,
            AnimationType.ITEM,
            AnimationType.DEAD,
            AnimationType.SPECIAL1,
            AnimationType.SPECIAL2);

    private final String atlasKey;
    private final String path;
    private final AnimationType[] animationTypes;

    AnimationSet(String atlasKey, String path, AnimationType... animationTypes) {
        this.atlasKey = atlasKey;
        this.animationTypes = animationTypes;
        this.path = path;
    }

    public String getAtlasKey() {
        return atlasKey;
    }

    public String getPath() {
        return path;
    }

    public AnimationType[] getAnimationTypes() {
        return animationTypes;
    }
}
