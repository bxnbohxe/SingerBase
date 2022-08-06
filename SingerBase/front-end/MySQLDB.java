
package singerbase;

import java.sql.*;


public class MySQLDB {
    private int portno = 3306;
    private String dbName = "mystore";
    private String dbUserName = "root";
    private String dbPassword = "";
    private String dbConnURL = "jdbc:mysql://localhost:" + String.valueOf(portno) + "/" + dbName;
    private Connection mysqlConn;
    private Statement stStatement;
    private PreparedStatement psExecuteNonQuery;
    private ResultSet mysqlResult;
    
    public MySQLDB() {
        try {
            mysqlConn = DriverManager.getConnection(dbConnURL, dbUserName, dbPassword);
        } catch (SQLException ex) { System.out.println("Connection Failed!!"); }
    }
    
    public int ExecuteNonQuery(String strQuery) {
        try {
            psExecuteNonQuery = mysqlConn.prepareStatement(strQuery);
            return psExecuteNonQuery.executeUpdate();
        } catch (SQLException ex) { System.out.println(ex); }
        return 0;
    }
    
    public void ExecuteNonQueryAtRoot(String strQuery) {
        try {
            mysqlConn = DriverManager.getConnection("jdbc:mysql://localhost:" + String.valueOf(portno) + "/", dbUserName, dbPassword);
            psExecuteNonQuery = mysqlConn.prepareStatement(strQuery);
            psExecuteNonQuery.executeUpdate();
            mysqlConn = DriverManager.getConnection(dbConnURL, dbUserName, dbPassword);
        } catch (SQLException ex) { System.out.println(ex); }
    }
    
    public String ExecuteScalar(String strQuery) {
        try {
            stStatement = mysqlConn.createStatement();
            mysqlResult = stStatement.executeQuery(strQuery);
            while(mysqlResult.next()) {
                return mysqlResult.getString(1);
            }
        } catch (SQLException ex) { System.out.println(ex); }
        return "";
    }
    
    public ResultSet ExecuteDataAdapter(String strQuery) {
        try {
            stStatement = mysqlConn.createStatement();
            mysqlResult = stStatement.executeQuery(strQuery);
            return mysqlResult;
        } catch (SQLException ex) { System.out.println(ex); }
        return null;
    }
}
