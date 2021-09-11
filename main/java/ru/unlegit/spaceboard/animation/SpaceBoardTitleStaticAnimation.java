package ru.unlegit.spaceboard.animation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class SpaceBoardTitleStaticAnimation implements SpaceBoardTitleAnimation {

    private final String currentTitle;

    public SpaceBoardTitleStaticAnimation(@Nullable String title) {
        this.currentTitle = title;
    }

    @Override
    public Collection<String> getTitles() {
        return Collections.singletonList(currentTitle);
    }

    @Override
    public String getCurrentTitle() {
        return currentTitle;
    }

    @Override
    public void nextTitle() {}
}