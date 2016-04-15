package model;

import face.AcessAdapter;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Yc on 2016/4/9 for students_system.
 */
public class UserInfo extends AcessAdapter {
    private String id;
    private String name;
    private String sex;
    private String birth;
    private InputStream photo;
    private InputStream resume;
    private String update_date;
    private String username;
    private String resume_type;
    public UserInfo(){}

    public void setResume_type(String resume_type) {
        this.resume_type = resume_type;
    }

    public String getResume_type() {
        return resume_type;
    }

    public UserInfo(String id, String name, String sex, String birth,String username) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.username = username;
    }

    public boolean insertPhoto(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        if(!existsByUsername())
            rlt = dbDao.modify("insert into students(photo,username) values(?,?)", photo,username);
        else
            rlt = dbDao.modify("update students set photo=? where username=?",photo,username);
        dbDao.close();
        return rlt;
    }

    public boolean insertResume(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        if(!existsByUsername())
            rlt = dbDao.modify("insert into students(resume,username,resume_type) values(?,?,?)", resume,username,resume_type);
        else
            rlt = dbDao.modify("update students set resume=?,resume_type=? where username=?",resume,resume_type,username);
        dbDao.close();
        return rlt;
    }
    public boolean remove(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        rlt = dbDao.modify("delete from students where username=?", username);
        dbDao.close();
        return rlt;
    }
    public boolean existsByUsername(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select * from students where username=?", username);

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }
    public boolean existsByUsernameId(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select * from students where username=? and id=?", username,id);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }
    public boolean existsById(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select * from students where id=?", id);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }

    public boolean insertBaseInfo(){
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        try {
            if(existsById()&&!existsByUsernameId()) return false;
            if(!existsByUsername())
                rlt = dbDao.modify("insert into students(id,name,sex,birth,update_date,username) values(?,?,?,?,current_date(),?)",
                        id,name,sex,new SimpleDateFormat("yyyy-MM-dd").parse(birth), username);
            else
                rlt = dbDao.modify("update students set update_date=current_date(),id=?,name=?,sex=?,birth=? where username=?",
                        id,name,sex,new SimpleDateFormat("yyyy-MM-dd").parse(birth), username);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return rlt;
    }

    @Override
    public boolean insert() {
        DbDao dbDao = new DbDao();
        boolean rlt = false;
        try {
            rlt = dbDao.modify("insert into students values(?,?,?,?,?,?,current_date(),?,?)",
                    id,name,sex,new SimpleDateFormat("yyyy-MM-dd").parse(birth),
                    photo,resume,username,resume_type);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return rlt;
    }

    public boolean selectResume() {
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select resume,resume_type from students where username=?", username);
            if(rs.next()) {
                resume = rs.getBinaryStream("resume");
                resume_type = rs.getString("resume_type");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }
    public boolean selectPhoto(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select photo from students where username=?", username);
            if(rs.next()) {
                photo = rs.getBinaryStream("photo");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }

    public boolean select(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select * from students where username=?", username);
            if(rs.next()) {
                id = rs.getString("id");
                name = rs.getString("name");
                sex = rs.getString("sex");
                birth = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("birth"));
                update_date = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("update_date"));
                photo = rs.getBinaryStream("photo");
                resume_type = rs.getString("resume_type");
                resume = rs.getBinaryStream("resume");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }
    public boolean selectById(){
        DbDao dbDao = new DbDao();
        try {
            ResultSet rs = dbDao.select("select * from students where id=?", id);
            if(rs.next()) {
                id = rs.getString("id");
                name = rs.getString("name");
                sex = rs.getString("sex");
                birth = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("birth"));
                update_date = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("update_date"));
                photo = rs.getBinaryStream("photo");
                resume_type = rs.getString("resume_type");
                resume = rs.getBinaryStream("resume");
                username = rs.getString("username");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbDao.close();
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != null ? !id.equals(userInfo.id) : userInfo.id != null) return false;
        return !(username != null ? !username.equals(userInfo.username) : userInfo.username != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public InputStream getResume() {
        return resume;
    }

    public void setResume(InputStream resume) {
        this.resume = resume;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo(String id, String name, String sex, String birth, InputStream photo, InputStream resume, String update_date, String username) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.photo = photo;
        this.resume = resume;
        this.update_date = update_date;
        this.username = username;
    }

    public UserInfo(InputStream photo, String id, String name, String sex, String birth, String username) {

        this.photo = photo;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.username = username;
    }

}
