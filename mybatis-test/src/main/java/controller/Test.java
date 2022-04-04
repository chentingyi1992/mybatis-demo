package controller;

import config.Configuration;
import config.XMLConfigBuilder;
import entity.User;
import io.Resources;
import sql_session.SqlSession;
import sql_session.SqlSessionFactory;
import sql_session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/30 15:30
 * @description：
 * @modified By：
 * @version: $
 */
public class Test {

    @org.junit.Test
    public void test(){
        //1、指定全局配置文件路径
        String location = "mybatis-config.xml";
        //2、加载配置文件成InputStream
        Resources resources = new Resources();
        InputStream inputStream = resources.getResourceAsStream(location);
        //3、根据InputStream获取Document对象
        XMLConfigBuilder configBuilder = new XMLConfigBuilder();
        Configuration configuration = configBuilder.parse(inputStream);
        System.out.println("1");
//        4、需要专门对mybatis标签进行解析
//        Resources.getResourceAsStream()
    }

    @org.junit.Test
    public void test1(){
        //1、指定全局配置文件路径
        String location = "mybatis-config.xml";
        //2、加载配置文件成InputStream
        Resources resources = new Resources();
        InputStream inputStream = resources.getResourceAsStream(location);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、根据InputStream获取Document对象
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        String statementId = "test.findUserById";
        User param = new User();
        param.setId(1);
        param.setUsername("cty");
        User user = sqlSession.selectOne(statementId,param);
        System.out.println(user);
    }




}
