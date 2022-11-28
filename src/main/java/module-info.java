module no.ntnu.idata2001.wargame {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires java.logging;

  opens no.ntnu.idata2001.wargame.ui to javafx.fxml, javafx.graphics, javafx.controls;

  opens no.ntnu.idata2001.wargame.logic;
  opens no.ntnu.idata2001.wargame.data.exception;


  exports no.ntnu.idata2001.wargame.ui;
  exports no.ntnu.idata2001.wargame.logic;
  exports no.ntnu.idata2001.wargame.data.exception;
  exports no.ntnu.idata2001.wargame.data;
  opens no.ntnu.idata2001.wargame.data;


}