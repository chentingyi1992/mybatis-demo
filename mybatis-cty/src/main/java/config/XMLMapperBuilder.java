package config;

import org.dom4j.Element;

import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 13:26
 * @description：专门来解析映射文件的类
 * @modified By：
 * @version: $
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        //为了方便管理statement，需要使用statement唯一标识
        String namespace = rootElement.attributeValue("namespace");
        List<Element> elements = rootElement.elements("select");
        for (Element selectElement : elements) {
            XMLStatementBuilder statementBuilder = new XMLStatementBuilder(configuration);
            statementBuilder.parse(selectElement,namespace);
        }
    }
}
