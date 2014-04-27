package com.tuongky.coquat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class XBoardEngine extends BaseEngine {

  private static final String ENGINE_BINARY = "engines/haqikid/haqikid";
//  private static final String ENGINE_BINARY = "wine engines/HaQiKiD.exe";

  public XBoardEngine() throws IOException {
    startEngine();
  }

  @Override
  protected void startEngine() throws IOException {
    process = Runtime.getRuntime().exec(ENGINE_BINARY);
    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    out = new PrintWriter(process.getOutputStream());
    out.println("st 3");
    out.println("variant xiangqi");
    out.flush();
  }

  @Override
  public String think(String fen, List<String> moves) {
    for (String command : XBoard.fenToEditCommands(fen)) {
      out.println(command);
    }
    out.println("force");
    for (String move : moves) {
      out.println(move);
    }
    out.println("go");
    out.flush();
    while (true) {
      try {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        if (line.startsWith("move")) {
          return line.split(" ")[1];
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
