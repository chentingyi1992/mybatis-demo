package executor;

import config.Configuration;
import config.MappedStatement;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 10:51
 * @description：
 * @modified By：
 * @version: $
 */
public interface Executor {

    /**
     *
     * @param mappedStatement 获取sql语句和入参、出参类型信息
     * @param configuration 获取数据源连接信息
     * @param param 获取参数
     * @param <T>
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration,Object param);
}
