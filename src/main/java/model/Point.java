package model;

public class Point {
  private int positionX;
  private int positionY;
  private boolean isAlive = false;

  public Point(int positionX, int positionY, boolean isAlive) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.isAlive = isAlive;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  @Override
  public String toString() {
    return "Point{" +
        "positionX=" + positionX +
        ", positionY=" + positionY +
        ", isAlive=" + isAlive +
        '}';
  }
}
