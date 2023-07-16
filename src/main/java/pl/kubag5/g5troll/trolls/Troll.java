package pl.kubag5.g5troll.trolls;

import org.bukkit.Material;
import pl.kubag5.g5troll.G5Troll;
import pl.kubag5.g5troll.Utils;

public abstract class Troll {
    String name, desc;
    String[] args;
    String usage;
    Material icon = Material.DIRT;
    boolean activeInMenu = true;
    boolean showWorldWarning;
    boolean showKillWarning;
    boolean showInventoryWarning;
    boolean showWarnings = true;
    Utils utils = new Utils();
    public Troll(String name, String desc, String... args) {
        this.name = name;
        this.desc = desc;
        this.args = args;
        usage = "/troll execute " + name + " {player}";
    }

    public void setShowWarnings(boolean showWarnings) {
        this.showWarnings = showWarnings;
    }

    public boolean warningsExist() {
        return (showKillWarning || showInventoryWarning || showWorldWarning) && showWarnings;
    }

    public String getWarnings() {
        StringBuilder warnings = new StringBuilder();
        boolean needComma = false;
        if (isShowKillWarning()) {
            needComma = true;
            warnings.append(G5Troll.getInstance().g5CnfTech("general.warnings.kill"));
        }
        if (isShowWorldWarning()) {
            if (needComma) {
                warnings.append(", ");
            }
            warnings.append(G5Troll.getInstance().g5CnfTech("general.warnings.world"));
        }
        if (isShowInventoryWarning()) {
            if (needComma) {
                warnings.append(", ");
            }
            warnings.append(G5Troll.getInstance().g5CnfTech("general.warnings.inventory"));
        }

        return warnings.toString();
    }


    public boolean isShowInventoryWarning() {
        return showInventoryWarning;
    }

    public void setShowInventoryWarning(boolean showInventoryWarning) {
        this.showInventoryWarning = showInventoryWarning;
    }

    public boolean isShowKillWarning() {
        return showKillWarning;
    }

    public void setShowKillWarning(boolean showKillWarning) {
        this.showKillWarning = showKillWarning;
    }

    public boolean isShowWorldWarning() {
        return showWorldWarning;
    }

    public void setShowWorldWarning(boolean showWorldWarning) {
        this.showWorldWarning = showWorldWarning;
    }


    public boolean isActiveInMenu() {
        return activeInMenu;
    }

    public void setActiveInMenu(boolean activeInMenu) {
        this.activeInMenu = activeInMenu;
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


    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public abstract void execute(TrollEvent event);
}
