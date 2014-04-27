package com.tuongky.coquat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class XBoardController {

  private static final String ENGINE_BINARY = "engines/haqikid/haqikid";

  public XBoardController() throws IOException {
    Process process = Runtime.getRuntime().exec(ENGINE_BINARY);
    BufferedReader br = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
    PrintWriter out = new PrintWriter(process.getOutputStream());
    while (true) {
      if (!br.ready()) {
        out.println("go");
        out.flush();
      }
      int x = br.read();
      if (x == -1) {
        break;
      }
      System.out.print((char) x);
    }
    System.out.println("Done.");
  }

  public static void main(String[] args) throws IOException {
    new XBoardController();
  }
}
