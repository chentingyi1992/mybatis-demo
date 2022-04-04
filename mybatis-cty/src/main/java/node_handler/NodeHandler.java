package node_handler;

import org.dom4j.Element;
import sql_node.SqlNode;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 14:22
 * @description：处理select标签的子标签
 * @modified By：
 * @version: $
 */
public interface NodeHandler {

    void handlerNode(Element nodeToHandler, List<SqlNode> sqlNodeList);
}
