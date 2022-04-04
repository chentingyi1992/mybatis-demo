package sql_node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 15:13
 * @description：封装了所有解析出来的sqlnodei节点信息，方便统一处理
 * @modified By：
 * @version: $
 */
public class MixedSqlNode implements SqlNode{

    private List<SqlNode> sqlNodes = new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(dynamicContext);
        }
    }
}
