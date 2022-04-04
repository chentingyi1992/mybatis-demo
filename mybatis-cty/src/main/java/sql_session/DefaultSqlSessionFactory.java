package sql_session;

import config.Configuration;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 17:19
 * @description：
 * @modified By：
 * @version: $
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    //注入configuration对象
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
