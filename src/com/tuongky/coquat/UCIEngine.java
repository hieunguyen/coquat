package com.tuongky.coquat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

public class UCIEngine extends BaseEngine {

  private static final String ENGINE_BINARY = "wine engines/cyclone/cycloneV6.2.exe";

  public UCIEngine() throws IOException {
    startEngine();
  }

  @Override
  protected void startEngine() throws IOException {
    process = Runtime.getRuntime().exec(ENGINE_BINARY);
    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    out = new PrintWriter(process.getOutputStream());
    out.println("uci");
    out.flush();
  }

  private String positionCommand(String fen, List<String> moves) {
    if (Strings.isNullOrEmpty(fen)) {
      return "position startpos moves " + Joiner.on(" ").join(moves);
    }
    return String.format("position fen %s moves %s", fen, Joiner.on(" ").join(moves));
  }

  @Override
  public String think(String fen, List<String> moves) {
    out.println("ucinewgame");
    out.println(positionCommand(fen, moves));
    out.println("go movetime 2000");
    out.flush();
    while (true) {
      try {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        if (line.startsWith("bestmove")) {
          return line.split(" ")[1];
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
