import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.IOException;

public class Chat {
	public static void main(String[] args) {
		Window window = new Window();
		try {
			ChatServerSocket chatServerSocket = new ChatServerSocket();
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
