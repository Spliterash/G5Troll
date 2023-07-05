package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TrollEvent {

    Player executor, target;
    String[] args;
    public TrollEvent(Player executor, Player target, String[] args) {
        this.executor = executor;
        this.args = args;
        this.target = target;
    }

    public TrollEvent(Player executor, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        this.executor = executor;
        this.args = args;
        this.target = target;
    }

    public boolean verify() {
        if (getTarget() == null) return false;
        if (!getTarget().isOnline()) return false;
        if (getExecutor() == null) return false;
        if (!getExecutor().isOnline()) return false;
        return getArgs() != null;
    }

    public Player getExecutor() {
        return executor;
    }

    public Player getTarget() {
        return target;
    }

    public void setExecutor(Player executor) {
        this.executor = executor;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    // args[0] is target.
    public String[] getArgs() {
        return args;
    }
}
