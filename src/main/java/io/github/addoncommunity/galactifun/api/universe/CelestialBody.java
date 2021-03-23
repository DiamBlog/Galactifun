package io.github.addoncommunity.galactifun.api.universe;

import io.github.addoncommunity.galactifun.api.universe.attributes.DayCycle;
import io.github.addoncommunity.galactifun.api.universe.attributes.Gravity;
import io.github.addoncommunity.galactifun.api.universe.attributes.Orbit;
import io.github.addoncommunity.galactifun.api.universe.attributes.atmosphere.Atmosphere;
import io.github.addoncommunity.galactifun.api.universe.types.CelestialType;
import io.github.addoncommunity.galactifun.util.ItemChoice;
import lombok.Getter;
import org.apache.commons.lang.Validate;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A celestial object
 * 
 * @author Mooy1
 */
public abstract class CelestialBody extends UniversalObject<CelestialBody> {
    
    @Getter
    @Nonnull 
    protected final DayCycle dayCycle;
    
    @Getter
    @Nonnull 
    protected final Atmosphere atmosphere;
    
    @Getter
    @Nonnull 
    protected final Gravity gravity;
    
    public CelestialBody(@Nonnull String name, @Nonnull Orbit orbit, @Nonnull CelestialType type,
                         @Nonnull ItemChoice choice) {
        super(name, orbit, type, choice);
        
        Validate.notNull(this.gravity = createGravity(), "Cannot use a null gravity!");
        Validate.notNull(this.dayCycle = createDayCycle(), "Cannot use a null day cycle!");
        Validate.notNull(this.atmosphere = createAtmosphere(), "Cannot use a null atmosphere");
        
    }
    
    @Nonnull
    protected abstract DayCycle createDayCycle();
    
    @Nonnull
    protected abstract Atmosphere createAtmosphere();
    
    @Nonnull
    protected abstract Gravity createGravity();
    
    @Override
    protected final void getItemStats(@Nonnull List<String> stats) {
        stats.add("&bGravity: &7" + this.gravity.getPercent() + "%");
        stats.add("&bOxygen: &7" + this.atmosphere.getOxygenPercentage() + "%");
        stats.add("&6Day Cycle: &e" + this.dayCycle.getDescription());
    }

}
