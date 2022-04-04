package sql_session;

import config.Configuration;
import config.MappedStatement;
import executor.CachingExecutor;
import executor.Executor;
import executor.SimpleExecutor;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 17:25
 * @description：
 * @modified By：
 * @version: $
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if(list!=null || list.size()==1){
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        //根据statementId获取mappedStatement
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        Executor executor = new CachingExecutor(new SimpleExecutor());
        return executor.query(mappedStatement,configuration,param);
    }
}
