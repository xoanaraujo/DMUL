package xoanaraujo.slimeit.entity.animations;

public enum AnimationType {
    ;

    private final String path;
    private final int rowIndex;
    private final float timePerFrame;

    AnimationType(String path, int rowIndex, float timePerFrame) {
        this.path = path;
        this.rowIndex = rowIndex;
        this.timePerFrame = timePerFrame;
    }

    public String getPath() {
        return path;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public float getTimePerFrame() {
        return timePerFrame;
    }
}