package me.vinzoperez.core;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class TagSettings {
    private final static TagSettings instance = new TagSettings();

    private File file;
    private YamlConfiguration config;

    private boolean isActive;
    private String currentTagger;
    private double knockbackStrength;

    private TagSettings(){

    }

    public void load() {
        file = new File(Tag.getInstance().getDataFolder(), "settings.yml");

        if (!file.exists())
        {
            Tag.getInstance().saveResource("settings.yml", false);

        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        isActive = config.getBoolean("GotMyLast.Is-Active");
        currentTagger = config.getString("GotMyLast.Current-Tagger");
        knockbackStrength = config.getDouble("GotMyLast.Knockback-Strength");

    }

    public void save()
    {
        try {
            config.save(file);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public boolean getStatus()
    {
        return isActive;
    }

    public void setStatus(boolean newStatus)
    {
        this.isActive = newStatus;

        set("GotMyLast.Is-Active", newStatus);
    }

    public String getTagger()
    {
        return currentTagger;
    }

    public void setTagger(String newTagger)
    {
        this.currentTagger = newTagger;

        set("GotMyLast.Current-Tagger", newTagger);
    }
    public void setKnockbackStrength(double newStrength)
    {
        this.knockbackStrength = newStrength;

        set("GotMyLast.Knockback-Strength", newStrength);
    }
    public double getKnockbackStrength() {
        return knockbackStrength;
    }
    public static TagSettings getInstance() {
        return instance;
    }


}
