package sql_session;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 17:19
 * @description：
 * @modified By：
 * @version: $
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();
}
