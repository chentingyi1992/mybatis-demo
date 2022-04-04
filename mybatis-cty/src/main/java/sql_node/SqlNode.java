package sql_node;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 14:50
 * @description：    封装不同的sql脚本，提供sql脚本处理功能
 * @modified By：
 * @version: $
 */
public interface SqlNode {
    void apply(DynamicContext context);
}
