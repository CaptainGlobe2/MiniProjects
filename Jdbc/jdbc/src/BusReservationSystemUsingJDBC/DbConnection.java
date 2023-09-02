package BusReservationSystemUsingJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String url = "jdbc:mysql://localhost:3306/busreserve";
    private static final String userName = "root";
    private static final String passWord = "rootmysql";

    public static Connection getConnection() throws SQLException {//the use of sqlexception is must because some tiems server is down and password or username are might be wrong so that we throws an exception
        return DriverManager.getConnection(url,userName,passWord);
    }
}

