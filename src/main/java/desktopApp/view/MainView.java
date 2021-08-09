package desktopApp.view;

import controllers.BoardController;
import controllers.PointsController;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Affine;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Board;
import model.Point;

public class MainView extends VBox {

  private Button startGame;
  private Label label1;
  private Label label2;
  private Label label3;
  private TextField width;
  private TextField height;
  private TextField countOfAlivePoints;
  private Board board;
  private BoardController boardController;
  private PointsController pointsController;

  public MainView(Stage stage) {
    startGame = new Button("Start Game");
    label1 = new Label("Enter width of the board: ");
    label2 = new Label("Enter height of the board: ");
    label3 = new Label("Enter count of alive \npoints in the beginning: ");
    width = new TextField();
    height = new TextField();
    countOfAlivePoints = new TextField();
    startGame.setOnAction(
        actionEvent -> {
          initBoard(width.getText(), height.getText(), countOfAlivePoints.getText());
          Scene newScene = new Scene(new SimulationView(boardController, stage), 810, 840);
          stage.setScene(newScene);
          stage.setX(100);
          stage.setY(100);
          stage.show();
        });

    getChildren().addAll(label1, width, label2, height, label3, countOfAlivePoints, startGame);
  }

  private void initBoard(String widthTF, String heightTF, String amount) {
    if (validateTextField(widthTF) && validateTextField(heightTF) && validateTextField(amount)) {
      board = new Board(Integer.parseInt(widthTF), Integer.parseInt(heightTF));
      boardController = new BoardController(board);
      pointsController = new PointsController(board);
      List<Point> points = pointsController.setRandomList(Integer.parseInt(amount));
      board.setPoints(points);
    } else {
     width.setText("");
     height.setText("");
     countOfAlivePoints.setText("");
    }
  }

  private boolean validateTextField(String text) {
    return text.matches("[0-9]*");
  }
}
