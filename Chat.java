import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Chat {
  public static void main(String [] args){

    JFrame frame = new JFrame();
    String username = JOptionPane.showInputDialog(frame, "Enter username: ");

    if (username != "") {
      try{
        ChatWindow window = new ChatWindow(username);
        Thread t1 = new Thread(window);
        t1.start();
  	  } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}
