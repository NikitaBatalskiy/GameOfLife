package controllers;

import java.util.ArrayList;
import java.util.List;
import model.Board;
import model.Point;

public class PointsController {

  private Board board;

  public PointsController(Board board) {
    this.board = board;
  }

  public List<Point> setRandomList(int countOfAlivePoints) {
    List<Point> randomList = new ArrayList<>();
    Point point;
    int count = 0;
    int width = board.getWidth();
    int height = board.getHeight();

    for (int matrixY = 0; matrixY < height; matrixY++) {
      for (int matrixX = 0; matrixX < width; matrixX++) {
        point = new Point(matrixX, matrixY, false);
        randomList.add(point);
      }
    }

    while (count < countOfAlivePoints) {
      int random = (int) (Math.random() * (width * height));
      if (!randomList.get(random).isAlive()) {
        randomList.get(random).setAlive(true);
        count++;
      }
    }
    return randomList;
  }

  public void setAlivePoint(Board board, int matrixX, int matrixY) {
    board
        .getPoints()
        .forEach(
            point -> {
              if (point.getPositionX() == matrixX && point.getPositionY() == matrixY) {
                point.setAlive(true);
              }
            });
  }

  public void setDeadPoint(Board board, int matrixX, int matrixY) {
    board
        .getPoints()
        .forEach(
            point -> {
              if (point.getPositionX() == matrixX && point.getPositionY() == matrixY) {
                point.setAlive(false);
              }
            });
  }
}
