package no.ntnu.idata2001.wargame.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.wargame.data.Army;
import no.ntnu.idata2001.wargame.data.CavalryUnit;
import no.ntnu.idata2001.wargame.data.ClassOfUnit;
import no.ntnu.idata2001.wargame.data.CommanderUnit;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.RangedUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;
import no.ntnu.idata2001.wargame.data.exception.FileFormatException;

/**
 * A class to handle files.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class FileHandler {

  private File saveFile;
  private File readFile;


  /**
   * Checking if file exist, and creating a new file if not.
   *
   * @param army the army to be saved in a file.
   */
  public void saveArmyToFile(Army army) throws IOException {
    String pathAsString = System.getProperty("user.home") +
        "\\Desktop"; //user.home is stave (home of the user), and //Desktop takes you to desktop.
    String fileName = army.getName() + ".csv";
    saveFile = new File(pathAsString + "\\" + fileName);
    if (!saveFile.isFile()) { //if the file does not exist, it will create a new file.
      saveFile.createNewFile();
    }
    writeToFile(army);
  }


  /**
   * Write to file.
   *
   * @param army the army to be added to file.
   * @throws IOException gets thrown if file does not exist.
   */
  public void writeToFile(Army army) throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new OutputStreamWriter(
            new FileOutputStream(saveFile), StandardCharsets.UTF_8))) {
      List<String> list = army.getAsList();
      for (String string : list) {
        bufferedWriter.write(string);
        bufferedWriter.newLine();
      }
    } catch (IOException exception) { //removed FileNotFoundException because it inherits IOException
      exception.printStackTrace();
      throw exception;
    }
  }

  /**
   * Read from file.
   * @return
   */
  public Army readFromFile(File file) throws IOException, FileFormatException {
    if (!file.isFile()) {
      throw new FileNotFoundException("The file is not a file. ");
    }
    String armyName = null;
    List<Unit> unitList = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line = bufferedReader.readLine();

      while (line != null) {
        System.out.println(line);

        String[] splitOnComma = line.split(",");
        if (splitOnComma.length == 3) {
          String unit = splitOnComma[0];
          String name = splitOnComma[1];
          int health = Integer.parseInt(splitOnComma[2]);
          if (unit.equalsIgnoreCase("Cavalry unit")){
            unitList.add(new CavalryUnit(name, health, Terrain.FOREST));
          } else if (unit.equalsIgnoreCase("Infantry unit")) {
            unitList.add(new InfantryUnit(name, health, Terrain.FOREST));
          } else if (unit.equalsIgnoreCase("Ranged unit")) {
            unitList.add(new RangedUnit(name, health, Terrain.FOREST));
          } else if (unit.equalsIgnoreCase("Commander unit")) {
            unitList.add(new CommanderUnit(name, health, Terrain.FOREST));
          }else {
            throw new FileFormatException("Format is wrong on file");
          }
        } else if (splitOnComma.length == 1) {
          armyName = splitOnComma[0];
        } else {
          throw new FileFormatException("Wrong format on file");
        }

        line = bufferedReader.readLine();
      }
    } catch (IOException | FileFormatException exception) {
      throw exception;
    }

    return new Army(armyName, unitList);
  }


  public static void main(String[] args) throws IOException {
    List<Unit> armyOneList = new ArrayList<>();
    List<Unit> armieTwoList = new ArrayList<>();
    for (int i = 0; i < 500; i++){
      UnitFactory unitFactory = new UnitFactory();
      armyOneList.add(unitFactory.createUnit(ClassOfUnit.CAVALRYUNIT, "hei", 100, Terrain.FOREST));
      armyOneList.add(unitFactory.createUnit(ClassOfUnit.INFANTRYUNIT, "Horsie", 100, Terrain.FOREST));
      armyOneList.add(unitFactory.createUnit(ClassOfUnit.RANGEDUNIT, "Bowman", 100, Terrain.FOREST));
      armyOneList.add(unitFactory.createUnit(ClassOfUnit.COMMANDERUNIT, "Bowman", 100, Terrain.FOREST));

      armieTwoList.add(unitFactory.createUnit(ClassOfUnit.CAVALRYUNIT, "Bjarne", 100, Terrain.FOREST));
      armieTwoList.add(unitFactory.createUnit(ClassOfUnit.INFANTRYUNIT, "Fjel", 100, Terrain.FOREST));
      armieTwoList.add(unitFactory.createUnit(ClassOfUnit.RANGEDUNIT, "Ris", 100, Terrain.FOREST));
      armieTwoList.add(unitFactory.createUnit(ClassOfUnit.COMMANDERUNIT, "Jeez", 100, Terrain.FOREST));
    }

    Army army = new Army("Human", armyOneList);
    Army army1 = new Army("HAha", armieTwoList);
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveArmyToFile(army1);
    fileHandler.saveArmyToFile(army);
  }


  public static void hehe() throws IOException {
    String directoryAsString = System.getProperty("user.home") + "\\Desktop";
    System.out.println(directoryAsString);
    String fileName = "asdasdasd.txt"; //sett inn hello. Orginal navnet er "asdasdasd"
    File file = new File(directoryAsString + "\\" + fileName);
    if (!file.isFile()){
      System.out.println("The file is not there. Creating a new one with the name: " + fileName);
      file.createNewFile();
      //System.out.println("The and tada " + file.isFile());
    } else {
      System.out.println("Found the file " + fileName);
    }
  }


}