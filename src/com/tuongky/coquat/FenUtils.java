package com.tuongky.coquat;

import com.google.common.base.Preconditions;

public final class FenUtils {

  private FenUtils() {}

  public static Position fenToPosition(String fen) {
    Preconditions.checkNotNull(fen, "FEN cannot be null.");
    int[][] board = new int[Constants.ROWS][Constants.COLS];
    String[] s = fen.split(" ");
    if (s.length < 2) {
      return null;
    }
    String[] rows = s[0].split("/");
    if (rows.length < board.length) {
      return null;
    }
    for (int i = 0; i < board.length; i++) {
      int square = 0;
      for (int j = 0; j < rows[i].length(); j++) {
        char c = rows[i].charAt(j);
        if (Character.isDigit(c)) {
          square += c - '0';
        } else {
          int pieceType = Constants.RED_PIECES.indexOf(Character.toUpperCase(c));
          if (pieceType < 0) {
            return null;
          }
          board[i][square] = pieceType + 1;
          if (Character.isLowerCase(c)) {
            board[i][square] *= -1;
          }
          square++;
        }
      }
    }
    int turn = s[1].toLowerCase().equals("w") ? Constants.RED : Constants.BLACK;
    int halfMoveClock = -1;
    int fullMove = -1;
    if (s.length >= 5) {
      try {
        halfMoveClock = Integer.parseInt(s[4]);
      } catch (Exception e) {
        halfMoveClock = -1;
      }
    }
    if (s.length >= 6) {
      try {
        fullMove = Integer.parseInt(s[5]);
      } catch (Exception e) {
        fullMove = -1;
      }
    }
    return new Position(board, turn, halfMoveClock, fullMove);
  }
}
