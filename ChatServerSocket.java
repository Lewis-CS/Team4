import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerSocket {

  public ChatServerSocket() throws IOException {
    System.out.println("Success");
    final int portNumber = 80;
    System.out.println("Creating server socket on port " + portNumber);
    ServerSocket serverSocket = new ServerSocket(portNumber);
    while (true) {
      System.out.println("In loop");
      Socket socket = serverSocket.accept();
      OutputStream os = socket.getOutputStream();

      socket.close();
    }
  }

}
