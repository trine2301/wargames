package no.ntnu.idata2001.wargame.logic;

import no.ntnu.idata2001.wargame.data.Army;
import no.ntnu.idata2001.wargame.data.CavalryUnit;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;
import no.ntnu.idata2001.wargame.data.exception.CouldNotRemoveUnitException;

/**
 * A class that simulates a battle between two armies.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class Battle {
  private final Army armyOne;
  private final Army armyTwo;


  /**
   * Constructor for objects of class Battle.
   *
   * @param armyOne represents one of the armies.
   * @param armyTwo represents one of the armies.
   */
  public Battle(Army armyOne, Army armyTwo) {
    if (armyOne == null || armyTwo == null) {
      throw new IllegalArgumentException("Army cannot be null");
    } else {
      this.armyOne = armyOne;
      this.armyTwo = armyTwo;
    }

  }


  /**
   * Random unit from one ArmyOne attacks another random unit from ArmyTwo.
   * If a unit has health == 0, it will be removed from that Army.
   * This will continue until one of the armies are destroyed.
   *
   * @return the name of the winning army (ArmyOne or ArmyTwo)
   */

  public Army simulate() throws CouldNotRemoveUnitException {
    Army winner = null;

    while (armyOne.hasUnits() && armyTwo.hasUnits()) {

      Unit unitFromArmyOne = armyOne.getRandom();
      Unit unitFromArmyTwo = armyTwo.getRandom();

      unitFromArmyOne.attack(unitFromArmyTwo);
      System.out.println(unitFromArmyTwo);
      unitFromArmyTwo.attack(unitFromArmyOne);
      System.out.println(unitFromArmyOne);

      if (unitFromArmyOne.getHealth() == 0) {
        armyOne.remove(unitFromArmyOne);
      }
      if (unitFromArmyTwo.getHealth() == 0) {
        armyTwo.remove(unitFromArmyTwo);
      }
    }

    if (armyOne.hasUnits()) {
      winner = armyOne;
    } else if (armyTwo.hasUnits()) {
      winner = armyTwo;
    }

    // Will return null if both Armies die at the same time.
    // if null is returned --> draw
    return winner;
  }


//  public static void main(String[] args) {
//    Army armyOne = new Army("Ari");
//    Army armyTwo = new Army("Trine");
//    Battle battle = new Battle(armyOne, armyTwo);
//
//
//    for (int i = 0; i < 5; i++) {
//      armyOne.addUnit(new CavalryUnit("CavalryOne", 100, Terrain.FOREST));
//      armyTwo.addUnit(new CavalryUnit("CavalryTwo", 100, Terrain.FOREST));
//      armyOne.addUnit(new InfantryUnit("InfantryOne", 100, Terrain.FOREST));
//      armyTwo.addUnit(new InfantryUnit("InfantryTwo", 100, Terrain.FOREST));
//    }
//
//
//    try {
//      Army winner = battle.simulate();
//      if (winner != null) {
//        System.out.println(winner.getName());
//      } else {
//        System.out.println("Draw");
//      }
//
//    } catch (CouldNotRemoveUnitException e) {
//      e.printStackTrace();
//    }
//  }

}
