package sql_source;

import sql_node.TextSqlNode;
import utils.GenericTokenParser;
import utils.ParameterMappingTokenHandler;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 14:22
 * @description：
 * @modified By：
 * @version: $
 */
public class SqlSourceParser {
    public SqlSource parse(String sql) {
        //处理#{}，将处理之后的sql语句，追加到context中
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",tokenHandler);
        //此处获取到的sql语句，是完全符合JDBC执行要求的语句
        sql = tokenParser.parse(sql);
        //将解析之后的sql语句和参数集合都封装到StaticSqlSource中
        return new StaticSqlSource(sql,tokenHandler.getParameterMappingList());
    }
}
