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
import javax.swing.JTextField;
import Login.UserDBManager;

class addWord extends JFrame{
	String[] crucialWord;
	int wordIndex = 0;
	UserDBManager udm;
	public addWord(UserDBManager udm) {
		this.udm = udm;
		crucialWord = new String[6];
		setTitle("add word");
		Image image = new ImageIcon("./img/������.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel addwordpanel = new JPanel();
		addwordpanel.setLayout(null);
		
		JLabel uptext1=new JLabel("�߿�ܾ� �߰�");
		addwordpanel.add(uptext1);
		uptext1.setBounds(160,20,300,100);
		
		JLabel downtext1=new JLabel("�߿�ܾ� �Է�");
		addwordpanel.add(downtext1);
		downtext1.setBounds(45,100,300,100);
		
		JTextField name = new JTextField(10);
		addwordpanel.add(name);
		name.setBounds(45,160,300,100);

		JButton addwordbutton=new JButton("Ȯ��");
		addwordpanel.add(addwordbutton);
		addwordbutton.setBounds(150,300,100,50);
		
		add(addwordpanel);
		setVisible(true);
		
		addwordbutton.addActionListener( new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	
				if(udm.addWord(name.getText())==true) {
					JOptionPane.showMessageDialog(null, "�߿�ܾ �߰��Ǿ����ϴ�.");
				}
				else {
				JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�. �߿�ܾ 6�̻����� Ȯ���ϼ���");
				}
			}
		});
	}
	
	
}
