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
 * @date ：Created in 2022/3/31 14:00
 * @description：用来解析映射文件中的select/insert等CRUD标签的类
 * @modified By：
 * @version: $
 */
public class XMLStatementBuilder {

    private Configuration configuration;



    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }



    public void parse(Element selectElement, String namespace) {
        String statementId = selectElement.attributeValue("id");
        if(statementId==null || "".equals(statementId)){
            return;
        }
        //一个CRUD标签对应一个MappedStatement对象
        //一个MappedStatement对象由一个statementId来标识，所以保证唯一性
        statementId = namespace + "." + statementId;

        String parameterType = selectElement.attributeValue("parameterType");
        Class<?> parameterClass = resolveType(parameterType);

        String resultType = selectElement.attributeValue("resultType");
        Class<?> resultClass = resolveType(resultType);

        String statementType = selectElement.attributeValue("statementType");
        if(statementType==null || "".equals(statementType)){
            statementType = "prepared";
        }

        //解析 sql文本
        SqlSource sqlSource = createSqlSource(selectElement);

        //封装数据
        //TODO 建议使用构建者模式去优化
        MappedStatement mappedStatement = new MappedStatement(statementId,parameterClass,resultClass,statementType,sqlSource);
        configuration.addMappedStatement(statementId,mappedStatement);

    }

    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptParser xmlScriptParser = new XMLScriptParser();
        SqlSource sqlSource = xmlScriptParser.parseScriptNode(selectElement);
        return sqlSource;
    }



    private Class<?> resolveType(String parameterType) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }



}
