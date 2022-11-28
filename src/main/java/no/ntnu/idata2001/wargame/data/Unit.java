package no.ntnu.idata2001.wargame.data;

/**
 * This class represents a unit in an army.
 * This class is an abstract superclass.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public abstract class Unit {
    private int health;
    private final String name; //name of the type of fighter
    private final int attack;
    private int armor;
    private Terrain terrain;

    /**
     * Constructor for objects of class Unit
     *
     * @param armor  A value representing the defence of a unit
     * @param attack A value representing the weapon
     * @param health A value representing the health of a unit
     * @param name   The name of the unit
     */
    protected Unit(String name, int health, int attack, int armor, Terrain terrain) {
        checkIfNameIsValid(name);
        checkIfHealthIsAboveZero(health);
        checkIfTerrainIsNotNull(terrain);
        this.health = health;
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.terrain = terrain;
    }

    /**
     *
     * @return
     */
    protected Terrain getTerrain(){
        return terrain;
    }

    /**
     * setting terrain
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }


    /**
     * A unit can attack an opponent using this method.
     * Hands out damage to a victim.
     *
     * @param victim is the opponent that is being attacked.
     */
    public void attack(Unit victim) {
        int totalAttack = attack + getAttackBonus();
        victim.reduceHealth(totalAttack);
    }

    /**
     * The total defense-capabibity of the opponent.
     *
     * @param opponent is the opponent with the armor
     */
    public void totalDefense(Unit opponent) {
        this.armor = getArmor() + getResistBonus();
    }


    /**
     * Return the name of a unit.
     * @return the name of a unit.
     */
    public String getName() {
        return name;
    }



    /**
     * A method to show the attack-value representing the unit`s weapon.
     * @return the attack-value representing the unit`s weapon.
     */
    public int getAttack() {
        return attack;
    }


    /**
     * Return a value representing defense-capability.
     * @return a value representing defense-capability
     */
    public int getArmor() {
        return armor;
    }


    /**
     * Takes in damage from attack()-method, and reduces health of a unit accordingly.
     * @param totalDamageDone the damage done by an attacker.
     */
    private void reduceHealth(int totalDamageDone) {
        checkIfHealthIsAboveZero(health);
        int damage = totalDamageDone + armor;
        if (this.health < damage) {
            this.health = 0;
        } else {
            this.health = this.health - damage;
        }
    }

    /**
     * Sets the health of a unit.
     * @param health the health of a unit.
     * @throws IllegalArgumentException this gets thrown when health is less than zero.
     */
    private void setHealth(int health) {
        checkIfNumberIsAboveZero(health, "health");
        this.health = health;
    }


    /**
     * Return the health of a unit. Value reduces when under attack.
     * @return the health of a unit.
     */
    public int getHealth() {
        return health;
    }


    /**
     * Return attackBonus. Different for different units.
     *
     * @return bonus in case of attack. Added to the attack value when
     * attacks another unit.
     */
    protected abstract int getAttackBonus();

    /**
     * Return defense bonus. Different value for different units.
     *
     * @return defense bonus. Added the defense value of the device
     * who are attacked.
     */
    protected abstract int getResistBonus();



    /**
     *
     * @return
     */
    protected abstract String getUnitAsString();


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

    /**
     * Checks if terain is not null.
     * @param terrain the terrain to check
     */
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

    /**
     * toString()-method, used for debugging.
     */
    @Override
    public String toString() {
        return "Unit{" +
            "health=" + health +
            ", name='" + name + '\'' +
            ", attack=" + attack +
            ", armor=" + armor +
            '}';
    }

}
