package cn.qtec.learn.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * Created by duhc on 2018/4/16.
 */
public class MybatisDemo {

    public static void main(String[] args) {
        String resource = "config.xml";
        InputStream is = MybatisDemo.class.getClassLoader().getResourceAsStream(resource);
        /**
         * 初始化资源 生成sqlSessionFactory
         */
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        /**
         * getMapper生成代理对象
         * 集成spring的话 ，在spring容器给指定bean注入mapper的时候get出来  @Autowired
         * MapperScannerConfig -- >解析配置文件 --> ClassPathMapperScanner --> MapperFactoryBean 调用父类SqlsessionDaoSupport 的方法 setSqlsessionFactory 设置
         * new SqlSessionTemplate( sqlsessionFactory ) 返回session
         * 的代理类 sessionProxy --> 完成openSession /closeSession
         */
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> user = userDao.getUser();
        System.out.println(user);
    }
}
