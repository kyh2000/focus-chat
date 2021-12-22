package Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.sql.*;

class addUser extends JFrame{
	JButton memok;
	JTextField newUserPwd;
	JTextField newUserName;
	
	UserDBManager udm;
	ResultSet rs;
	
	
	public addUser(UserDBManager udm) {
		this.udm = udm;
		setTitle("login");
		Image image = new ImageIcon("../team/img/������.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel newUserPanel = new JPanel();
		newUserPanel.setLayout(null);
		
		memok=new JButton("Ȯ��");
		newUserPwd=new JTextField(10);
		newUserName=new JTextField(10);
		
		JLabel pwdText = new JLabel("��й�ȣ: ");
		JLabel nameText = new JLabel(" �̸�: ");
		
		newUserPanel.add(newUserPwd);
		newUserPanel.add(newUserName);
		newUserPanel.add(memok);		
		newUserPanel.add(pwdText);
		newUserPanel.add(nameText);
		
		newUserName.setBounds(65,150,215,50);
		nameText.setBounds(10,150,250,50);
		newUserPwd.setBounds(65,250,215,50);
		pwdText.setBounds(10,250,250,50);
		memok.setBounds(300,200,70,70);
		add(newUserPanel);
		setVisible(true);
		
		memok.addActionListener( new ActionListener(){  
			 public void actionPerformed(ActionEvent e) {	
				 String addUserName = newUserName.getText();
				 String addUserPwd = newUserPwd.getText();
				 if (udm.addUser(addUserName, addUserPwd)==true) {
					JOptionPane.showMessageDialog(null, "id: " + udm.returnLastId() + "ȸ�����ԿϷ�!");
				 	dispose();
				 }
				 else 
					 JOptionPane.showMessageDialog(null, "�ٽ� �õ����ּ���");
				}
	        });
	}

}
