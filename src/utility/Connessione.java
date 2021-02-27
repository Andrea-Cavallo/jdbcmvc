package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {

	static final String username = "root";
	static final String password = "12345678";
	static final String url = "jdbc:mysql://localhost:3306/campionatodb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public static Connection crea_connessione() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// crea la connessione e lo statement con cui eseguire le query
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	public static void chiudi_connessione(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
