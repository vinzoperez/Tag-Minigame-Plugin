package me.vinzoperez.core;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TagListener(), this);

        getCommand("tag").setExecutor(new GotMyLastCommand());

        GotMyLastSettings.getInstance().load();
    }

    @Override
    public void onDisable() {

    }

    public static Core getInstance()
    {
        return getPlugin(Core.class);
    }
}
