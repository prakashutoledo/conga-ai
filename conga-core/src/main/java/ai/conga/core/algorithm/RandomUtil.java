package ai.conga.core.algorithm;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

public final class RandomUtil {
    private static final Random RANDOM = new Random();

    public static <T> Optional<T> randomElement(@NotNull List<? extends T> collection) {
        if(isEmptyCollection(collection)) {
            return Optional.empty();
        }
        return Optional.ofNullable(collection.get(RANDOM.nextInt(collection.size())));
    }

    public static <T> boolean isEmptyCollection(Collection<? extends T> collection) {
        return isNullCollection(collection) || collection.isEmpty();
    }

    public static <T> boolean isNullCollection(Collection<? extends T> collection) {
        return collection == null;
    }

    private RandomUtil() {
    }
}
