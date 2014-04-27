package com.tuongky.coquat;

public final class Constants {

  private Constants() {
  }

  public static final int ROWS = 10;
  public static final int COLS = 9;

  public static final int EMPTY = 0;
  public static final int RED = 1;
  public static final int BLACK = 2;

  public static final int K = 1; // King
  public static final int A = 2; // Assistant
  public static final int E = 3; // Elephant
  public static final int R = 4; // Rook
  public static final int C = 5; // Cannon
  public static final int H = 6; // Horse
  public static final int P = 7; // Pawn

  public static final String RED_PIECES = "KAERCHP";

  public static final String START_FEN = "rheakaehr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RHEAKAEHR w - - - 1";
}
