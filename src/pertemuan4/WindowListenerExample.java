package pertemuan4;

import javax.swing.*;
import java.awt.event.*;

public class WindowListenerExample {
	
	public static void main(String[] args) {
		//Membuat Frame
		JFrame frame = new JFrame("WindowListener Example");
		
		//Membuat label untuk menampilkan pesan
		JLabel label = new JLabel("Lakukan operasi pada jendela");
		label.setBounds(50, 50, 300, 30);
		
		//Menambah WindowwListener ke frame
		frame.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				label.setText("Window Opened");
			}
			
			public void windowClosing(WindowEvent e) {
				label.setText("Window Closing");
			}
			
			public void windowClosed(WindowEvent e) {
				System.out.println("Window Closed");
			}
			
			public void windowIconified(WindowEvent e) {
				label.setText("Window Minimized");
			}
			
			public void windowDeiconified(WindowEvent e) {
				label.setText("Window Restored");	
			}
			
			public void windowActivated(WindowEvent e) {
				label.setText("Window Actived");
			}

			public void windowDeactivated(WindowEvent e) {
				label.setText("Window Deactived");
			}
		});
		
		//Menambahkan Frame
		frame.add(label);
		
		//Setting frame
		frame.setSize(400, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}