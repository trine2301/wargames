package no.ntnu.idata2001.wargame.data;

/**
 * Represents the infantry unit.
 * This is a unit which is stronger in close combat.
 *
 *
 * @author Trine Staverløkk
 * @version 0.1
 */

public class InfantryUnit extends Unit {

    /**
     * Constructor for objects of class InfantryUnit
     *
     * @param name The name of the unit
     * @param health A value representing the health of a unit
     * @param attack A value representing the weapon
     * @param armor A value representing the defence of a unit
     */
    public InfantryUnit(String name, int health, int attack, int armor, Terrain terrain) {
        super(name, health, attack, armor, terrain);
    }

    /**
     * This constructor gives a value to the fields attack and armor.
     * @param name The name of the unit
     * @param health A value representing the health of a unit
     */
    public InfantryUnit(String name, int health, Terrain terrain) {
        super(name, health, 10, 10, terrain);
        checkIfNameIsValid(name);
        checkIfHealthIsAboveZero(health);
        checkIfTerrainIsNotNull(terrain);
    }

    /**
     * Method to give attack bonus to a unit.
     * @return returns the value 2 to represent the attack bonus.
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 0;
        if (getTerrain() == Terrain.FOREST) {
            attackBonus = 3;
        } else {
            attackBonus = 2;
        }
        return attackBonus;
    }


    /**
     * Method to give resistance bonus to the unit.
     * @return returns the value 1 to represent the defence bonus.
     */
    @Override
    protected int getResistBonus() {
        int resistBonus = 0;
        if(getTerrain() == Terrain.FOREST) {
            resistBonus = 2;
        } else {
            resistBonus = 1;
        }
        return resistBonus;
    }

    /**
     *
     */
    @Override
    protected String getUnitAsString() {
        return "Infantry unit" + "," + getName() + "," + getHealth();
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
