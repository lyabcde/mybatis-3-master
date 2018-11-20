package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import test.dao.UserMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestMain {
    public static void main(String[] args) throws IOException {
        String resouce = "conf.xml";
        //InputStream is = TestMain.class.getClassLoader().getResourceAsStream(resouce);
        InputStream is = Resources.getResourceAsStream(resouce);

        // 构建sqlSession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();

        /**
         * 第一种方式: 直接执行已映射的 SQL 语句
         */
        String statement = "test.dao.UserMapper.getById";
        User user = session.selectOne(statement, 1);
        System.out.println(user);

        System.out.println("---------------------");

        /**
         * 第二种方式: 执行更清晰和类型安全的代码
         */
        UserMapper userMapper = session.getMapper(UserMapper.class);
        user = userMapper.getById(1);
        System.out.println(user);
    }
}
