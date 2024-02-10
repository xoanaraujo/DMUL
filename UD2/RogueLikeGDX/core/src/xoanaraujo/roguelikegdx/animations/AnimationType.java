package xoanaraujo.roguelikegdx.animations;

import com.badlogic.gdx.graphics.g2d.Animation;

public enum AnimationType {
    IDLE_DOWN(new int[]{0}, new int[]{0}, 1f, Animation.PlayMode.NORMAL),
    IDLE_UP(new int[]{0}, new int[]{1}, 1f, Animation.PlayMode.NORMAL),
    IDLE_LEFT(new int[]{0}, new int[]{2}, 1f, Animation.PlayMode.NORMAL),
    IDLE_RIGHT(new int[]{0}, new int[]{3}, 1f, Animation.PlayMode.NORMAL),
    WALK_DOWN(new int[]{0, 1, 2, 3}, new int[]{0, 0, 0, 0}, 0.15f, Animation.PlayMode.LOOP),
    WALK_UP(new int[]{0, 1, 2, 3}, new int[]{1, 1, 1, 1}, 0.15f, Animation.PlayMode.LOOP),
    WALK_LEFT(new int[]{0, 1, 2, 3}, new int[]{2, 2, 2, 2}, 0.15f, Animation.PlayMode.LOOP),
    WALK_RIGHT(new int[]{0, 1, 2, 3}, new int[]{3, 3, 3, 3}, 0.15f, Animation.PlayMode.LOOP),
    ATTACK_DOWN(new int[]{4}, new int[]{0}, 0.2f, Animation.PlayMode.NORMAL),
    ATTACK_UP(new int[]{4}, new int[]{1}, 0.2f, Animation.PlayMode.NORMAL),
    ATTACK_LEFT(new int[]{4}, new int[]{2}, 0.2f, Animation.PlayMode.NORMAL),
    ATTACK_RIGHT(new int[]{4}, new int[]{3}, 0.2f, Animation.PlayMode.NORMAL),
    JUMP_DOWN(new int[]{5}, new int[]{0}, 1f, Animation.PlayMode.NORMAL),
    JUMP_UP(new int[]{5}, new int[]{1}, 1f, Animation.PlayMode.NORMAL),
    JUMP_LEFT(new int[]{5}, new int[]{2}, 1f, Animation.PlayMode.NORMAL),
    JUMP_RIGHT(new int[]{5}, new int[]{3}, 1f, Animation.PlayMode.NORMAL),
    DEAD( new int[]{6}, new int[]{0}, 1f, Animation.PlayMode.NORMAL),
    ITEM( new int[]{6}, new int[]{1}, 1f, Animation.PlayMode.NORMAL),
    SPECIAL1(  new int[]{6}, new int[]{2}, 1f, Animation.PlayMode.NORMAL),
    SPECIAL2(new int[]{6}, new int[]{3}, 1f, Animation.PlayMode.NORMAL);

    private final int rows[], columns[];
    private final float timePerFrame;
    private final Animation.PlayMode playMode;

    AnimationType(int[] rows, int[] columns, float timePerFrame, Animation.PlayMode playMode) {
        this.rows = rows;
        this.columns = columns;
        this.timePerFrame = timePerFrame;
        this.playMode = playMode;
    }

    public int[] getRows() {
        return rows;
    }

    public int[] getColumns() {
        return columns;
    }

    public float getTimePerFrame() {
        return timePerFrame;
    }

    public Animation.PlayMode getPlayMode() {
        return playMode;
    }
}