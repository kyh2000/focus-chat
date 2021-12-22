package focuspro;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Login.UserDBManager;

class deleteWord extends JFrame{
	String[] crucialWord;
	int wordIndex = 0;
	UserDBManager udm;
	public deleteWord(UserDBManager udm) {
		this.udm = udm;
		crucialWord = new String[6];
		setTitle("add word");
		Image image = new ImageIcon("./img/아이콘.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel addwordpanel = new JPanel();
		addwordpanel.setLayout(null);
		
		JLabel uptext1=new JLabel("중요단어 삭제");
		addwordpanel.add(uptext1);
		uptext1.setBounds(160,20,300,100);
		
		JLabel downtext1=new JLabel("중요단어 목록");
		addwordpanel.add(downtext1);
		downtext1.setBounds(45,100,300,100);
		
		JTextArea list = new JTextArea();
		addwordpanel.add(list);
		list.setBounds(45,160,300,100);
		list.append(udm.retCruWord());
		
		JTextField name = new JTextField(10);
		addwordpanel.add(name);
		name.setBounds(45,280,300,100);

		JButton addwordbutton=new JButton("확인");
		addwordpanel.add(addwordbutton);
		addwordbutton.setBounds(150,400,100,50);
		
		add(addwordpanel);
		setVisible(true);
		
		addwordbutton.addActionListener( new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	
				if(udm.delWord(name.getText())==true) {
					JOptionPane.showMessageDialog(null, "중요단어가 삭제되었습니다.");
				}
				else {
				JOptionPane.showMessageDialog(null, "실패하였습니다.");
				}
			}
		});
	}
	
	
}

