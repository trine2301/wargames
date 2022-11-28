package no.ntnu.idata2001.wargame.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The wargame application.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class WargameApp extends Application {

  /**
   * Main starting point for the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

      WargameController wargameController = new WargameController();
      fxmlLoader.setController(wargameController);
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root, 800, 450);
      wargameController.setNewGameButton();
      wargameController.setNewArmyOneButton();
      wargameController.setNewArmyTwoButton();
      wargameController.setStartButton();
      wargameController.setChooseTerrainComboBox();
      wargameController.setMenusItemsFunctions();
      primaryStage.setTitle("Wargame!");
      primaryStage.setScene(scene);
      primaryStage.show();
      wargameController.updateWindow();
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
