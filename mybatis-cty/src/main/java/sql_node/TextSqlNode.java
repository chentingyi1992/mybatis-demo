package sql_node;

import utils.GenericTokenParser;
import utils.OgnlUtils;
import utils.SimpleTypeRegistry;
import utils.TokenHandler;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 14:58
 * @description： 封装sql文本
 * @modified By：
 * @version: $
 */
public class TextSqlNode implements SqlNode{

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        //处理${}，将处理之后的sql语句，追加到context中
        GenericTokenParser tokenParser = new GenericTokenParser("${","}",new BindingTokenParse(context));
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }

    public boolean isDynamic(){
        if(sqlText.indexOf("${}")>-1){
            return true;
        }
        return false;
    }

    public static class BindingTokenParse implements TokenHandler {

        private DynamicContext context;

        public BindingTokenParse(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression: 比如说${username},那么expression就是username，username也就是ognl表达式
         * @param expression
         * @return
         */
        @Override
        public String handlerToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if(paramObject==null){
                return "";
            }else if(SimpleTypeRegistry.isSimpleType(paramObject.getClass())){
                return String.valueOf(paramObject);
            }
            Object value = OgnlUtils.getValue(expression, context.getBindings());
            String strValue = value == null ? "" : String.valueOf(value);
            return strValue;
        }
    }
}
