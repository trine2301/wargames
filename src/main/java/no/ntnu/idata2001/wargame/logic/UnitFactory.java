package no.ntnu.idata2001.wargame.logic;


import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.wargame.data.CavalryUnit;
import no.ntnu.idata2001.wargame.data.ClassOfUnit;
import no.ntnu.idata2001.wargame.data.CommanderUnit;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.RangedUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;

/**
 * A class to represent the creation of a unit, based on unit-type, name and health.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class UnitFactory {

  /**
   * Creates a unit.
   *  @param unitType the type of the unit
   * @param name     the name of the unit
   * @param health   the health of the unit
   * @return
   */
  public Unit createUnit(ClassOfUnit unitType, String name, int health, Terrain terrain) {
    return switch (unitType) {
      case CAVALRYUNIT -> new CavalryUnit(name, health, terrain);
      case INFANTRYUNIT -> new InfantryUnit(name, health, terrain);
      case COMMANDERUNIT -> new CommanderUnit(name, health, terrain);
      case RANGEDUNIT -> new RangedUnit(name, health, terrain);
    };
  }


  /**
   * Creates a list of n number of a desired unit.
   *
   * @param numberOfUnits the number of units to list
   * @param unitType the type of unit to list
   * @param name the name of the unit
   * @param health the health of the unit
   *
   * @return a list of with desired number of units based on unit-type, name and health.
   */
  public List<Unit> createListOfUnits(int numberOfUnits, ClassOfUnit unitType, String name, int health, Terrain terrain) {

    List<Unit> listOfUnits = new ArrayList<>();
    for (int i=0; i<numberOfUnits; i++){
      listOfUnits.add(createUnit(unitType, name, health, terrain));
    }

    return listOfUnits;
  }

}

