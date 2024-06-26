package blokplugins.kitroom.listners;

import blokplugins.kitroom.database.PointsDatabase;
import blokplugins.kitroom.extra.InventorySerializations;
import blokplugins.kitroom.extra.KitRoomItemsMainHolder;
import blokplugins.kitroom.menus.kitroomadmin;
import blokplugins.kitroom.menus.kitroomitems;
import blokplugins.kitroom.menus.mainmenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class kitroomitemslistner implements Listener {
    private final PointsDatabase pointsDatabase;
    private final InventorySerializations inventorySerializer;

    public kitroomitemslistner(InventorySerializations inventorySerializer, PointsDatabase pointsDatabase) {
        this.inventorySerializer = inventorySerializer;
        this.pointsDatabase = pointsDatabase;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof KitRoomItemsMainHolder) {
            if (e.getRawSlot() >= 45 && e.getRawSlot() <= 53) {
                Player player = (Player) e.getWhoClicked();
                String title = e.getView().getTitle();
                if (e.getRawSlot() == 53) {
                    new mainmenu(player, null);
                } else if (e.getRawSlot() == 47) {
                    handleKitClickKit(player, "vanillapvp");
                } else if (e.getRawSlot() == 48) {
                    handleKitClickKit(player, "diamoncrystal");
                } else if (e.getRawSlot() == 49) {
                    handleKitClickKit(player, "potions");
                } else if (e.getRawSlot() == 50) {
                    handleKitClickKit(player, "armory");
                } else if (e.getRawSlot() == 51) {
                    handleKitClickKit(player, "axe");
                } else if (e.getRawSlot() == 45) {
                    handleKitClickKit(player, title);
                }
                e.setCancelled(true);
            }
        }
    }
    private void handleKitClickKit (Player player, String name){
        Inventory deserializedInventory = inventorySerializer.deserializeInventory("kitroom", name);

        new kitroomitems(player, deserializedInventory, name);
    }
}
