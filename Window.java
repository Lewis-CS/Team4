import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

  public Window() {
    JFrame frame = new JFrame("Chat");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize.width, screenSize.height);

    JMenuBar menuBar = new JMenuBar();
    JMenu menu1 = new JMenu("File");
    JMenu menu2 = new JMenu("Help");
    menuBar.add(menu1);
    menuBar.add(menu2);

    JMenuItem menuItem1 = new JMenuItem("Open");
    JMenuItem menuItem2 = new JMenuItem("Save as");
    menu1.add(menuItem1);
    menu1.add(menuItem2);

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Enter Text: ");
    JTextField textField = new JTextField(20);
    JButton send = new JButton("Send");
    JButton reset = new JButton("Clear");
    panel.add(label);
    panel.add(textField);
    panel.add(send);
    panel.add(reset);

    JTextArea textArea = new JTextArea();

    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    frame.getContentPane().add(BorderLayout.NORTH, menuBar);
    frame.getContentPane().add(BorderLayout.CENTER, textArea);
    frame.setVisible(true);
  }
}
