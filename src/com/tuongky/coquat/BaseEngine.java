package com.tuongky.coquat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public abstract class BaseEngine implements Engine {

  protected BufferedReader reader;
  protected PrintWriter out;
  protected Process process;

  protected abstract void startEngine() throws IOException;

  @Override
  public boolean restart() {
    try {
      cleanUp();
      startEngine();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private void cleanUp() throws IOException {
    out.close();
    reader.close();
    process.destroy();
  }
}
