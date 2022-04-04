package sql_source;

import sql_node.DynamicContext;
import sql_node.SqlNode;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 15:06
 * @description：
 * @modified By：
 * @version: $
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(null);
        //将sqlNode处理成一条sql语句
        rootSqlNode.apply(context);
        //该sql语句此时还包含#{},不包含${}
        String sql = context.getSql();
        //通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        //将解析的结果最终封装成StaticSqlSource
        sqlSource = sqlSourceParser.parse(sql);
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        return sqlSource.getBoundSql(paramObject);
    }
}
