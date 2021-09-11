package ru.unlegit.spaceboard.animation;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.ChatColor;

import java.util.List;

public class SpaceBoardTitleFlickAnimation implements SpaceBoardTitleAnimation {

    @Getter private final List<ChatColor> colors = Lists.newLinkedList();
    @Getter private final List<String> titles = Lists.newLinkedList();
    private final List<String> rawTitles = Lists.newArrayList();
    @Getter private int titleCounter = 0;
    @Getter private String currentTitle;

    public SpaceBoardTitleFlickAnimation addTextToAnimation(@NonNull String text, boolean bold) {
        String textToAdd = bold ? "§l" + text : text;
        if(colors.size() == 0) {
            this.titles.add(textToAdd);
        } else {
            for (ChatColor color : colors) {
                this.titles.add(color + textToAdd);
            }
        }
        this.rawTitles.add(textToAdd);
        return this;
    }

    public SpaceBoardTitleFlickAnimation addColor(@NonNull ChatColor color) {
        this.colors.add(color);
        for (String rawTitle : this.rawTitles) {
            this.titles.add(color + rawTitle);
        }
        if(colors.size() == 1) {
            rawTitles.forEach(titles::remove);
        }
        return this;
    }

    @Override
    public void nextTitle() {
        if(titles.size() == 0) return;
        this.currentTitle = this.titles.get(this.titleCounter);
        this.titleCounter++;
        if(this.titleCounter >= this.titles.size()) {
            titleCounter = 0;
        }
    }
}