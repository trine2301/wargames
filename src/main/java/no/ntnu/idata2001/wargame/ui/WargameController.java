package no.ntnu.idata2001.wargame.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import no.ntnu.idata2001.wargame.data.Army;
import no.ntnu.idata2001.wargame.logic.Battle;
import no.ntnu.idata2001.wargame.logic.FileHandler;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.CavalryUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;
import no.ntnu.idata2001.wargame.data.exception.CouldNotRemoveUnitException;
import no.ntnu.idata2001.wargame.data.exception.FileFormatException;

/**
 * Controller class for UI.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class WargameController {
  @FXML
  private Button newGameButton;

  @FXML
  private Button newArmyOneButton;

  @FXML
  private Button newArmyTwoButton;

  @FXML
  private ComboBox<Army> chooseArmyOneComboBox;

  @FXML
  private ComboBox<Terrain> chooseTerrainComboBox;

  @FXML
  private Label armyOneNameLabel;

  @FXML
  private Label armyTwoNameLabel;

  @FXML
  private Button startButton;

  @FXML
  private TableView<Unit> armyOneTableView;

  @FXML
  private TableView<Unit> armyTwoTableView;

  @FXML
  private TableView<Unit> armyGrid;

  @FXML
  private MenuItem loadArmyOneMenu;

  @FXML
  private MenuItem loadArmyTwoMenu;

  @FXML
  private MenuItem saveArmyOneMenu;

  @FXML
  private MenuItem saveArmyTwoMenu;

  @FXML
  private MenuItem exitAppMenu;

  @FXML
  private TableView<String> gameInfo;


  private Army armyOne;

  private Army armyTwo;


  /**
   *Initializer for wargame controller.
   */
  public WargameController(){
    List<Unit> unitList = new ArrayList<>();
    ObservableList<Unit> unitListList = FXCollections.observableList(unitList);
    this.armyOne = new Army("Army One");
    this.armyTwo = new Army("Army Two");
  }

  //TODO this one. Should be initialized and not have constructor.
//  @FXML
//  public void initialize() {
//    this.deckOfCards = new DeckOfCards();
//    this.cardFaceImages = new HashMap<>();
//    this.loadCardFaceImages();
//    this.checkButton.setDisable(true);
//    StringBuilder version = new StringBuilder();
//    version.append(
//            "JavaFX version: " + System.getProperties().get("javafx.runtime.version").toString())
//        .append("     Java version: " + System.getProperty("java.version"));
//    this.versionLabel.setText(version.toString());
//  }

  /**
   * Setting function to menu items.
   * Load army will load an army from a file the user chooses.
   * Save army will save the army to a file.
   */
  public void setMenusItemsFunctions(){
    loadArmyOneMenu.setOnAction(event -> {
      loadArmyFromFile(1);
    });
    loadArmyTwoMenu.setOnAction(event -> {
      loadArmyFromFile(2);
    });
    saveArmyOneMenu.setOnAction(event -> {
      FileHandler fileHandler = new FileHandler();
      if (armyOne.hasUnits()) {
        try {
          fileHandler.saveArmyToFile(armyOne);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setHeaderText("Army successfully saved to file");
          alert.setContentText("Army successfully saved to file in desktop");
          alert.showAndWait();

        } catch (IOException e) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Could not save warning");
          alert.setHeaderText("Could not save army to file");
          alert.setContentText("Could not save army to file");
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Could not save warning");
        alert.setHeaderText("Could not save army to file");
        alert.setContentText("There was no armies to save to File.");
        alert.showAndWait();
      }
    });
    saveArmyTwoMenu.setOnAction(event -> {
      FileHandler fileHandler = new FileHandler();
      if (armyOne.hasUnits()) {
        try {
          fileHandler.saveArmyToFile(armyTwo);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setHeaderText("Army successfully saved to file");
          alert.setContentText("Army successfully saved to file in desktop");
          alert.showAndWait();

        } catch (IOException e) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Could not save warning");
          alert.setHeaderText("Could not save army to file");
          alert.setContentText("Could not save army to file");
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Could not save warning");
        alert.setHeaderText("Could not save army to file");
        alert.setContentText("There was no armies to save to File.");
        alert.showAndWait();
      }
    });


    exitAppMenu.setOnAction(event -> {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation dialog");
      alert.setHeaderText("Exit Wargames?");
      alert.setContentText("Are you sure you are done playing this awesome game?");
      Optional<ButtonType> result = alert.showAndWait();

      if (result.get() == ButtonType.OK) {
        Platform.exit();
      }
    });
  }


  /**
   * Method to load file.
   * @param armyNumber The number of the army to load to file.
   */
  private void loadArmyFromFile(int armyNumber){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Import army");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv file", "*.csv")); //will only show csv-files in fileChooser.
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      FileHandler filehandler = new FileHandler();
      try {
        Army armyFromFile = filehandler.readFromFile(file);
        //clears armies and adds them again, to make it simple for myself.
        newGameButton.setDisable(true);
        newArmyOneButton.setDisable(armyOne.hasUnits());
        newArmyTwoButton.setDisable(armyTwo.hasUnits());
        if (armyNumber == 1) {
          setArmyOne(armyFromFile);
          newArmyOneButton.setDisable(true);
        } else {
          this.armyTwo = armyFromFile;
          setArmyTwo(armyFromFile);
          newArmyTwoButton.setDisable(true);
        }
        updateTables();

      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Could not read file warning");
        alert.setHeaderText("Something went bad when trying to read file");
        alert.setContentText("Something went bad when trying to read file");
        alert.showAndWait();
      } catch (FileFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Could not read file error");
        alert.setHeaderText("Could not read file, because the format is wrong");
        alert.setContentText("Could not read file, because the format is wrong");
        alert.showAndWait();
      }

    }else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Could not load file");
      alert.setHeaderText("Could not load file");
      alert.setContentText("You didnt choose a file. Try again to choose a file. ");
      alert.showAndWait();
    }
  }

  /**
   * Showing winner in table.
   */
  private void infoTable(){
    gameInfo.getItems().addAll("hi");
  }

  /**
   * Updating tables.
   */
  private void updateTables(){
    armyTwoTableView.getItems().clear();
    armyOneTableView.getItems().clear();
    armyOneTableView.getItems().addAll(armyOne.getAllUnits());
    armyTwoTableView.getItems().addAll(armyTwo.getAllUnits());
    gameInfo.getItems().clear();
    gameInfo.getItems().addAll("hi");
  }



  /**
   * Setting function to newGameButton.
   */
  public void setNewGameButton() {
    newArmyOneButton.setDisable(true);
    newArmyTwoButton.setDisable(true);
    newGameButton.setOnAction(actionEvent -> {
      //This should be in gameInfo-table
      System.out.println("Choose an army!");
      newGameButton.setDisable(true);
      newArmyOneButton.setDisable(false);
      newArmyTwoButton.setDisable(false);
      armyOneTableView.getItems().clear();
      armyTwoTableView.getItems().clear();
    });
  }

  /**
   * Method to update window, before each round.
   */
  public void updateWindow(){
    if (armyOneTableView.getColumns().isEmpty()) {
      fillUp(armyOneTableView);
      fillUp(armyTwoTableView);
      //fillGameInfoTable(gameInfo);
    }
  }

  /**
   * Setting function to newArmyOne-button.
   */
  public void setNewArmyOneButton() {
    newArmyOneButton.setOnAction(actionEvent -> {
      setArmyOne(new Army(armyOne.getName()));

      Terrain terrain = chooseTerrainComboBox.getSelectionModel().getSelectedItem();
      for (int i = 0; i < 5; i++) {
        armyOne.addUnit(new InfantryUnit("InfantryOne", 100, terrain));
        armyOne.addUnit(new InfantryUnit("InfantryTwo", 100, terrain));
      }

      //TODO in gameInfo-table
      System.out.println("New army succesfully made, they are an army of " + armyOne.getName() + ", yay!");
      newArmyOneButton.setDisable(true);

      updateTables();
      System.out.println(armyOneTableView.getItems().size());
    });
  }


  /**
   * Setting function to newArmyTwo-button.
   */
  public void setNewArmyTwoButton() {
    newArmyTwoButton.setOnAction(actionEvent -> {
      setArmyTwo(new Army(armyTwo.getName()));
      Terrain terrain = chooseTerrainComboBox.getSelectionModel().getSelectedItem();
          for (int i = 0; i < 5; i++) {
            armyTwo.addUnit(new CavalryUnit("CavalryOne", 100, terrain));
            armyTwo.addUnit(new InfantryUnit("InfantryOne", 100, terrain));
          }
          //TODO gameInfo table
      System.out.println("New army successfully made, they are an army of " + armyTwo.getName() + ", yay!");
      newArmyTwoButton.setDisable(true);

      updateTables();
      System.out.println(gameInfo.getItems().size());
    });
  }


  /**
   * Setting name to label.
   * @param army the name of the label
   */
  private void setArmyOne(Army army) {
    this.armyOne = army;
    armyOneNameLabel.setText(armyOne.getName());
  }


  /**
   * Setting name to label.
   * @param army the name of the label
   */
  private void setArmyTwo(Army army) {
    this.armyTwo = army;
    armyTwoNameLabel.setText(armyTwo.getName());
  }


  /**
   * Fills up a tableview with columns
   */
  private void fillUp(TableView<Unit> table){
    TableColumn<Unit, String> nameColumn = new TableColumn<>("Unit name");
    nameColumn.setCellValueFactory(value -> {
      String nameOfUnit = value.getValue().getName();
      SimpleStringProperty simpleStringProperty = new SimpleStringProperty(nameOfUnit);
      return simpleStringProperty;
    });
    TableColumn<Unit, Number> healthColumn = new TableColumn<>("Unit health");
    healthColumn.setCellValueFactory(value -> {
      int healthOfUnit = value.getValue().getHealth();
      SimpleIntegerProperty simpleHealthProperty = new SimpleIntegerProperty(healthOfUnit);
      return simpleHealthProperty;
        });
    table.getColumns().add(nameColumn);
    table.getColumns().add(healthColumn);


  }
//  private void fillGameInfoTable(TableView gameInfo){
//    TableView<String> infoScreen = new TableView<>();
//    inforScreen.setCellValueFactory(value -> {
//      String nameOfUnit = value.getValue().getName();
//      SimpleStringProperty simpleStringProperty = new SimpleStringProperty(nameOfUnit);
//      return simpleStringProperty;
//
//    });
//    gameInfo.getColumns().add(nameColumn);
//  }

  /**
   * Choose a pseudo random terrain.
   * If random is picked again, default terrain (forest) will be set.
   */
  private Terrain getPseudoRandomTerrain() {
    Terrain terrain;
    int pick = new Random().nextInt(Terrain.values().length);
    Terrain terrainPlayed = Terrain.values()[pick];

    if (terrainPlayed != Terrain.RANDOM) {
      terrain = terrainPlayed;
    } else {
      terrain = Terrain.FOREST;
    }
    return terrain;
  }



  /**
   * Setting function to chooseTerrain-comboBox.
   */
  public void setChooseTerrainComboBox(){
    chooseTerrainComboBox.getItems().add(Terrain.HILL);
    chooseTerrainComboBox.getItems().add(Terrain.PLAINS);
    chooseTerrainComboBox.getItems().add(Terrain.RANDOM);
    chooseTerrainComboBox.getSelectionModel().select(Terrain.FOREST); //default
  }



  /**
   * Setting function to startButton
   */
  public void setStartButton() {
    startButton.setOnAction(actionEvent -> {

      System.out.println(chooseTerrainComboBox.getSelectionModel().getSelectedItem());

      Terrain terrain = chooseTerrainComboBox.getSelectionModel().getSelectedItem();
      Terrain randomTerrain = getPseudoRandomTerrain();

      Battle battle = new Battle(armyOne, armyTwo);

      if (terrain.equals(Terrain.RANDOM)) {
        terrain = getPseudoRandomTerrain();
        armyOne.getAllUnits().forEach(unit -> unit.setTerrain(randomTerrain));
        armyTwo.getAllUnits().forEach(unit -> unit.setTerrain(randomTerrain));
      } else {
        armyOne.getAllUnits().forEach(unit -> unit.setTerrain(chooseTerrainComboBox.getSelectionModel().getSelectedItem()));
        armyTwo.getAllUnits().forEach(unit -> unit.setTerrain(chooseTerrainComboBox.getSelectionModel().getSelectedItem()));
      }


      try {
        Army winner = battle.simulate();
        armyOneTableView.refresh();
        armyTwoTableView.refresh();
        if (winner != null) {
          System.out.println("The winner is: " + winner.getName() + ", congratulations!");
        } else {
          System.out.println("Its a draw, both armies are dead");
        }

      } catch (CouldNotRemoveUnitException e) {
        e.printStackTrace();
      }

      newGameButton.setDisable(false);
      System.out.println("The terrain you are in is: " + terrain);
    });
  }
}
