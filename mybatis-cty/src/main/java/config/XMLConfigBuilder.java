package config;

import io.Resources;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.DocumentUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/30 15:38
 * @description： 专门用来解析全局配置文件的类
 * @modified By：
 * @version: $
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parse(InputStream inputStream) {
        //通过流获取document
        Document document = DocumentUtils.readDocument(inputStream);
        //获取根标签
        Element rootElement = document.getRootElement();
        parseConfiguration(rootElement);
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
        //通过根标签获取environments标签
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        //通过根标签获取mappers标签
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
    }

    private void parseMappers(Element mappers) {
        List<Element> mapper = mappers.elements("mapper");
        for (Element element : mapper) {
            parseMapper(element);
        }
    }

    /**
     * 解析mapper标签
     * @param mapper
     */
    private void parseMapper(Element mapper) {
        String resource = mapper.attributeValue("resource");
        Resources resources = new Resources();
        InputStream inputStream = resources.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);
        //创建专门来解析映射文件的解析类
        XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
        mapperBuilder.parse(document.getRootElement());
    }

    private void parseEnvironments(Element environments) {
        //获取environments的default设置的值
        String aDefault = environments.attributeValue("default");
        if(aDefault==null || "".equals(aDefault)){
            return;
        }
        //获取多个environment标签
        List<Element> environment = environments.elements("environment");
        for (Element element : environment) {
            //找到id值与配置环境相同的配置
            String id = element.attributeValue("id");
            if(aDefault.equals(id)){
                parseDataSource(element.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element dbElement) {
        String type = dbElement.attributeValue("type");
        if("DBCP".equals(type)){
            BasicDataSource basicDataSource = new BasicDataSource();
            Properties properties = new Properties();
            List<Element> propElement = dbElement.elements();
            for (Element prop : propElement) {
                String name = prop.attributeValue("name");
                String value = prop.attributeValue("value");
                properties.put(name,value);
            }
            basicDataSource.setDriverClassName(properties.getProperty("driver"));
            basicDataSource.setUrl(properties.getProperty("url"));
            basicDataSource.setUsername(properties.getProperty("username"));
            basicDataSource.setPassword(properties.getProperty("password"));
            configuration.setDataSource(basicDataSource);
        }
    }
}
