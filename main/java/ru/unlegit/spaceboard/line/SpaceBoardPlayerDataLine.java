package ru.unlegit.spaceboard.line;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.function.Function;

@RequiredArgsConstructor
public class SpaceBoardPlayerDataLine implements SpaceBoardLine {

    private final Function<Player, String> titleFunction;

    @Override
    public @NonNull String getText(@NonNull Player player) {
        return titleFunction.apply(player);
    }
}