package com.tuongky.coquat;

public class Position {

  private final int[][] board;
  private final int turn;
  private final int halfMoveClock;
  private final int fullMove;

  public Position(int[][] board, int turn, int halfMoveClock, int fullMove) {
    this.board = new int[board.length][];
    for (int i = 0; i < board.length; i++) {
      this.board[i] = board[i].clone();
    }
    this.turn = turn;
    this.halfMoveClock = halfMoveClock;
    this.fullMove = fullMove;
  }

  public int[][] getBoard() {
    return board;
  }

  public int getTurn() {
    return turn;
  }

  public int getHalfMoveClock() {
    return halfMoveClock;
  }

  public int getFullMove() {
    return fullMove;
  }
}
