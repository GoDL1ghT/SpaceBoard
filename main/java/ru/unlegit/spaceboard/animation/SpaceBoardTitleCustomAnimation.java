package ru.unlegit.spaceboard.animation;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NonNull;
import ru.unlegit.spaceboard.util.ChatUtil;

import java.util.Arrays;
import java.util.List;

public class SpaceBoardTitleCustomAnimation implements SpaceBoardTitleAnimation {

    @Getter private final List<String> titles = Lists.newLinkedList();
    @Getter private String currentTitle;
    private int titleCounter;

    public SpaceBoardTitleCustomAnimation(String... titles) {
        this.titles.addAll(ChatUtil.color(Arrays.asList(titles)));
    }

    public SpaceBoardTitleCustomAnimation addTextToAnimation(@NonNull String text) {
        this.titles.add(ChatUtil.color(text));
        return this;
    }

    @Override
    public void nextTitle() {
        this.currentTitle = this.titles.get(this.titleCounter);
        this.titleCounter++;
        if(this.titleCounter >= this.titles.size()) {
            titleCounter = 0;
        }
    }
}