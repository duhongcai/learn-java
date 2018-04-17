package cn.qtec.learn.mybatis;

import java.io.Serializable;

/**
 * Created by duhc on 2018/4/16.
 */
public class User implements Serializable{
    private int id;
    private String userName;
    private int sex;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
                '}';
    }
}
