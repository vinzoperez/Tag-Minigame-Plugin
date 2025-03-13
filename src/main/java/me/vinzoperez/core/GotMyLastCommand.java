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

public class GotMyLastCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start"))
            {
                if (GotMyLastSettings.getInstance().getStatus()) {
                    sender.sendMessage(ChatColor.RED + " Tag MiniGame has already started.");

                } else {
                    GotMyLastSettings.getInstance().setStatus(true);
                    Bukkit.broadcastMessage(ChatColor.GREEN + " <!> Tag MiniGame Has Started! <!>");
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

                    Player randomTagger = onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
                    GotMyLastSettings.getInstance().setTagger(randomTagger.getName());

                    Bukkit.broadcastMessage(ChatColor.RED + randomTagger.getName() + " is the first tagger!");
                }

            } else if (args[0].equalsIgnoreCase("stop"))
            {
                Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Tag MiniGame has been stopped");
                GotMyLastSettings.getInstance().setStatus(false);
            }
            else if (args[0].equalsIgnoreCase("tagger")){
                if (GotMyLastSettings.getInstance().getStatus()) {
                    sender.sendMessage(ChatColor.GREEN + "The current tagger is " + ChatColor.RED + GotMyLastSettings.getInstance().getTagger());
                }

            } else if (args[0].equalsIgnoreCase("stats"))
            {
                sender.sendMessage(ChatColor.GREEN + " Nothing To Display Here Yet.");
            }
            else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("/gotmylast start -- Starts The MiniGame");
                sender.sendMessage("/gotmylast stop -- Stops The MiniGame");
                sender.sendMessage("/gotmylast tagger -- See who is the tagger");
                sender.sendMessage("/gotmylast stats -- retrieves stats about Tag MiniGame");
                sender.sendMessage("/gotmylast SetKnockback <value> -- sets the knockback strength on being tagged.");
            }

        }
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("SetKnockBack")){
                double KnockbackStrength = Double.parseDouble(args[1]);

                GotMyLastSettings.getInstance().setKnockbackStrength(KnockbackStrength);
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
