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
		Image image = new ImageIcon("./img/아이콘.png").getImage();
		setIconImage(image);
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
	    mainpanel.setLayout(null);
	    ImageIcon alln=new ImageIcon(numberScreen.class.getResource(("/team/img/모든알림 종모양.png")));
	    ImageIcon impsn=new ImageIcon(numberScreen.class.getResource(("/team/img/중요단어 알림 종모양.png")));
	    ImageIcon imppn=new ImageIcon(numberScreen.class.getResource(("/team/img/중요인 종모양.png")));
	    ImageIcon allmuten=new ImageIcon(numberScreen.class.getResource(("/team/img/알림끄기 종모양.png")));
	    ImageIcon imp=new ImageIcon(numberScreen.class.getResource(("/team/img/중요인.png")));
	    ImageIcon notimp=new ImageIcon(numberScreen.class.getResource(("/team/img/중요인아님.png")));
	    JLabel imagelabel = new JLabel(alln);
	    JLabel imagelabel2 = new JLabel(imp);
	    JButton button1=new JButton("모든알림");
	    JButton button2=new JButton("중요인 알림");
	    JButton button3=new JButton("중요단어 알림");
	    JButton button4=new JButton("모든알림 꺼짐");
	    JButton button5=new JButton("중요인 설정");
	    JButton button6=new JButton("중요인 미설정");
	    JButton optionbutton=new JButton(new ImageIcon(numberScreen.class.getResource("/team/img/톱니바퀴.png")));
	    JButton chatbutton=new JButton(new ImageIcon(numberScreen.class.getResource("/team/img/말풍선.png")));
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
		 button1.addActionListener( new ActionListener(){//모든알림
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(alln);
	            	setAlam(0);
        
	            }
	            
	        });
		 button2.addActionListener( new ActionListener(){//중요인 알림
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(imppn);
	            	setAlam(1);
	            }
	            
	        });
		 button3.addActionListener( new ActionListener(){//중요단어 알림
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(impsn);
	            	setAlam(2);
	            }
	            
	        });
		 button4.addActionListener( new ActionListener(){//모든알림 꺼짐
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel.setIcon(allmuten);
	            	setAlam(3);
	            }
	            
	        });
		 button5.addActionListener( new ActionListener(){//중요인
	            
	            public void actionPerformed(ActionEvent e) {
	            	imagelabel2.setIcon(imp);
	            	setAlam(4);
	            }
	            
	        });
		 button6.addActionListener( new ActionListener(){//중요인 아님
	            
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

		 chatbutton.addActionListener( new ActionListener(){// 채팅창
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
				m.listenMessage(); //<- 패킷 받는 부분 빼고는 잘 작 동함 
	    	} catch (IOException ioException) {
				
			} catch (SQLException sqlException) {
				
			}
		}

	}
}
