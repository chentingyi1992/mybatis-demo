package sql_node;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 14:45
 * @description： 保存了非${}的sql文本信息
 * @modified By：
 * @version: $
 */
public class StaticSqlNode implements SqlNode{

    private String sqlText;

    public StaticSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        dynamicContext.appendSql(sqlText);
    }
}
