package executor;

import config.Configuration;
import config.MappedStatement;
import sql_source.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 10:56
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class BaseExecutor implements Executor{

    private Map<String,List<Object>> oneLevelCache = new HashMap<>();

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        //调用sqlSource获取boundSql
        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(param);
        //处理一级缓存
        List<Object> results = oneLevelCache.get(boundSql.getSql());
        if(results!=null){
            return (List<T>) results;
        }
        //查询数据库
        results = queryFromDataBase(mappedStatement,configuration,param,boundSql);
        oneLevelCache.put(boundSql.getSql(),results);
        return (List<T>) results;
    }

    public abstract List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param,BoundSql boundSql);
}
