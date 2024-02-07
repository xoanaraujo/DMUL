package xoanaraujo.tuttifruttigdx.fruit;

import static xoanaraujo.tuttifruttigdx.util.GameConst.BIT_APPLE;
import static xoanaraujo.tuttifruttigdx.util.GameConst.BIT_ORANGE;

public enum FruitType {
    APPLE(BIT_APPLE,"", 0.4f),
    ORANGE(BIT_ORANGE,"", 0.6f);

    private final String path;
    private final Float radius;
    private final Short bit;

    FruitType(Short bit, String path, Float radius) {
        this.bit = bit;
        this.path = path;
        this.radius = radius;
    }

    public String getPath() {
        return path;
    }

    public Float getRadius() {
        return radius;
    }

    public Short getBit() {
        return bit;
    }
}
