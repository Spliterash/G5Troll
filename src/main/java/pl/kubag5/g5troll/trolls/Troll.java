package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Troll {
    String name, desc;
    String[] args;
    String usage;
    public Troll(String name, String desc, String[] args) {
        this.name = name;
        this.desc = desc;
        this.args = args;
        usage = "/troll execute " + name + " {player}";
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArg(int i) {
        return args[i];
    }

    public int getArgsLength() {
        if (args == null) return 0;
        return getArgs().length;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setArg(int i, String arg) {
        this.args[i] = arg;
    }

    public void execute(String[] args) {

    }
}
