package focuspro;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Login.UserDBManager;

public class option extends JFrame{
	UserDBManager udm;
	public option(UserDBManager udm) {
		this.udm=udm;
		setTitle("option");
		Image image = new ImageIcon("./img/아이콘.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel optionpanel = new JPanel();
		optionpanel.setLayout(null);
		JButton addword=new JButton("중요단어 추가");
		JButton deleteword=new JButton("중요단어 삭제");
		optionpanel.add(addword);
		optionpanel.add(deleteword);
		addword.setBounds(45,150,300,100);
		deleteword.setBounds(45,300,300,100);
		add(optionpanel);
		setVisible(true);
		 addword.addActionListener( new ActionListener(){
	            
	            public void actionPerformed(ActionEvent e) {
	            	new addWord(udm);
	            }
	        });
		 deleteword.addActionListener( new ActionListener(){
	            
	            public void actionPerformed(ActionEvent e) {
	            	new deleteWord(udm);
	            } 
	        });
	}
}