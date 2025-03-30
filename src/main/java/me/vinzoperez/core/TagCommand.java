package me.vinzoperez.core;


import net.kyori.adventure.text.Component;
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
        Component message;
        if (args.length > 1)
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
                    message = mm.deserialize(WARN_MESSAGE + "<green>Tag MiniGame has already started.</green>");
                    sender.sendMessage(message);

                } else {
                    TagSettings.getInstance().setStatus(true);
                    message = mm.deserialize(WARN_MESSAGE + "<green>Tag MiniGame Has Started! <grey><</grey><red>!</red><grey>></grey>");
                    Bukkit.broadcast(message);

                }

            } else if (args[0].equalsIgnoreCase("stop")) {
                message = mm.deserialize(WARN_MESSAGE + "<red><bold>Tag MiniGame has been stopped</bold></red>");
                Bukkit.broadcast(message);
                TagSettings.getInstance().setStatus(false);
            }else if (args[0].equalsIgnoreCase("newtagger"))
            {
                if (TagSettings.getInstance().getStatus()) {
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

                    Player randomTagger = onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
                    TagSettings.getInstance().setTagger(randomTagger.getName());
                    message = mm.deserialize(WARN_MESSAGE+ "<red>" + randomTagger.getName() + "</red><green> is the first tagger!</green>");
                    Bukkit.broadcast(message);
                } else {
                    message = mm.deserialize(WARN_MESSAGE+ "<red>Error: Is the tag minigame started? /tag start");
                    sender.sendMessage(message);
                }

            }
            else if (args[0].equalsIgnoreCase("tagger")){
                if (TagSettings.getInstance().getStatus()) {
                    if (TagSettings.getInstance().getTagger() != null)
                    {
                        message = mm.deserialize(WARN_MESSAGE + "<green>The current tagger is </green><red>" + TagSettings.getInstance().getTagger() + "</red>");

                    } else {
                        message = mm.deserialize(WARN_MESSAGE + "<red> No active tagger please use /tag newtagger ");
                    }

                    sender.sendMessage(message);
                }

            } else if (args[0].equalsIgnoreCase("stats"))
            {
                message = mm.deserialize(WARN_MESSAGE + "<green>Nothing to display here yet. </green>");
                sender.sendMessage(message);
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
                message = mm.deserialize(WARN_MESSAGE + "<green> Knockback Strength is now set to </green><red" + knockbackStrength +"</red>" );
                sender.sendMessage(message);
            }
            if (args[0].equalsIgnoreCase("stats")) {
                message = mm.deserialize(WARN_MESSAGE + "<green>Nothing to display here yet. </green>");
                sender.sendMessage(message);
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
