package me.vinzoperez.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class TagListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if (!(e.getDamager() instanceof Player)) {return;}
        if (!(e.getEntity() instanceof Player)) {return;}
        if (!(TagSettings.getInstance().getStatus())) { return; }

        Player attacker = (Player) e.getDamager();
        LivingEntity victim = (Player) e.getEntity();

        Vector knockbackDirection = victim.getLocation().toVector().subtract(attacker.getLocation().toVector()).normalize();

        double knockbackStrength = TagSettings.getInstance().getKnockbackStrength();
        double upwardBoost = 0.4;

        e.setCancelled(true);

        if (attacker.getName().equalsIgnoreCase(TagSettings.getInstance().getTagger()))
        {

            victim.setVelocity(knockbackDirection.multiply(knockbackStrength).setY(upwardBoost));

            TagSettings.getInstance().setTagger(victim.getName());
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + attacker.getName() + ChatColor.GREEN + " TAGGED " + ChatColor.RED + "" + ChatColor.BOLD + "" + victim.getName() + " and IS NOW IT!");
        }
    }
}
