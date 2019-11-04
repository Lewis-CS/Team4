import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Team 4

public class Message implements Runnable {
    private String username;
    private Socket socket;
    private BufferedReader serverIn;
    private BufferedReader userIn;
    private PrintWriter out;

    public Message(Socket socket, String name) {
        this.socket = socket;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));

            while(!socket.isClosed()) {
                if(serverIn.ready()) {
                    String message = serverIn.readLine();
                    if(message != null) {
                        System.out.println(message);
                    }
                }
                if(userIn.ready()) {
                    out.println(username + " > " + userIn.readLine());
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}