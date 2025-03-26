package me.vinzoperez.core;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tag extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TagListener(), this);

        getCommand("tag").setExecutor(new TagCommand());

        TagSettings.getInstance().load();
    }

    @Override
    public void onDisable() {

    }

    public static Tag getInstance()
    {
        return getPlugin(Tag.class);
    }
}
