package ru.unlegit.spaceboard.line;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class SpaceBoardUpdatableLine implements SpaceBoardLine {

    private final Supplier<String> textSupplier;

    @Override
    public @NonNull String getText(@Nullable Player player) {
        return textSupplier.get();
    }
}