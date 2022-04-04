package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/30 15:35
 * @description：
 * @modified By：
 * @version: $
 */
public class DocumentUtils {

    public static Document readDocument(InputStream inputStream){
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
