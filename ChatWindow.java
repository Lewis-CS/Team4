import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatWindow implements Runnable {

    public JTextField textField;
    public JTextArea textArea;
    public JPanel sidePanel;
    public String currentRoom;
    BufferedWriter writer;
    BufferedReader reader;
    ArrayList<String> myRooms = new ArrayList<String>();

    public ChatWindow(String user) {
      currentRoom = "General";
      myRooms.add("General");

        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        JLabel rooms = new JLabel("Rooms:                     ");
        JLabel generalRoom = new JLabel("General");
        generalRoom.setForeground(Color.gray);
        sidePanel.add(rooms);
        sidePanel.add(new JLabel(" "));
        sidePanel.add(generalRoom);
        panel2.add(sidePanel, BorderLayout.WEST);

        textField = new JTextField();
        panel1.add(textField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        sendButton.setOpaque(true);
        sendButton.setForeground(Color.blue);
        JButton clearButton = new JButton("Clear");
        clearButton.setOpaque(true);
        clearButton.setForeground(Color.red);

        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);
        panel1.add(buttonPanel, BorderLayout.EAST);

        textArea = new JTextArea();
        textArea.setEditable(false);
        panel2.add(textArea, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.SOUTH);

        //Welcome message
        textArea.append("Welcome to the Chat, " + user + "!\n");
        textArea.append("\nInstructions: \n");
        textArea.append("\'$create [ROOM_NAME]\' to create a new room\n");
        textArea.append("\'$join [ROOM_NAME]\' to join a new room\n");
        textArea.append("\'$list\' to list existing room names\n");
        textArea.append("\nHave fun!\n\n");

        frame.setContentPane(panel2);

        try {
          Socket socketClient = new Socket("localhost", 4444);
          writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
          reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

          writer.write(user + " has joined the chat.");
          writer.write("\r\n");
          writer.flush();

        } catch (Exception e) {
          e.printStackTrace();
        }

        Action sendAction = new AbstractAction() {
          public void actionPerformed(ActionEvent ev) {
            String message = textField.getText();

            if (message.contains("$join")) {
              String[] x = message.split(" ");
              if (x.length >= 2 && currentRoom != x[1]) {
                if (myRooms.contains(x[1])) {
                  currentRoom = x[1];
                  textArea.append("You have joined " + currentRoom + "\n");
                } else {
                  textArea.append("That room does not exist\n");
                }
              } else {
                textArea.append("Could not join room\n");
              }
            } else {
              message = currentRoom + " " + user + " > " + textField.getText();
              try {
                writer.write(message);
                writer.write("\r\n");
                writer.flush();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            textField.setText("");
          }
        };
        textField.addActionListener(sendAction);
        sendButton.addActionListener(sendAction);

        clearButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            textField.setText("");
          }
        });

        frame.setVisible(true);
    }

    public void run() {
      try {
        String serverMsg = "";
        while ((serverMsg = reader.readLine()) != null) {
          if (serverMsg.contains("has joined the chat")) {
            textArea.append(serverMsg + "\n");
          } else if (serverMsg.contains("Available Rooms:")) {
            textArea.append(serverMsg + "\n");
          } else if (serverMsg.contains("New room created:")) {
            textArea.append(serverMsg + "\n");
            String[] x = serverMsg.split(": ");
            myRooms.add(x[1]);
            JLabel newRoom = new JLabel(x[1]);
            newRoom.setForeground(Color.gray);
            sidePanel.add(newRoom);
            sidePanel.validate();
            sidePanel.repaint();
            System.out.println(myRooms);
          } else {
            String[] splitMessage = serverMsg.split(" ", 2);
            if (splitMessage[0].equals(currentRoom)) {
              textArea.append(splitMessage[1] + "\n");
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
