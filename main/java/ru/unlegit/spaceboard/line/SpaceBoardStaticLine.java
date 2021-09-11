package ru.unlegit.spaceboard.line;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

@RequiredArgsConstructor
public class SpaceBoardStaticLine implements SpaceBoardLine {

    private final String text;

    @Override
    public @NonNull String getText(@Nullable Player player) {
        return text;
    }
}