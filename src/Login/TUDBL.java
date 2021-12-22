package Login;
import java.sql.SQLException;

public class TUDBL {//user database login
	public static void main(String[] args) throws SQLException {
		UserDBManager udm = new UserDBManager("root", "0000", "focus_db");
		new UserDBLoginer(udm);
		
	}
}
