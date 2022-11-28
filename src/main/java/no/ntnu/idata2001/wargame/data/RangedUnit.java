package no.ntnu.idata2001.wargame.data;

/**
 * Represents a unit that is stronger from a distance.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */

public class RangedUnit extends Unit {
    private int numberOfAttacks;

    /**
     * Constructor for objects of class Unit
     *
     * @param armor  A value representing the defence of a unit
     * @param attack A value representing the weapon
     * @param health A value representing the health of a unit
     * @param name   name of the unit
     */
    public RangedUnit(String name, int health, int attack, int armor, Terrain terrain) {
        super(name, health, attack, armor, terrain);
        this.numberOfAttacks = 0;
    }

    /**
     * This constructor gives a value to the fields attack and armor.
     *
     * @param name The name of the unit
     * @param health A value representing the health of a unit
     */
    public RangedUnit(String name, int health, Terrain terrain) {
        super(name, health, 15, 8, terrain);
        checkIfNameIsValid(name);
        checkIfHealthIsAboveZero(health);
        checkIfTerrainIsNotNull(terrain);
        this.numberOfAttacks = 0;
    }


    @Override
    protected int getAttackBonus() {
        int attackBonus = 0;
        if (getTerrain() == Terrain.HILL) {
            attackBonus = 3;
        } else if (getTerrain() == Terrain.FOREST) {
            attackBonus = 1;
        } else {
            attackBonus = 2;
        }
        return attackBonus;
    }


    @Override
    protected int getResistBonus() {
        int bonus = 0;
        if (numberOfAttacks == 0) {
            numberOfAttacks++;
            bonus = 6;
        } else if (numberOfAttacks == 1) {
            numberOfAttacks++;
            bonus = 4;
        } else {
            bonus = 2;
        }
        return bonus;
    }

    /**
     *
     */
    @Override
    protected String getUnitAsString() {
        return "Ranged unit" + "," + getName() + "," + getHealth();
    }

    /**
     * Checks if name is valid.
     * @param name the name to check.
     */
    private void checkIfNameIsValid(String name) {
        checkString(name, "name");
    }

    /**
     * Checks if health is above zero.
     * @param health the health to check.
     */
    private void checkIfHealthIsAboveZero(int health) {
        checkIfNumberIsAboveZero(health, "health");
    }

    private void checkIfTerrainIsNotNull(Terrain terrain) {
        checkIfObjectIsNull(terrain, "terrain");
    }

    /**
     * Checks if the input long is above zero.
     * @param number the number to check.
     * @param prefix the prefix the error should have.
     * @throws IllegalArgumentException gets thrown if the number is below or equal to zero.
     */
    private void checkIfNumberIsAboveZero(long number, String prefix){
        if (number <= 0){
            throw new IllegalArgumentException("The " + prefix + " must be above zero.");
        }
    }

    /**
     * Checks if a string is of a valid format or not.
     * @param stringToCheck the string you want to check.
     * @param errorPrefix   the error the exception should have if the string is invalid.
     * @throws IllegalArgumentException gets thrown if the string is empty or null.
     */
    private void checkString(String stringToCheck, String errorPrefix) {
        checkIfObjectIsNull(stringToCheck, errorPrefix);
        if (stringToCheck.isEmpty()) {
            throw new IllegalArgumentException("The " + errorPrefix + " cannot be empty.");
        }
    }

    /**
     * Checks if an object is null.
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}