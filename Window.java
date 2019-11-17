import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

  public String username;

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
    JLabel label = new JLabel("Enter Username: ");
    JTextField textField = new JTextField(20);
    JButton send = new JButton("Send");
    JButton clear = new JButton("Clear");
    panel.add(label);
    panel.add(textField);
    panel.add(send);
    panel.add(clear);

    JPanel sidePanel = new JPanel();
    JLabel rooms = new JLabel("Rooms:                     ");
    sidePanel.add(rooms);

    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);

    ActionListener actions = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Send")) {
          if (username == null) {
            username = textField.getText();
            textArea.append("Hello, " + username + "!\n");
            textField.setText("");
            label.setText("Enter Text: ");
          } else {
            textArea.append(username + " > " + textField.getText() + "\n");
            textField.setText("");
          }
        } else if (action.equals("Clear")) {
          textField.setText("");
        }
      }
    };

    send.addActionListener(actions);
    send.setActionCommand("Send");

    clear.addActionListener(actions);
    clear.setActionCommand("Clear");

    frame.getContentPane().add(BorderLayout.WEST, sidePanel);
    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    frame.getContentPane().add(BorderLayout.NORTH, menuBar);
    frame.getContentPane().add(BorderLayout.CENTER, textArea);
    frame.setVisible(true);
  }
}
