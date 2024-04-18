package Base;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class BaseDeDatos {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/autos";
    static final String USER = "root";
    static final String PASS = "joel123";
 
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}