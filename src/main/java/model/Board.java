package model;

import java.util.List;

public class Board {
  private int width;
  private int height;
  private List<Point> points;

  public Board(int width, int height, List<Point> points) {
    this.width = width;
    this.height = height;
    this.points = points;
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  @Override
  public String toString() {
    StringBuilder line = new StringBuilder("-----------\n");
    for (int matrixY = 0; matrixY < height; matrixY++) {
      line.append("|");
      for (int matrixX = 0; matrixX < width; matrixX++) {
        int finalMatrixX = matrixX;
        int finalMatrixY = matrixY;
        points.forEach(
            point -> {
              if (point.getPositionX() == finalMatrixX && point.getPositionY() == finalMatrixY) {
                if (point.isAlive()) line.append("*");
                else line.append(" ");
              }
            });
      }
      line.append("|\n");
    }
    line.append("-----------\n");
    return String.valueOf(line);
  }
}
