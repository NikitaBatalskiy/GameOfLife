package desktopApp.view;

import controllers.BoardController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationView extends VBox {

  private boolean isSimulating = false;
  private Timeline timeline;
  private final Canvas canvas;
  private final Affine affine;
  private final BoardController boardController;
  private Stage stage;

  public SimulationView(BoardController boardController, Stage stage) {
    this.boardController = boardController;
    this.stage = stage;

    HBox buttonsMenu = new HBox(5);
    Button stepButton = new Button("Step");
    Button simulateButton = new Button("Simulate");
    Button stopButton = new Button("Stop");
    buttonsMenu.getChildren().addAll(stepButton, simulateButton, stopButton);
    canvas = new Canvas(800, 800);
    affine = new Affine();
    timeline = new Timeline(new KeyFrame(Duration.millis(500), this::simulateAndDrawIt));
    timeline.setCycleCount(Timeline.INDEFINITE);
    float width = boardController.getBoard().getWidth();
    float height = boardController.getBoard().getHeight();
    affine.appendScale(800 / width, 800 / height);
    getChildren().addAll(buttonsMenu, canvas);
    drawSimulation();

    stepButton.setOnAction(
        actionEvent -> {
          boardController.simulate();
          drawSimulation();
        });

    simulateButton.setOnAction(
        actionEvent -> {
          timeline.play();
        });

    stopButton.setOnAction(
        actionEvent -> {
          if (isSimulating) {
            timeline.stop();
          } else stopButton.isDisabled();
        });
  }

  public void drawSimulation() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setTransform(affine);

    gc.setFill(Color.LIGHTGRAY);
    gc.fillRect(0, 0, 800, 800);

    gc.setFill(Color.BLACK);
    boardController
        .getBoard()
        .getPoints()
        .forEach(
            point -> {
              if (point.isAlive()) {
                gc.fillRect(point.getPositionX(), point.getPositionY(), 1, 1);
              }
            });

    gc.setStroke(Color.GRAY);
    gc.setLineWidth(0.05);
    float width = boardController.getBoard().getWidth();
    float height = boardController.getBoard().getHeight();
    for (int x = 0; x < width; x++) {
      gc.strokeLine(x, 0, x, height);
    }

    for (int y = 0; y < height; y++) {
      gc.strokeLine(0, y, width, y);
    }
  }

  private void simulateAndDrawIt(ActionEvent actionEvent) {
    Scene newScene = new Scene(new NewSimulationView(boardController, timeline), 810, 840);
    stage.setScene(newScene);
    stage.setX(100);
    stage.setY(100);
    stage.show();
  }
}
