package Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.swing.*;

import focuspro.numberScreen;

public class UserDBLoginer extends JFrame{
	JButton ok;
	JTextField userid;
	JTextField password;
	ResultSet rs;
	
	UserDBManager udm;
	numberScreen nS;
	
	public UserDBLoginer(UserDBManager udm) throws SQLException {
		super("UserDBLoginer");
		
		this.udm = udm;
		rs = udm.selectUser();
		rs = udm.getRs();
		
		setTitle("login");
		Image image = new ImageIcon("../team/img/아이콘.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel loginpanel = new JPanel();
		loginpanel.setLayout(null);
		ok=new JButton("확인");
		userid=new JTextField(10);
		password=new JTextField(10);
		JButton addmember=new JButton("회원가입");
		JLabel idtext = new JLabel("아이디: ");
		JLabel passwordtext=new JLabel("비밀번호: ");
		loginpanel.add(userid);
		loginpanel.add(password);
		loginpanel.add(ok);
		loginpanel.add(idtext);
		loginpanel.add(passwordtext);
		loginpanel.add(addmember);
		userid.setBounds(65,200,215,50);
		password.setBounds(65,300,215,50);
		idtext.setBounds(10,200,250,50);
		passwordtext.setBounds(10,300,250,50);
		ok.setBounds(300,240,70,70);
		addmember.setBounds(70,450,250,50);
		add(loginpanel);
		setVisible(true);
		
		ok.addActionListener( new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	String uid = userid.getText();
					String pwd = password.getText();
						if(udm.userLogin(uid, pwd)==true) {
						System.out.println("로그인에 성공하였습니다.");
						numberScreen nS = new numberScreen(uid,udm);
						dispose();	
						
						
					}
					else {
						System.out.println("로그인에 실패하였습니다.");
					}
	            }
	            
	        });
		 addmember.addActionListener( new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new addUser(udm);
	            }
	            
	        });
	}
}

