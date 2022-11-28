package no.ntnu.idata2001.wargame.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import no.ntnu.idata2001.wargame.data.exception.CouldNotRemoveUnitException;


/**
 * This class represents all units in the battle to fight each other.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class Army {
    private final String name;
    private final List<Unit> units;

    /**
     * Constructor for objects of class Army.
     *
     * @param name the name of the unit to be added in an army.
     *
     * @throws IllegalArgumentException This gets thrown when name is null.
     */
    public Army(String name) {
        checkIfNameIsValid(name);
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Constructor for objects of class Army.
     *
     * @param name the name of the unit to be added in an army.
     * @param units a list of units in the battle.
     *
     * @throws IllegalArgumentException This gets thrown when name is null or empty.
     */
    public Army(String name, List<Unit> units) {
        checkIfNameIsValid(name);
        checkIfObjectIsNull(units, "Unit list");
        this.name = name;
        this.units = units;
    }

    /**
     * Method to show the name of a unit.
     *
     * @return the name of a unit.
     */
    public String getName() {
        return this.name;
    }



    /**
     * Adds a unit to unit-list.
     *
     * @param unit the unit to be added to unit list.
     *
     * @throws IllegalArgumentException This gets thrown when unit is null.
     */
    public void addUnit(Unit unit) {
        checkIfObjectIsNull(unit, "unit");
        units.add(unit);
    }


    /**
     * Adds all units from input-list units into list of units.
     *
     * @param units units to be added to units-list.
     */
    public void addAll(List<Unit> units) {
        checkIfObjectIsNull(units, "units");
        units.addAll(units);
    }

    /**
     * Remove a unit from the list of units.
     *
     * @param unit the unit to be removed.
     *
     * @throws IllegalArgumentException This gets thrown when unit is null.
     * @throws CouldNotRemoveUnitException This gets thrown when unit is not in army.
     */
    public void remove(Unit unit) throws CouldNotRemoveUnitException {
        checkIfObjectIsNull(unit, "unit");
        if (!units.remove(unit)) { //this happens when the unit is not in list.
            throw new CouldNotRemoveUnitException("The unit could not be found in the register");
        }

    }


    /**
     * Method to check if list units is empty or not.
     *
     * @return true if list of units has elements, false if empty list.
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }


    /**
     * A method to show all units in list.
     *
     * @return All units in list of units.
     *
     */
    public List<Unit> getAllUnits() {
        return this.units;
    }


    /**
     * A method to show a random element from units.
     *
     * @return Random element from list units
     */
    public Unit getRandom() {
        Random rand = new Random();
        return units.get(rand.nextInt(units.size()));
    }


    /**
     * Gets the infantry units and adds to a new list.
     *
     * @return the instances of units of the type infantry-unit.
     */
    public List<Unit> getInfantryUnits() {
        List<Unit> listOfInfantryUnits = units.stream().filter(unit -> unit instanceof InfantryUnit).toList();
        return listOfInfantryUnits;
    }

    /**
     * Gets the Cavalry units and adds to a new list.
     *
     * @return the instances of units of the type cavalry-unit.
     */
    public List<Unit> getCavalryUnits() {
        List<Unit> listOfCavalryUnits = units.stream().filter(unit -> unit instanceof CavalryUnit).toList();
        return listOfCavalryUnits;
    }

    /**
     * Gets the ranged units and adds to a new list.
     *
     * @return the instances of units of the type range-unit.
     */
    public List<Unit> getRangedUnits() {
        List<Unit> listOfRangedUnits = units.stream().filter(unit -> unit instanceof RangedUnit).toList();
        return listOfRangedUnits;
    }

    /**
     * Gets the Commander units and adds to a new list.
     *
     * @return the instances of units of the type commander-unit.
     */
    public List<Unit> getCommanderUnits() {
        List<Unit> listOfCommanderUnits = units.stream().filter(unit -> unit instanceof CommanderUnit).toList();
        return listOfCommanderUnits;
    }


    /**
     * Checks what type a unit is by comparing.
     *
     * @param desiredUnit the unit to put in list
     * @return the instances of units wanted in list.
     */
    public Boolean getDesiredUnit(Unit desiredUnit, ClassOfUnit classOfUnit) {
        boolean valid = false;
        if (classOfUnit == ClassOfUnit.CAVALRYUNIT && !(desiredUnit instanceof CommanderUnit)) {
            valid = desiredUnit instanceof CavalryUnit;
        } else if (classOfUnit == ClassOfUnit.COMMANDERUNIT) {
            valid = desiredUnit instanceof CommanderUnit;
        } else if (classOfUnit == ClassOfUnit.RANGEDUNIT) {
            valid = desiredUnit instanceof RangedUnit;
        } else if (classOfUnit == ClassOfUnit.INFANTRYUNIT) {
            valid = desiredUnit instanceof InfantryUnit;
        }
        return valid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Army{" +
            "name='" + name + '\'' +
            '}';
    }


    public List<String> getAsList() {
        List<String> list = new ArrayList();
        list.add(name);
        units.forEach(unit -> list.add(unit.getUnitAsString()));
        return list;
    }


    /**
     * Checks if name is valid.
     *
     * @param name the name to check.
     */
    private void checkIfNameIsValid(String name) {
        checkString(name, "name");
    }


    /**
     * Checks if a string is of a valid format or not.
     *
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
     *
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     *
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }

}





