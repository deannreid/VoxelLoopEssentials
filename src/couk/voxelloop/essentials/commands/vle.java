package couk.voxelloop.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vle implements CommandExecutor {

    @Override           
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + ("This is some fucked up Plugin for the Voxelloop Network"));
        }
        //If command correct. Return True
        return false;
    }

}

