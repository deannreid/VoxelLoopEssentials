package couk.voxelloop.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class testCommand implements CommandExecutor {

    @Override           
    @SuppressWarnings("unused")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(ChatColor.AQUA + ("Testing Commands R Awesome You got Free Bricks. WOO"));
            ItemStack bricks = new ItemStack(Material.BRICK, 20);
        }
        //If command correct. Return True
        return false;
    }

}
