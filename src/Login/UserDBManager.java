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
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println(dbName + "데이터베이스 연결 성공");
		} catch(ClassNotFoundException e){
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch(SQLException e){
			System.out.println("연결에 실패하였습니다.");
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
	// 회원 로그인, 아이디와 비밀번호를 입력받아 성공:true, 실패:false를 반환
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
				System.out.println("로그인 성공");
				return true;
			}
			else {
				System.out.println("로그인 실패");
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			return false;
		}
	}
	
	// 회원 추가, 이름과 비밀번호를 입력받아 성공:true, 실패:false / 아이디를 회원에게 알려줌
	public boolean addUser(String addUserName, String addUserPwd) {
		try{
			Statement stmt = con.createStatement();
			String s = "INSERT INTO user_table (uid, pwd) VALUES ";
			s += "('" + addUserName + "','"+ addUserPwd + "')";
			System.out.println(s);
			int i = stmt.executeUpdate(s);
			if(i==1) {
				System.out.println("레코드 추가 성공");
				return true;
			}
			else {
				System.out.println("레코드 추가 실패");
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
			return false;
		}
	}	

	// 마지막에 추가된 계정의 id를 반환
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
	//로그를 저장
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
				System.out.println("레코드 추가 성공");
			else
				System.out.println("레코드 추가 실패");
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
	// 중요 단어 추가
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
					System.out.println("레코드 추가 성공");
					return true;
				}
				else {
					System.out.println("레코드 추가 실패");
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
	//중요 단어를 String으로 반환
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
				System.out.println("레코드 삭제 성공");
				return true;
			}
			else {
				System.out.println("레코드 삭제 실패");
				return false;
			}
		}catch(SQLException e){
		System.out.println(e.getMessage());
		return false;
		}
	}
	// 기존 채팅들을 보여줌 
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
	// 메시지에 중요 단어가 있는지 체크
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