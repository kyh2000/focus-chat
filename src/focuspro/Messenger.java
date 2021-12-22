package focuspro;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Login.UserDBManager;

public class Messenger {
	JTextField textField;
	JTextArea textArea;
	DatagramSocket socket;
	DatagramPacket packet;
	InetAddress address = null;
	int myPort = 5005;
	int yourPort = 6000;
	int alam;
	
	String messengerTitle;
	String userName;
	UserDBManager udm;
	MyFrame mm;



	public Messenger(String messengerTtitle, String userName, UserDBManager udm, int alam) throws IOException, SQLException {
		this.messengerTitle = messengerTtitle;
		this.userName = userName;
		this.udm = udm;
		this.alam = alam;
		address = InetAddress.getByName("localhost");
		mm = new MyFrame(messengerTitle);
		socket = new DatagramSocket(myPort);
		
	}

	// ��Ŷ�� �޾Ƽ� �ؽ�Ʈ ������ ǥ���Ѵ�.
	
	public void listenMessage() {
		String yourMsg, s=null;
		while (true) {
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet); //=== ��Ŷ�� �޴´�.
				yourMsg = new String(buf);
				s = yourMsg.trim();	// remove white spaces
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			if(s != null)	{
				textArea.append(s + "\n");
				udm.saveLog(s);
			if(udm.checkMsg(s)==true && (alam ==0 || alam == 2)) {
				Toolkit.getDefaultToolkit().beep();
			}
			}
		}
	}

	// ���� Ŭ���� ����
	class MyFrame extends JFrame implements ActionListener {
		public MyFrame(String title) {
			super(title);
			Image image = new ImageIcon("./img/������.png").getImage();
			setIconImage(image);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			textField = new JTextField(30);
			textField.addActionListener(this);
			textArea = new JTextArea(10, 30);
			textArea.setEditable(true);
			add(textField, BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER);
			pack();
			setVisible(true);
			textArea.append(udm.showMsgLog());
		}
		// �޽����� ������.
		public void actionPerformed(ActionEvent evt) {	
			String myMsg = "[" + userName + "] " + textField.getText();
			textArea.append(myMsg + "\n");
			textField.setText("");
			
			DatagramPacket packet;
			byte[] buffer = myMsg.getBytes();

			packet = new DatagramPacket(buffer, buffer.length, address,	yourPort);
			try {
				socket.send(packet); //=== ��Ŷ�� ������.
			} catch (IOException e) {
				e.printStackTrace();
			}
			udm.saveLog(myMsg); 
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

}
