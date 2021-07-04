package model;

import java.util.List;

public class Board {
  private int width;
  private int height;
  private int[][] matrix;
  private List<Point> points;

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.matrix = new int[width][height];
  }

  //Не получается делать новую строку при каждой иттерации
  @Override
  public String toString() {
    StringBuilder line = new StringBuilder();
    for (int matrixY = 0; matrixY < height; matrixY++) {
      line.append("|");
      for (int matrixX = 0; matrixX < width; matrixX++) {
        if (matrix[matrixX][matrixY] == 1) {
          line.append("*");
        } else {
          line.append("-");
        }
      }
      line.append("|");
    }
    line.append("-----------\n");
    return String.valueOf(line);
  }

  public void printBoard() {
    System.out.println("-----------");
    for (int matrixY = 0; matrixY < height; matrixY++) {
      StringBuilder string = new StringBuilder("|");
      for (int matrixX = 0; matrixX < width; matrixX++) {
        if (matrix[matrixX][matrixY] == 1) {
          string.append("*");
        } else {
          string.append("-");
        }
      }
      string.append("|");
      System.out.println(string);
    }
    System.out.println("-----------\n");
  }

  public void setAlivePosition(int matrixX, int matrixY) {
    matrix[matrixX][matrixY] = 1;
  }

  public void setDeadPosition(int matrixX, int matrixY) {
    matrix[matrixX][matrixY] = 0;
  }

  private int countAliveNeighbours(int matrixX, int matrixY) {
    int count = 0;

    count += getStatusOfPosition(matrixX - 1, matrixY - 1);
    count += getStatusOfPosition(matrixX, matrixY - 1);
    count += getStatusOfPosition(matrixX + 1, matrixY - 1);

    count += getStatusOfPosition(matrixX - 1, matrixY);
    count += getStatusOfPosition(matrixX + 1, matrixY);

    count += getStatusOfPosition(matrixX - 1, matrixY + 1);
    count += getStatusOfPosition(matrixX, matrixY + 1);
    count += getStatusOfPosition(matrixX + 1, matrixY + 1);

    return count;
  }

  public void simulate() {
    int[][] newMatrix = new int[width][height];
    for (int matrixY = 0; matrixY < height; matrixY++) {
      for (int matrixX = 0; matrixX < width; matrixX++) {
        int aliveNeighbours = countAliveNeighbours(matrixX, matrixY);
        if (getStatusOfPosition(matrixX, matrixY) == 1) {
          if (aliveNeighbours < 2 || aliveNeighbours > 3) {
            newMatrix[matrixX][matrixY] = 0;
          } else {
            newMatrix[matrixX][matrixY] = 1;
          }
        } else {
          if (aliveNeighbours == 3) {
            newMatrix[matrixX][matrixY] = 1;
          }
        }
      }
    }
    matrix = newMatrix;
  }

  private int getStatusOfPosition(int matrixX, int matrixY) {
    if (matrixX < 0 || matrixX >= width || matrixY < 0 || matrixY >= height) {
      return 0;
    }

    return matrix[matrixX][matrixY];
  }
}