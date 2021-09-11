package ru.unlegit.spaceboard.line;

import lombok.NonNull;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

@FunctionalInterface
public interface SpaceBoardLine {

    @NonNull
    String getText(@Nullable Player player);
}