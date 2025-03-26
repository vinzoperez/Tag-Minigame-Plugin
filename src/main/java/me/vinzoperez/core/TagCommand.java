package me.vinzoperez.core;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagCommand implements CommandExecutor, TabExecutor {

    // Convert to functions to make code more robust.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start"))
            {
                if (TagSettings.getInstance().getStatus()) {
                    sender.sendMessage(ChatColor.RED + " Tag MiniGame has already started.");

                } else {
                    TagSettings.getInstance().setStatus(true);
                    Bukkit.broadcastMessage(ChatColor.GREEN + " <!> Tag MiniGame Has Started! <!>");

                }

            } else if (args[0].equalsIgnoreCase("stop")) {
                Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Tag MiniGame has been stopped");
                TagSettings.getInstance().setStatus(false);
            }else if (args[0].equalsIgnoreCase("chooseNewTagger"))
            {
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

                Player randomTagger = onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
                TagSettings.getInstance().setTagger(randomTagger.getName());

                Bukkit.broadcastMessage(ChatColor.RED + randomTagger.getName() + " is the first tagger!");
            }
            else if (args[0].equalsIgnoreCase("tagger")){
                if (TagSettings.getInstance().getStatus()) {
                    sender.sendMessage(ChatColor.GREEN + "The current tagger is " + ChatColor.RED + TagSettings.getInstance().getTagger());
                }

            } else if (args[0].equalsIgnoreCase("stats"))
            {
                sender.sendMessage(ChatColor.GREEN + " Nothing To Display Here Yet.");
            }
            else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("/tag start -- Starts The MiniGame");
                sender.sendMessage("/tag stop -- Stops The MiniGame");
                sender.sendMessage("/tag tagger -- See who is the tagger");
                sender.sendMessage("/tag stats -- retrieves stats about Tag MiniGame");
                sender.sendMessage("/tag SetKnockback <value> -- sets the knockback strength on being tagged.");
            }

        }
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("SetKnockBack")){
                double KnockbackStrength = Double.parseDouble(args[1]);

                TagSettings.getInstance().setKnockbackStrength(KnockbackStrength);
                sender.sendMessage(ChatColor.GREEN + " Knockback Strength is now set to " + ChatColor.RED + KnockbackStrength);
            }
        }

        Player player = (Player) sender;
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
