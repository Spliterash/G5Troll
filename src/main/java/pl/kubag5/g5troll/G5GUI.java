package pl.kubag5.g5troll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pl.kubag5.g5troll.trolls.Troll;
import pl.kubag5.g5troll.trolls.TrollEvent;

import java.util.ArrayList;
import java.util.Objects;

public class G5GUI implements Listener {
    String name;
    ArrayList<Inventory> pages = new ArrayList<>();
    int actualpage = 0;
    public G5GUI(String name, Player[] players) {
        this.name = name;
        createPages(players);
    }

    public G5GUI(String name, Troll[] trolls, Player player) {
        this.name = name;
        target = player;
        createPages(trolls);
    }


    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return pages.get(actualpage);
    }

    public void loadPage(int page, Player p) {
        if (isPageExist(page)) {
            actualpage = page;
            open(p);
        }
    }

    public boolean isPageExist(int page) {
        return pages.size() > page && page > -1;
    }

    public void nextPage(Player p) {
        loadPage(actualpage+1,p);
    }
    public void backPage(Player p) {
        loadPage(actualpage-1,p);
    }

    public enum PageType {
        PLAYER,
        TROLL
    }

    PageType pt = null;
    Object[] objects;
    public void createPages(Troll[] trolls) {
       pt = PageType.TROLL;
       this.objects = trolls;
       int i = 0;
       Inventory newpage = null;
       for (Troll t : trolls) {
           if (i == 0) {
               newpage = Bukkit.createInventory(null, 27, name);
               addControlls(newpage);
               pages.add(newpage);
           }
           ItemStack it = new ItemStack(t.getIcon());
           ItemMeta itmeta = it.getItemMeta();
           itmeta.setDisplayName(ChatColor.GREEN + t.getName());
           ArrayList<String> lore = new ArrayList<>();
           lore.add(ChatColor.GOLD + t.getDesc());
           lore.add(" ");
           if (t.isShowWorldWarning() || t.isShowKillWarning()) {
               String warnings = "";
               if (t.isShowKillWarning()) {
                   warnings += "kill player,";
               }
               if (t.isShowWorldWarning()) {
                   warnings += " change world";
               }
               lore.add(ChatColor.RED + "WARNING: this troll can: " + warnings);
               lore.add(" ");
           }
           if (!t.isActiveInMenu()) {
               lore.add(ChatColor.RED + "You can't use it in menu! :C");
               lore.add(ChatColor.RED + "Use: " + t.getUsage());
           }
           itmeta.setLore(lore);
           it.setItemMeta(itmeta);
           newpage.setItem(i,it);
           i++;
           if (i == 18) {
               i = 0;
           }
       }

    }

    public void addControlls(Inventory newpage) {
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "close");
        close.setItemMeta(closemeta);
        newpage.setItem(26, close);

        ItemStack next = new ItemStack(Material.FEATHER);
        ItemMeta nextmeta = next.getItemMeta();
        nextmeta.setDisplayName(ChatColor.YELLOW + "next page");
        next.setItemMeta(nextmeta);
        newpage.setItem(25, next);

        ItemStack back = new ItemStack(Material.FEATHER);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.YELLOW + "previous page");
        back.setItemMeta(backmeta);
        newpage.setItem(24, back);
    }

    public void createPages(Player[] players) {
        pt = PageType.PLAYER;
        this.objects = players;
        int i = 0;
        Inventory newpage = null;
        for (Player p : players) {
            if (i == 0) {
                newpage = Bukkit.createInventory(null, 27, name);
                addControlls(newpage);
                pages.add(newpage);
            }
            ItemStack it = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta itmeta = (SkullMeta) it.getItemMeta();
            itmeta.setOwner(p.getName());
            itmeta.setDisplayName(ChatColor.GREEN + p.getName());
            it.setItemMeta(itmeta);
            newpage.setItem(i,it);
            i++;
            if (i == 18) {
                i = 0;
            }
        }
    }



    @EventHandler
    public void onCLick(InventoryClickEvent e) {
        if (Objects.equals(e.getClickedInventory(), pages.get(actualpage))) {
            if (e.getSlot() == 26) {
                e.getWhoClicked().closeInventory();
            }
            if (e.getSlot() == 25) {
                nextPage((Player) e.getWhoClicked());
            }
            if (e.getSlot() == 24) {
                backPage((Player) e.getWhoClicked());
            }
            if (e.getSlot() < 18) {
                executeClick((actualpage*18)+e.getSlot(), (Player) e.getWhoClicked());
            }
            e.setCancelled(true);
        }
    }


    Player target;
    public void executeClick(int click, Player whoClick) {
        if (click+1 > objects.length) return;
        if (pt == PageType.TROLL) {
            Troll troll = (Troll) objects[click];
            if (troll.isActiveInMenu()) {
                TrollEvent te = new TrollEvent(whoClick, target, new String[]{target.getName()});
                 whoClick.closeInventory();
                if (te.verify()) troll.execute(te);
            }
        }
        if (pt == PageType.PLAYER) {
            G5GUI gui = new G5GUI(ChatColor.RED+"Choose troll", G5Troll.getInstance().getTrolls(), (Player) objects[click]);
            Bukkit.getPluginManager().registerEvents(gui, G5Troll.getInstance());
            gui.open(whoClick);
        }
    }




    public void open(Player p) {
        p.openInventory(pages.get(actualpage));
    }

}
