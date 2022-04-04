package sql_source;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 14:35
 * @description：
 * @modified By：
 * @version: $
 */
public interface SqlSource {

    /**
     * 根据入参对象，获取JDBC
     * @param paramObject
     * @return
     */
    BoundSql getBoundSql(Object paramObject);
}
