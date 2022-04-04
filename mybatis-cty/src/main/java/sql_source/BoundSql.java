package sql_source;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/31 15:08
 * @description：
 * @modified By：
 * @version: $
 */
public class BoundSql {
    //JDBC可以执行的sql语句
    private String sql;
    //参数集合
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
