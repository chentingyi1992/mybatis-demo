package config;

import node_handler.NodeHandler;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import sql_node.*;
import sql_source.DynamicSqlSource;
import sql_source.RawSqlSource;
import sql_source.SqlSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 16:44
 * @description：
 * @modified By：
 * @version: $
 */
public class XMLScriptParser {

    private boolean isDynamic;
    private Map<String,NodeHandler> nodeHandlerMap = new HashMap<>();

    public XMLScriptParser() {
        initNodeHandlers();
    }

    private void initNodeHandlers(){
        this.nodeHandlerMap.put("if",new IfNodeHandler());
        //...
        // this.nodeHandlerMap.put("where",new WhereNodeHandler());
    }

    public SqlSource parseScriptNode(Element selectElement){
        //解析动态标签
        MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);

        List<SqlNode> contents = new ArrayList<>();
        SqlSource sqlSource = null;
        //判断是否包含${}
        if(isDynamic){
            sqlSource = new DynamicSqlSource(rootSqlNode);
        }else{
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<Node> elements = selectElement.elements();
        List<SqlNode> contents = new ArrayList<>();
        for (int i=0; i<selectElement.nodeCount();i++) {
            Node node = selectElement.node(i);
            if(node instanceof Text){
                String sqlText = node.getText().trim();
                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                if(textSqlNode.isDynamic()){
                    this.isDynamic = true;
                    contents.add(textSqlNode);
                }else{
                    contents.add(new StaticSqlNode(sqlText));
                }
            }else if(node instanceof Element){
                //此处通过NodeHandler去处理不同的标签
                Element nodeToHandler = (Element) node;
                String name = nodeToHandler.getName();

                //怎么去查找对应的标签处理器呢，需要通过标签名称
                NodeHandler nodeHandler = nodeHandlerMap.get(name);
                nodeHandler.handlerNode(nodeToHandler,contents);

                this.isDynamic = true;
            }
        }
        return new MixedSqlNode(contents);
    }

    class IfNodeHandler implements NodeHandler{

        @Override
        public void handlerNode(Element nodeToHandler, List<SqlNode> contents) {
            String test = nodeToHandler.attributeValue("test");
            MixedSqlNode mixedSqlNode = parseDynamicTags(nodeToHandler);
            contents.add(new IfSqlNode(test,mixedSqlNode));
        }
    }

}
