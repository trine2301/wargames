package no.ntnu.idata2001.wargame.data;

/**
 * This class represents the Cavalry unit.
 * Should have a little advantage in close combat, and in first attack.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class CavalryUnit extends Unit {
    private boolean firstAttack;
    private final int attackBonus;

    /**
     * Constructor for objects of class CavalryUnit.
     *
     * @param armor A value representing the defence of a unit
     * @param attack A value representing the weapon
     * @param health A value representing the health of a unit
     * @param name the name of the unit
     */
    public CavalryUnit(String name, int health, int attack, int armor, Terrain terrain) {
        super(name, health, attack, armor, terrain);
        this.firstAttack = false;
        this.attackBonus = 2;
    }
    /**
     * Simplified constructor for objects of class Unit, with values for attack and health.
     * @param name name of the unit
     * @param health A value representing the health of the unit
     */
    public CavalryUnit(String name, int health, Terrain terrain) {
        super(name, health, 20, 12, terrain);
        checkIfNameIsValid(name);
        checkIfHealthIsAboveZero(health);
        checkIfTerrainIsNotNull(terrain);

        this.firstAttack = false;
        this.attackBonus = 2;
    }


    /**
     * Method to give attack bonus to a unit.
     *
     * @return returns the right value of the total attack bonus
     */
    @Override
    protected int getAttackBonus() {
        int totalAttackBonus = 0;
        if (!firstAttack) {
            firstAttack = true;
            totalAttackBonus =  4 + attackBonus;
        } else {
            totalAttackBonus = attackBonus;
        }
        if (getTerrain() == Terrain.PLAINS) {
            totalAttackBonus += 1;
        }

        return totalAttackBonus;
    }


    @Override
    protected int getResistBonus() {
        int resistBonus = 0;
        if (getTerrain() != Terrain.FOREST) {
            resistBonus = 1;
        } else {
            resistBonus = 0;
        }
        return resistBonus;
    }


    /**
     *
     */
    @Override
    protected String getUnitAsString() {
        return "Cavalry unit" + "," + getName() + "," + getHealth();
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
