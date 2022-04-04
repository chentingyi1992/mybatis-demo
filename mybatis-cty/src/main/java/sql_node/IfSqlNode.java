package sql_node;

import utils.OgnlUtils;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 14:52
 * @description：
 * @modified By：
 * @version: $
 */
public class IfSqlNode implements SqlNode{

    private String test;
    private SqlNode rootSqlNode;

    public IfSqlNode(String test, SqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {

        Object paramObject = context.getBindings().get("_parameter");
        boolean testValue = OgnlUtils.evaluateBoolean(test,paramObject);
        if(testValue){
            rootSqlNode.apply(context);
        }
    }
}
