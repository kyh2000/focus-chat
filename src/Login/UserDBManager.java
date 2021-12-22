package Login;
import java.sql.*;
import java.lang.*;

public class UserDBManager {
	String id;
	String password;
	String dbName;
	int lastId;
	
	Connection con = null;
	ResultSet rs;
	
	public UserDBManager(String id, String password, String dbName) throws SQLException {
		this.id = id;
		this.password = password;
		this.dbName = dbName;
	}

	public ResultSet getRs() {	return rs;	}
	
	public Connection makeConnection()
	{
		String url = "jdbc:mariadb://localhost:3308/" + dbName;
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, id, password);
			System.out.println(dbName + "�����ͺ��̽� ���� ����");
		} catch(ClassNotFoundException e){
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch(SQLException e){
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
		return con;
	}
	
	public ResultSet selectUser() {
		try {
			con = makeConnection();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user_table");
		} catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return rs;
	}
	// ȸ�� �α���, ���̵�� ��й�ȣ�� �Է¹޾� ����:true, ����:false�� ��ȯ
	public boolean userLogin(String uid, String pwd) {
		try{
			Statement stmt = con.createStatement();
			String s = "SELECT * FROM user_table";
			int ok = 0;
			System.out.println(s);
			ResultSet i = stmt.executeQuery(s);
			
			while (i.next()) {
				
				if((uid.equals(i.getString("uid"))) && (pwd.equals(i.getString("pwd"))))
					ok = 1;
			}
			
			if(ok==1) {
				System.out.println("�α��� ����");
				return true;
			}
			else {
				System.out.println("�α��� ����");
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			return false;
		}
	}
	
	// ȸ�� �߰�, �̸��� ��й�ȣ�� �Է¹޾� ����:true, ����:false / ���̵� ȸ������ �˷���
	public boolean addUser(String addUserName, String addUserPwd) {
		try{
			Statement stmt = con.createStatement();
			String s = "INSERT INTO user_table (uid, pwd) VALUES ";
			s += "('" + addUserName + "','"+ addUserPwd + "')";
			System.out.println(s);
			int i = stmt.executeUpdate(s);
			if(i==1) {
				System.out.println("���ڵ� �߰� ����");
				return true;
			}
			else {
				System.out.println("���ڵ� �߰� ����");
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			return false;
		}
	}	

	// �������� �߰��� ������ id�� ��ȯ
	public int returnLastId() {
		try{
			Statement returnId = con.createStatement();
			String maxIdStmt = "SELECT MAX(id) FROM user_table ";
			System.out.println(maxIdStmt);
			ResultSet maxId = returnId.executeQuery(maxIdStmt);
			maxId.next();
			lastId = maxId.getInt("");
		
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			
		}
		return this.lastId;
	}
	//�α׸� ����
	public void saveLog(String log) {
		Connection con = makeConnection();
		try{
			Statement stmt = con.createStatement();
			String s = "INSERT INTO msg_log (msg) VALUES ";
			s += "('" + log + "')";
			System.out.println(s);
			int i = stmt.executeUpdate(s);
			System.out.println(i);
			if(i==0)
				System.out.println("���ڵ� �߰� ����");
			else
				System.out.println("���ڵ� �߰� ����");
			con.close();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public void cruWordCount() {
		try{
			Statement countStmt = con.createStatement();
			String countIdStmt = "select count(*) from word_table";
			System.out.println(countIdStmt);
			ResultSet maxId = countStmt.executeQuery(countIdStmt);
			maxId.next();
			lastId = maxId.getInt("");
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	// �߿� �ܾ� �߰�
	public boolean addWord(String crucialWord) {
		cruWordCount();
		try {	
			if(lastId <= 6) {
				Statement addWordStmt = con.createStatement();
				String s = "INSERT INTO word_table (wordName) VALUES ";
				s += "('" + crucialWord + "')";
				System.out.println(s);
				int i = addWordStmt.executeUpdate(s);

				if(i==1) {
					System.out.println("���ڵ� �߰� ����");
					return true;
				}
				else {
					System.out.println("���ڵ� �߰� ����");
					return false;
				}
			}
			else 
				return false;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	
	}
	//�߿� �ܾ String���� ��ȯ
	public String retCruWord() {
		String crucial = "";
		try{
			Statement wordStmt = con.createStatement();
			String s= "SELECT wordName FROM word_table ";
			System.out.println(s);
			ResultSet cruWord = wordStmt.executeQuery(s);
			while(cruWord.next()) {
				crucial += cruWord.getString("wordName");
				crucial += "\n";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			
		}
		
		
		return crucial;
	}
	public boolean delWord(String deleteWord) {
		try {
			Statement delWordStmt = con.createStatement();
			String s = "DELETE FROM word_table WHERE wordName = ";
			s += "('" + deleteWord + "')";
			System.out.println(s);
			int i = delWordStmt.executeUpdate(s);
			if(i==1) {
				System.out.println("���ڵ� ���� ����");
				return true;
			}
			else {
				System.out.println("���ڵ� ���� ����");
				return false;
			}
		}catch(SQLException e){
		System.out.println(e.getMessage());
		return false;
		}
	}
	// ���� ä�õ��� ������ 
	public String showMsgLog() {
		String msg = "";
		try{
			Statement wordStmt = con.createStatement();
			String s= "SELECT * FROM msg_log ";
			System.out.println(s);
			ResultSet cruWord = wordStmt.executeQuery(s);
			while(cruWord.next()) {
				msg += cruWord.getString("msg");
				msg += "\n";
				
			}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println(msg);
		return msg;
	}
	// �޽����� �߿� �ܾ �ִ��� üũ
	public boolean checkMsg(String msg) {
		String crucial;
		try{
			Statement wordStmt = con.createStatement();
			String s= "SELECT wordName FROM word_table ";
			System.out.println(s);
			ResultSet cruWord = wordStmt.executeQuery(s);
			while(cruWord.next()) {
				crucial = cruWord.getString("wordName");
				if(msg.contains(crucial))
					return true;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);	
		}
		return false;
	}
}