package sql_node;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 14:51
 * @description： 封装了sql信息，可以封装入参信息
 * @modified By：
 * @version: $
 */
public class DynamicContext {
    private StringBuffer sb = new StringBuffer();
    private Map<String, Object> bindings = new HashMap<>();

    public String getSql(){
        return sb.toString();
    }

    public void appendSql(String sql){
        sb.append(sql).append(" ");
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    //将传入的入参对象封装到map中
    public DynamicContext(Object parameter) {
        this.bindings.put("_parameter",parameter);
    }
}
