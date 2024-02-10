package xoanaraujo.roguelikegdx.entity;

public enum VelocityType {

    SLOWEST(50f),
    SLOW(100f),
    NORMAL(150f),
    FAST(200f),
    FASTEST(250f);

    private final float velocity;

    VelocityType(float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }
}
