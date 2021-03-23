import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
	private static final String url = "jdbc:postgresql://localhost/hangman";
    private static final String user = "postgres";
    private static final String password = "traecy";
    
	 public Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return conn;
	    }
}
