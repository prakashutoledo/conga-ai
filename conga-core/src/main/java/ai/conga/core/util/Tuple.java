package ai.conga.core.util;

import ai.conga.core.domain.Copy;
import org.jetbrains.annotations.NotNull;

public class Tuple<X,Y> implements Copy<Tuple<X,Y>> {
    private final X x;
    private final Y y;

    public Tuple(@NotNull X x, @NotNull Y y) {
        //Objects.requireNonNull(x, "X value should not be null");
        //Objects.requireNonNull(y, "X value should not be null");
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public Tuple<X, Y> deepCopyOf() {
        return new Tuple<>(x, y);
    }
}
