package ru.unlegit.spaceboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import ru.unlegit.spaceboard.animation.SpaceBoardTitleAnimation;
import ru.unlegit.spaceboard.animation.SpaceBoardTitleStaticAnimation;
import ru.unlegit.spaceboard.line.SpaceBoardLine;
import ru.unlegit.spaceboard.line.SpaceBoardStaticLine;
import ru.unlegit.spaceboard.util.ChatUtil;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

@AllArgsConstructor
public class SpaceBoard {

    @Getter private final Set<UUID> holders = Sets.newHashSet();
    private final List<SpaceBoardLine> lines = Lists.newArrayList();
    private final Supplier<Integer> lineIndex = () -> lines.size() - 1;
    @Getter @Setter private SpaceBoardTitleAnimation titleAnimation;

    public void addHolder(@NonNull Player player) {
        if(holders.contains(player.getUniqueId())) return;
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("dummy", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        int lineIndex = this.lineIndex.get();
        for (SpaceBoardLine line : lines) {
            String text = ChatUtil.color(line.getText(player));
            objective.getScore(text).setScore(lineIndex);
            lineIndex--;
        }
        objective.setDisplayName(ChatUtil.color(titleAnimation.getCurrentTitle()));
        player.setScoreboard(scoreboard);
        holders.add(player.getUniqueId());
    }

    public void removeHolder(@NonNull Player player) {
        if(!holders.contains(player.getUniqueId())) return;
        holders.remove(player.getUniqueId());
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public SpaceBoard addEmptyLine() {
        String index;
        if(lines.size() > 0) {
            index = String.valueOf(lines.size());
        } else {
            index = "a";
        }
        this.lines.add(new SpaceBoardStaticLine("&" + index));
        update();
        return this;
    }

    public SpaceBoard addLine(@NonNull SpaceBoardLine line) {
        this.lines.add(line);
        update();
        return this;
    }

    public void setLine(int index, @NonNull SpaceBoardLine line) {
        this.lines.set(index, line);
        update();
    }

    public void removeLine(int index) {
        lines.remove(index);
        update();
    }

    public void update() {
        if(holders.size() == 0) return;
        filterEntries();
        for (UUID entryId : holders) {
            Player entry = Bukkit.getPlayer(entryId);
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            scoreboard.getEntries().forEach(scoreboard::resetScores);
            Objective objective = objective = scoreboard.registerNewObjective("dummy", "dummy");
            objective.setDisplayName(ChatUtil.color(this.titleAnimation.getCurrentTitle()));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            int lineIndex = this.lineIndex.get();
            for (SpaceBoardLine line : lines) {
                String text = ChatUtil.color(line.getText(entry));
                objective.getScore(text).setScore(lineIndex);
                lineIndex--;
            }
            entry.setScoreboard(scoreboard);
        }
    }

    public void updateTitle() {
        if(this.titleAnimation instanceof SpaceBoardTitleStaticAnimation) return;
        this.titleAnimation.nextTitle();
        if(holders.size() == 0) return;
        filterEntries();
        for (UUID entryId : this.holders) {
            Player entry = Bukkit.getPlayer(entryId);
            Scoreboard scoreboard = entry.getScoreboard();
            Objective objective = scoreboard.getObjective("dummy");
            objective.setDisplayName(this.titleAnimation.getCurrentTitle());
        }
    }

    private void filterEntries() {
        for (UUID entryId : holders) {
            Player entry = Bukkit.getPlayer(entryId);
            if(entry == null || !entry.isOnline()) {
                this.holders.remove(entryId);
            }
        }
    }

    public void destroy() {
        filterEntries();
        this.holders.forEach(entryId -> {
            removeHolder(Bukkit.getPlayer(entryId));
        });
        this.lines.clear();
        this.holders.clear();
        this.titleAnimation = null;
    }
}