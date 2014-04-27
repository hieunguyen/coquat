package com.tuongky.coquat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GoServlet extends HttpServlet {

  private List<String> toMoves(String moveStr) {
    List<String> moves = new ArrayList<>();
    if (moveStr == null) {
      return moves;
    }
    for (String move : moveStr.split(" ")) {
      moves.add(move);
    }
    return moves;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String fen = request.getParameter("fen");
    if (fen == null) {
      fen = Constants.START_FEN;
    }
    List<String> moves = toMoves(request.getParameter("moves"));

    @SuppressWarnings("unchecked")
    BlockingQueue<Engine> engineQueue =
        (BlockingQueue<Engine>) getServletContext().getAttribute("engineQueue");
    Engine engine = null;
    String move = "";
    try {
      engine = engineQueue.take();
      move = engine.think(fen, moves);
      if (move == null) { // try to restart the engine.
        engine.restart();
        move = engine.think(fen, moves);
        if (move == null) { // still fail, give up.
          response.setHeader("Access-Control-Allow-Origin", "*");
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      try {
        if (engine != null) {
          engineQueue.put(engine);
        }
      } catch (InterruptedException e) {} // Nothing we can do here.
    }
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println(move);
  }
}
