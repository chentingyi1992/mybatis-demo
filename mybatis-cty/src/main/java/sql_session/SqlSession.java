package sql_session;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 17:22
 * @description：表示一次sql会话
 * @modified By：
 * @version: $
 */
public interface SqlSession {

    <T> T selectOne(String statementId,Object param);

    <T> List<T> selectList(String statementId, Object param);
}
