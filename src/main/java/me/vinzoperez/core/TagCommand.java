package me.vinzoperez.core;


import net.kyori.adventure.text.minimessage.MiniMessage;
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
        MiniMessage mm = MiniMessage.miniMessage();
        String WARN_MESSAGE = "<grey><</grey><red>!</red><grey>></grey> "; // <!>

        // No Arguments Provided
        if (args.length == 0)
        {
            sender.sendMessage("/tag start -- Starts The MiniGame");
            sender.sendMessage("/tag stop -- Stops The MiniGame");
            sender.sendMessage("/tag tagger -- See who is the tagger");
            sender.sendMessage("/tag stats <player> -- retrieves stats about Tag MiniGame");
            sender.sendMessage("/tag newtagger -- selects a random tagger");

            sender.sendMessage("/tag SetKnockback <value> -- sets the knockback strength on being tagged.");
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start"))
            {
                if (TagSettings.getInstance().getStatus()) {
                    sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<green>Tag MiniGame has already started.</green>"));

                } else {
                    TagSettings.getInstance().setStatus(true);
                    Bukkit.broadcast(mm.deserialize(WARN_MESSAGE + "<green>Tag MiniGame Has Started! <grey><</grey><red>!</red><grey>></grey>"));

                }

            } else if (args[0].equalsIgnoreCase("stop")) {
                Bukkit.broadcast(mm.deserialize(WARN_MESSAGE + "<red><bold>Tag MiniGame has been stopped</bold></red>"));
                TagSettings.getInstance().setStatus(false);
            }else if (args[0].equalsIgnoreCase("newtagger"))
            {
                if (TagSettings.getInstance().getStatus()) {
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

                    Player randomTagger = onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
                    TagSettings.getInstance().setTagger(randomTagger.getName());
                    Bukkit.broadcast(mm.deserialize(WARN_MESSAGE+ "<red>" + randomTagger.getName() + "</red><green> is the first tagger!</green>"));
                } else {
                    sender.sendMessage(mm.deserialize(WARN_MESSAGE+ "<red>Error: Is the tag minigame started? /tag start"));
                }

            }
            else if (args[0].equalsIgnoreCase("tagger")){
                if (TagSettings.getInstance().getStatus()) {
                    if (TagSettings.getInstance().getTagger() != null)
                    {
                        sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<green>The current tagger is </green><red>" + TagSettings.getInstance().getTagger() + "</red>"));
                    } else {
                        sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<red> No active tagger please use /tag newtagger "));
                    }


                }

            } else if (args[0].equalsIgnoreCase("stats"))
            {
                sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<green>Nothing to display here yet. </green>"));
            }
            else if (args[0].equalsIgnoreCase("help")) {
                // Default help for now
                sender.sendMessage("/tag start -- Starts The MiniGame");
                sender.sendMessage("/tag stop -- Stops The MiniGame");
                sender.sendMessage("/tag tagger -- See who is the tagger");
                sender.sendMessage("/tag stats <player> -- retrieves stats about Tag MiniGame");
                sender.sendMessage("/tag newtagger -- selects a random tagger");

                sender.sendMessage("/tag SetKnockback <value> -- sets the knockback strength on being tagged.");

            }

        }
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("SetKnockBack")){
                double knockbackStrength = Double.parseDouble(args[1]);

                TagSettings.getInstance().setKnockbackStrength(knockbackStrength);
                sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<green> Knockback Strength is now set to </green><red" + knockbackStrength +"</red>"));
            }
            if (args[0].equalsIgnoreCase("stats")) {
                sender.sendMessage(mm.deserialize(WARN_MESSAGE + "<green>Nothing to display here yet. </green>"));
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
