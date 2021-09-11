package ru.unlegit.spaceboard.animation;

import java.util.Collection;

public interface SpaceBoardTitleAnimation {

    Collection<String> getTitles();

    String getCurrentTitle();

    void nextTitle();
}