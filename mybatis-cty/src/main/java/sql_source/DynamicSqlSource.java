package sql_source;

import sql_node.DynamicContext;
import sql_node.SqlNode;
import sql_node.TextSqlNode;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 15:07
 * @description：
 * @modified By：
 * @version: $
 */
public class DynamicSqlSource implements SqlSource {

    /**
     * 只是封装了解析过程中产生的sqlNode解析信息
     */
    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        DynamicContext context = new DynamicContext(paramObject);
        //将sqlNode处理成一条sql语句
        rootSqlNode.apply(context);
        //该sql语句此时还包含#{},不包含${}
        String sql = context.getSql();
        //通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        //将解析的结果最终封装成StaticSqlSource
        SqlSource sqlSource = sqlSourceParser.parse(sql);
        //调用StaticSqlSource的方法
        return sqlSource.getBoundSql(paramObject);
    }
}
