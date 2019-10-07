import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window {
  public static void main(String[] args) {
    int width = 500;
    int height = 500;

    JFrame frame = new JFrame("Chat");
    frame.setSize(width, height);
    frame.setLocation(0,0);

    frame.setVisible(true);
  }
}
