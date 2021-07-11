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

  public List<Point> setRandomList( int countOfAlivePoints){
    List<Point> randomList = new ArrayList<>();
    Point point;
    int count = 0;
    int width = board.getWidth();
    int height = board.getHeight();
    while (count < countOfAlivePoints) {
      for (int matrixY = 0; matrixY < height; matrixY++) {
        for (int matrixX = 0; matrixX < width; matrixX++) {
          int random = (int) (1 + Math.random() * 2);
          if (random == 1) {
            point = new Point(matrixX, matrixY, false);
          } else {
            point = new Point(matrixX, matrixY, true);
          }
          randomList.add(point);
          count++;
        }
      }
    }
    return randomList;
  }

  public void setAlivePoint(Board board, int matrixX, int matrixY) {
    board.getPoints().forEach(point -> {
      if (point.getPositionX() == matrixX && point.getPositionY() == matrixY) {
        point.setAlive(true);
      }
    });
  }

  public void setDeadPoint(Board board, int matrixX, int matrixY) {
    board.getPoints().forEach(point -> {
      if (point.getPositionX() == matrixX && point.getPositionY() == matrixY) {
        point.setAlive(false);
      }
    });
  }
}
