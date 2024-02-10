package xoanaraujo.slimeit.animations;

public enum GreenNinjaAnimationSet {
    GREEN_NINJA("actor/character/greenninja/green_ninja.atlas",
            AnimationType.WALK_DOWN,
            AnimationType.WALK_UP,
            AnimationType.WALK_LEFT,
            AnimationType.WALK_RIGHT,
            AnimationType.ITEM,
            AnimationType.DEAD,
            AnimationType.ATTACK_DOWN,
            AnimationType.ATTACK_UP,
            AnimationType.ATTACK_LEFT,
            AnimationType.ATTACK_RIGHT,
            AnimationType.SPECIAL1,
            AnimationType.SPECIAL2);

    private final AnimationType[] animationTypes;
    private final String path;

    GreenNinjaAnimationSet(String path, AnimationType... animationTypes) {
        this.animationTypes = animationTypes;
        this.path = path;
    }

    public AnimationType[] getAnimationTypes() {
        return animationTypes;
    }

    public String getPath() {
        return path;
    }
}
