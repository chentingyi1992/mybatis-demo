package config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/30 15:39
 * @description：封装了全局配置文件和映射配置文件的信息
 * @modified By：
 * @version: $
 */
public class Configuration {

    private DataSource dataSource;
    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatementMap.put(statementId,mappedStatement);
    }

    public MappedStatement getMappedStatement(String statementId){
        MappedStatement mappedStatement = mappedStatementMap.get(statementId);
        return mappedStatement;
    }
}
