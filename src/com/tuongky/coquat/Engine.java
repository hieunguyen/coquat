package com.tuongky.coquat;

import java.util.List;

public interface Engine {

  String think(String fen, List<String> moves);

  boolean restart();
}
