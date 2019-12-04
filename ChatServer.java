import java.io.*;
import java.net.*;
import java.util.*;

class ChatServer implements Runnable{

  Socket connectionSocket;
  public static Vector clients = new Vector();
  public static Vector rooms = new Vector();

  public ChatServer(Socket s){

    if (rooms.contains("General")) {
    } else {
      rooms.add("General");
    }

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

        if (data1.contains("$create")) {
          String[] args = data1.split(" ");
          if (args.length < 4) {
            System.out.println("Not enough arguments");
          } else {
            rooms.add(args[4]);
            System.out.println(rooms);
            for (int i = 0; i < clients.size(); i++) {
              try {
                BufferedWriter bw = (BufferedWriter)clients.get(i);
                bw.write("New room created: " + args[4]);
                bw.write("\r\n");
                bw.flush();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        } else if (data1.contains("$list")) {
          for (int i = 0; i < clients.size(); i++) {
            try {
              BufferedWriter bw = (BufferedWriter)clients.get(i);
              bw.write("Available Rooms: " + rooms);
              bw.write("\r\n");
              bw.flush();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } else {
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
