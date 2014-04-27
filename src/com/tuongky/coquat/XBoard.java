package com.tuongky.coquat;

import java.util.ArrayList;
import java.util.List;

public final class XBoard {

  private XBoard() {}

  public static List<String> fenToEditCommands(String fen) {
    Position pos = FenUtils.fenToPosition(fen);
    List<String> commands = new ArrayList<>();
    if (pos == null) {
      return commands;
    }
    commands.add("new");
    if (pos.getTurn() == Constants.BLACK) {
      commands.add("force");
      commands.add("c3c4");
    }
    commands.add("edit");
    // clears the board.
    commands.add("#");
    int[][] board = pos.getBoard();
    for (int i = 0; i < Constants.ROWS; i++) {
      for (int j = 0; j < Constants.COLS; j++) {
        if (board[i][j] > 0) { // a red piece.
          char col = (char) ('a' + j);
          char row = (char) ('0' + Constants.ROWS - i - 1);
          commands.add("" + Constants.RED_PIECES.charAt(board[i][j] - 1) + col + row);
        }
      }
    }

    // Change the color.
    commands.add("c");

    for (int i = 0; i < Constants.ROWS; i++) {
      for (int j = 0; j < Constants.COLS; j++) {
        if (board[i][j] < 0) { // a black piece.
          char col = (char) ('a' + j);
          char row = (char) ('0' + Constants.ROWS - i - 1);
          commands.add("" + Constants.RED_PIECES.charAt(-board[i][j] - 1) + col + row);
        }
      }
    }

    commands.add(".");
    return commands;
  }

  public static void main(String[] args) {
    String fen = "rheakaehr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RHEAKAEHR w - - - 1";
    List<String> commands = fenToEditCommands(fen);
    for (String command : commands) {
      System.out.println(command);
    }
  }
}
