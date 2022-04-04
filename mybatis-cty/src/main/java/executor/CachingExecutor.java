package executor;

import config.Configuration;
import config.MappedStatement;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 10:53
 * @description：
 * @modified By：
 * @version: $
 */
public class CachingExecutor implements Executor{

    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        //处理二级缓存

        return delegate.query(mappedStatement,configuration,param);
    }
}
