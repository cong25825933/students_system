package model;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
public class DbDao {
    public final static String URL="jdbc:mysql://localhost:3306/students";
    public final static String DRIVER="com.mysql.jdbc.Driver";
    public final static String USER="root";
    public final static String PWD="110114";
    static {
        Properties prop = new Properties();
        try {
//            System.out.println(new File("/").getAbsolutePath());
//            prop.load(new FileReader("../config/db.properties"));
//            DRIVER = prop.getProperty("jdbc.driver");
//            URL = prop.getProperty("jdbc.url");
//            USER = prop.getProperty("jdbc.user");
//            PWD = prop.getProperty("jdbc.pwd");
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected  Connection conn;
    public Connection getConn() throws SQLException {
        if(conn==null)
            conn = DriverManager.getConnection(URL, USER, PWD);
        return conn;
    }
    public ResultSet select(String sql,Object... args) throws SQLException {
//        getConn().createStatement().executeQuery();
        PreparedStatement ps = getConn().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        return ps.executeQuery();
    }

    public boolean modify(String sql,Object... args){
        PreparedStatement ps = null;
        try {
            ps = getConn().prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            if(ps.executeUpdate()!=1)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    public void close(){
        try {
            if(conn!=null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
