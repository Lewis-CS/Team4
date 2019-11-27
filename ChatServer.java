import java.io.*;
import java.net.*;
import java.util.*;

class ChatServer implements Runnable{

  Socket connectionSocket;
  public static Vector clients = new Vector();

  public ChatServer(Socket s){
    try{
    	System.out.println("New client connected");
    	connectionSocket=s;
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void run(){
    try{

    	BufferedReader reader =
    		new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    	BufferedWriter writer=
    		new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
      clients.add(writer);

      while(true) {
        String data1 = reader.readLine().trim();
        System.out.println("Received New Message: " + data1);

        for (int i = 0; i < clients.size(); i++) {
          try {
            BufferedWriter bw = (BufferedWriter)clients.get(i);
            bw.write(data1);
            bw.write("\r\n");
            bw.flush();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String argv[]) throws Exception {
    System.out.println("Threaded Chat Server is Running  " );
    ServerSocket mysocket = new ServerSocket(4444);
    while(true){
      Socket sock = mysocket.accept();
      ChatServer chatServer = new ChatServer(sock);
      Thread serverThread = new Thread(chatServer);
      serverThread.start();
    }
  }
}
