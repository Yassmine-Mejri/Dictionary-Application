import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionOracle {


    static String user = "SYSTEM";
    static String password = "0000";
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String driver ="oracle.jdbc.driver.OracleDriver";


    public static Connection getCon(){
        Connection con = null;
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url,user,password);
                System.out.println("Connected with connection oracle");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}