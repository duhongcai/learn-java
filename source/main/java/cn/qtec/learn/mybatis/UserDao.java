package cn.qtec.learn.mybatis;

import java.util.List;

/**
 * Created by duhc on 2018/4/16.
 */
public interface UserDao {
    List<User> getUser();
    int addUser(User user);
    int updateUser(User user);
    int delUser(int id);
}
