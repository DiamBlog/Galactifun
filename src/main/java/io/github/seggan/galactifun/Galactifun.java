package io.github.seggan.galactifun;

import io.github.seggan.galactifun.api.CelestialObject;
import io.github.seggan.galactifun.solarsystem.mars.Mars;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Galactifun extends JavaPlugin implements SlimefunAddon {

    private final Set<CelestialObject> celestials = new HashSet<>();

    @Getter
    private static Galactifun instance = null;

    @Override
    public void onEnable() {
        instance = this;

        Config cfg = new Config(this);

        if (getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Slimefun-Addon-Community/Galactifun/master").start();
        }

        new Mars().register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public void addCelestial(CelestialObject celestial) {
        celestials.add(celestial);
    }
}
