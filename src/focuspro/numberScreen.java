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

public class numberScreen extends JFrame{
	JButton ok;
	JTextField id;
	JTextField password;
	String userId;
	UserDBManager udm;
	Messenger m;
	
	public int alam;
	public numberScreen(String userId, UserDBManager udm) {
		this.udm = udm;
		this.userId = userId;
		alam = 0;
		
		JFrame numberScreen = new JFrame();
		setTitle("main");
		Image image = new ImageIcon("./img/������.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
	    mainpanel.setLayout(null);
	    ImageIcon alln=new ImageIcon(numberScreen.class.getResource(("/team/img/���˸� �����.png")));
	    ImageIcon impsn=new ImageIcon(numberScreen.class.getResource(("/team/img/�߿�ܾ� �˸� �����.png")));
	    ImageIcon imppn=new ImageIcon(numberScreen.class.getResource(("/team/img/�߿��� �����.png")));
	    ImageIcon allmuten=new ImageIcon(numberScreen.class.getResource(("/team/img/�˸����� �����.png")));
	    ImageIcon imp=new ImageIcon(numberScreen.class.getResource(("/team/img/�߿���.png")));
	    ImageIcon notimp=new ImageIcon(numberScreen.class.getResource(("/team/img/�߿��ξƴ�.png")));
	    JLabel imagelabel = new JLabel(alln);
	    JLabel imagelabel2 = new JLabel(imp);
	    JButton button1=new JButton("���˸�");
	    JButton button2=new JButton("�߿��� �˸�");
	    JButton button3=new JButton("�߿�ܾ� �˸�");
	    JButton button4=new JButton("���˸� ����");
	    JButton button5=new JButton("�߿��� ����");
	    JButton button6=new JButton("�߿��� �̼���");
	    JButton optionbutton=new JButton(new ImageIcon(numberScreen.class.getResource("/team/img/��Ϲ���.png")));
	    JButton chatbutton=new JButton(new ImageIcon(numberScreen.class.getResource("/team/img/��ǳ��.png")));
		mainpanel.add(button1);
		mainpanel.add(button2);
		mainpanel.add(button3);
		mainpanel.add(button4);
		mainpanel.add(button5);
		mainpanel.add(button6);
		mainpanel.add(optionbutton);
		mainpanel.add(chatbutton);
		mainpanel.add(imagelabel);
		mainpanel.add(imagelabel2);
		imagelabel.setBounds(60,10,60,60);
		optionbutton.setBounds(10,10,40,40);
		chatbutton.setBounds(300,10,60,60);
		button1.setBounds(150,10,90,20);
		button2.setBounds(150,40,100,20);
		button3.setBounds(150,70,120,20);
		button4.setBounds(150,100,120,20);
		button5.setBounds(150,200,120,20);
		button6.setBounds(150,250,120,20);
		imagelabel2.setBounds(20,170,120,120);
		add(mainpanel);
		setVisible(true);
		 button1.addActionListener( new ActionListener(){//���˸�
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(alln);
	            	setAlam(0);
        
	            }
	            
	        });
		 button2.addActionListener( new ActionListener(){//�߿��� �˸�
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(imppn);
	            	setAlam(1);
	            }
	            
	        });
		 button3.addActionListener( new ActionListener(){//�߿�ܾ� �˸�
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(impsn);
	            	setAlam(2);
	            }
	            
	        });
		 button4.addActionListener( new ActionListener(){//���˸� ����
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(allmuten);
	            	setAlam(3);
	            }
	            
	        });
		 button5.addActionListener( new ActionListener(){//�߿���
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel2.setIcon(imp);
	            	setAlam(4);
	            }
	            
	        });
		 button6.addActionListener( new ActionListener(){//�߿��� �ƴ�
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel2.setIcon(notimp);
	            	setAlam(5);
	            }
	            
	        });
		 optionbutton.addActionListener( new ActionListener(){
	            
	            public void actionPerformed(ActionEvent e) {
	            new option(udm);
  
	            }
	            
	        });

		 chatbutton.addActionListener( new ActionListener(){// ä��â
	            public void actionPerformed(ActionEvent e) {
	            	Thread t = new MyThread();
	            	t.start();
	            	
	            }		
	        });

	}
	public void setAlam(int x) {
		alam = x;
	}
	class MyThread extends Thread {
		public void run() {
			try {
				m = new Messenger("My Messenger", userId, udm, alam);
				m.listenMessage(); //<- ��Ŷ �޴� �κ� ����� �� �� ���� 
	    	} catch (IOException ioException) {
				
			} catch (SQLException sqlException) {
				
			}
		}

	}
}
