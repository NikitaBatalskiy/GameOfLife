import model.Board;

public class ConsoleApp {
  public static void main(String[] args) {
//    Board board = new Board(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    Board board = new Board(10, 15);

    board.setAlivePosition(2,3);
    board.setAlivePosition(3,3);
    board.setAlivePosition(4,3);
    board.setAlivePosition(1,13);
    board.setAlivePosition(1,12);
    board.setAlivePosition(2,11);

//    board.printBoard();
    System.out.println(board.toString());

//    board.simulate();
//    board.printBoard();
//
//    board.simulate();
//    board.printBoard();
//
//    board.simulate();
//    board.printBoard();
  }
}
