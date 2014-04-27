package com.tuongky.coquat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CoquatServer {

  private static final int PORT = 1234;
  private static final int ENGINES = 2;

  public static void main(String[] args) throws Exception {
    Server server = new Server(PORT);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

    BlockingQueue<Engine> queue = new LinkedBlockingQueue<>();
    for (int i = 0; i < ENGINES; i++) {
//      queue.add(new UCIEngine());
      queue.add(new XBoardEngine());
    }
    context.setAttribute("engineQueue", queue);

    context.setContextPath("/");
    context.addServlet(new ServletHolder(new GoServlet()), "/go");
    server.setHandler(context);
    server.start();
    server.join();
  }
}
