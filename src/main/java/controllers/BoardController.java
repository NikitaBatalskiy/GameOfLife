package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import model.Board;
import model.Point;

public class BoardController {

  private Board board;

  public BoardController(Board board) {
    this.board = board;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public void printBoard() {
    System.out.println(board.toString());
  }

  private int countAliveNeighbours(int matrixX, int matrixY) {
    int count = 0;

    count += getStatusOfPoint(matrixX - 1, matrixY - 1);
    count += getStatusOfPoint(matrixX, matrixY - 1);
    count += getStatusOfPoint(matrixX + 1, matrixY - 1);

    count += getStatusOfPoint(matrixX - 1, matrixY);
    count += getStatusOfPoint(matrixX + 1, matrixY);

    count += getStatusOfPoint(matrixX - 1, matrixY + 1);
    count += getStatusOfPoint(matrixX, matrixY + 1);
    count += getStatusOfPoint(matrixX + 1, matrixY + 1);

    return count;
  }

  private int getStatusOfPoint(int matrixX, int matrixY) {
    AtomicInteger count = new AtomicInteger();
    if (matrixX < 0 || matrixX >= board.getWidth() || matrixY < 0 || matrixY >= board.getHeight()) {
      return count.get();
    }
    board
        .getPoints()
        .forEach(
            point -> {
              if (point.getPositionX() == matrixX && point.getPositionY() == matrixY) {
                if (point.isAlive()) {
                  count.set(1);
                } else count.set(0);
              }
            });
    return count.get();
  }

  public void simulate() {
    List<Point> newPoints = new ArrayList<>();
    Point newPoint;
    for (Point point : board.getPoints()) {
      int aliveNeighbours = countAliveNeighbours(point.getPositionX(), point.getPositionY());
      newPoint =
          new Point(
              point.getPositionX(), point.getPositionY(), isPointAlive(aliveNeighbours, point));
      newPoints.add(newPoint);
    }
    board.setPoints(newPoints);
  }

  private boolean isPointAlive(int aliveNeighbours, Point point) {
    if (!point.isAlive() && aliveNeighbours == 3) {
      return true;
    }
    if (aliveNeighbours == 2 || aliveNeighbours == 3) {
      if (point.isAlive()) return true;
      else return aliveNeighbours == 3;
    }
    return false;
  }

  public int getCountOfAlivePointsInBoard() {
    return (int) board.getPoints().stream().filter(Point::isAlive).count();
  }
}
