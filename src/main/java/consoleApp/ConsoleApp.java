package consoleApp;

import controllers.BoardController;
import controllers.PointsController;
import java.util.List;
import java.util.Scanner;
import model.Board;
import model.Point;

public class ConsoleApp {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter width of the board: ");
    int width = scanner.nextInt();
    System.out.print("Enter height of the board: ");
    int height = scanner.nextInt();
    System.out.print("Enter count of alive points in the beginning: ");
    int countOfAlivePoints = scanner.nextInt();
    System.out.print("Enter count of life simulations(0 to simulate till the end): ");
    int countOfLifeSimulations = scanner.nextInt();
    Board board = new Board(width, height);
    BoardController boardController = new BoardController(board);
    PointsController pointsController = new PointsController(board);
    List<Point> points = pointsController.setRandomList(countOfAlivePoints);
    board.setPoints(points);
    boardController.printBoard();
    if (countOfLifeSimulations == 0) {
        while (boardController.getCountOfAlivePointsInBoard() > 0){
          boardController.simulate();
          boardController.printBoard();
          Thread.sleep(1000);
        }
    } else {
      for (int iteration = 0; iteration < countOfLifeSimulations; iteration++) {
        boardController.simulate();
        boardController.printBoard();
        Thread.sleep(1000);
      }
    }
  }
}
