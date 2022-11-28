package no.ntnu.idata2001.wargame.data;

import no.ntnu.idata2001.wargame.data.CavalryUnit;
import no.ntnu.idata2001.wargame.data.Terrain;

/**
 * Represents a more capable CavalryUnit.
 * Same advantages as CavalryUnit.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class CommanderUnit extends CavalryUnit {
    /**
     * Constructor for the object of the class CommanderUnit.
     *
     * @param name name the name of the unit
     * @param health A value representing the health of a unit
     * @param attack A value representing the weapon
     * @param armor A value representing the defence of a unit
     *
     */
    public CommanderUnit(String name, int health, int attack, int armor, Terrain terrain) {
        super(name, health, attack, armor, terrain);
    }

    public CommanderUnit(String name, int health, Terrain terrain) {
        super(name, health, 25, 15, terrain);
    }

    /**
     *
     */
    @Override
    protected String getUnitAsString() {
        return "Commander unit" + "," + getName() + "," + getHealth();
    }

}
