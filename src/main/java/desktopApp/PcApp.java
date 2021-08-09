package desktopApp;

import desktopApp.view.MainView;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PcApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    MainView mainView = new MainView(primaryStage);
    Scene scene = new Scene(mainView, 300, 220);
    primaryStage.setTitle("Game of Life");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
