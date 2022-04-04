package sql_session;

import config.Configuration;
import config.XMLConfigBuilder;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 17:13
 * @description：使用构建者模式去创建SqlSessionFactory
 * @modified By：
 * @version: $
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactory build(InputStream inputStream){
        //执行解析流程
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        configuration = xmlConfigBuilder.parse(inputStream);
        return build();
    }


    public SqlSessionFactory build(Reader reader){
        return null;
    }

    private SqlSessionFactory build(){
        return new DefaultSqlSessionFactory(configuration);
    }
}
