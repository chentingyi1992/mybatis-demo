package utils;

import sql_source.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 14:26
 * @description：
 * @modified By：
 * @version: $
 */
public class ParameterMappingTokenHandler implements TokenHandler{

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    @Override
    public String handlerToken(String content) {
        parameterMappingList.add(builderParameterMapping(content));
        return "?";
    }

    private ParameterMapping builderParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
