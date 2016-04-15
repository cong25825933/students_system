package model;

import face.AcessAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
public class User extends AcessAdapter{
    private String username;
    private String pwd;
    private String regdate;

    public boolean insert(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        rlt = dbDao.modify("insert into users values(?,?,CURRENT_DATE())",username,pwd);
        dbDao.close();
        return rlt;
    }
    public boolean exists(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        try {
            ResultSet rs = dbDao.select("select * from users where username=?", username);
            rlt = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbDao.close();
        return rlt;
    }
    public boolean check(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        try {
            ResultSet rs = dbDao.select("select * from users where username=? and pwd=?", username,pwd);
            rlt = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbDao.close();
        return rlt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(username != null ? !username.equals(user.username) : user.username != null);

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public User(String username, String pwd, String regdate) {

        this.username = username;
        this.pwd = pwd;
        this.regdate = regdate;
    }

    public User(String username, String pwd) {

        this.username = username;
        this.pwd = pwd;
    }
}
