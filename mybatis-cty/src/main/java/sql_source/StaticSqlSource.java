package sql_source;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 14:24
 * @description：
 * @modified By：
 * @version: $
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappingList;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        return new BoundSql(sql,parameterMappingList);
    }
}
