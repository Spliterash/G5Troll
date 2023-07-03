package pl.kubag5.g5troll;

import pl.kubag5.g5troll.trolls.Troll;

public class G5APIImpl implements G5API {
    private G5Troll plugin;

    public G5APIImpl(G5Troll plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addTroll(Troll t) {
        plugin.addTroll(t);
    }
}
