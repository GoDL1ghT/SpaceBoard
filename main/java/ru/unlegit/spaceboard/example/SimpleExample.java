package ru.unlegit.spaceboard.example;

import org.bukkit.ChatColor;
import ru.unlegit.spaceboard.SpaceBoard;
import ru.unlegit.spaceboard.animation.SpaceBoardTitleFlickAnimation;
import ru.unlegit.spaceboard.line.SpaceBoardPlayerDataLine;
import ru.unlegit.spaceboard.line.SpaceBoardStaticLine;
import ru.unlegit.spaceboard.line.SpaceBoardUpdatableLine;

public class SimpleExample {

    static {
        SpaceBoard spaceBoard = new SpaceBoard(
                new SpaceBoardTitleFlickAnimation()
                        .addColor(ChatColor.LIGHT_PURPLE)
                        .addColor(ChatColor.DARK_PURPLE)
                        .addTextToAnimation("First Title", true)
                        .addTextToAnimation("Second Title", false))
                .addEmptyLine()
                .addLine(new SpaceBoardPlayerDataLine(player -> "&f&lName: &c&l" + player.getName()))
                .addEmptyLine()
                .addLine(new SpaceBoardUpdatableLine(() -> "Some line with updatable variables"))
                .addEmptyLine()
                .addLine(new SpaceBoardStaticLine("Some static line for your scoreboard(for example 'mc.server.net'"));
    }
}