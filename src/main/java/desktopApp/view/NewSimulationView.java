package desktopApp.view;

import controllers.BoardController;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class NewSimulationView extends VBox {

  private boolean isSimulating = true;
  private final Canvas canvas;
  private final Affine affine;
  private final BoardController boardController;

  public NewSimulationView(BoardController boardController, Timeline timeline) {
    this.boardController = boardController;

    HBox buttonsMenu = new HBox(5);
    Button stepButton = new Button("Step");
    Button simulateButton = new Button("Simulate");
    Button stopButton = new Button("Stop");
    buttonsMenu.getChildren().addAll(stepButton, simulateButton, stopButton);
    stepButton.setOnAction(
        actionEvent -> {
          boardController.simulate();
          drawSimulation();
        });

    simulateButton.setOnAction(
        actionEvent -> {
          if (isSimulating) simulateButton.setDisable(true);
          else{
            timeline.play();
            isSimulating = true;
          }
        });

    stopButton.setOnAction(
        actionEvent -> {
          if (isSimulating) {
            timeline.stop();
            isSimulating = false;
          } else stopButton.setDisable(true);
        });
    canvas = new Canvas(800, 800);
    affine = new Affine();
    float width = boardController.getBoard().getWidth();
    float height = boardController.getBoard().getHeight();
    affine.appendScale(800 / width, 800 / height);
    getChildren().addAll(buttonsMenu, canvas);
    boardController.simulate();
    drawSimulation();
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
}
