package executor;

import config.Configuration;
import config.MappedStatement;
import sql_source.BoundSql;
import sql_source.ParameterMapping;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 10:57
 * @description：简单执行JDBC代码
 * @modified By：
 * @version: $
 */
public class SimpleExecutor extends BaseExecutor{


    @Override
    public List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param, BoundSql boundSql) {
        //TODO 下面为1.0简单版，后期需要使用StatementHandler  ParameterHandler  ResultSetHandler去实现
        //获取链接
        Connection connection = getConnection(configuration);
        //获取sql语句
        String sql = boundSql.getSql();

        //判断创建哪种Statement
        String statementType = mappedStatement.getStatementType();
        ResultSet resultSet = null;
        List<Object> results = null;
        try {
            if("prepared".equals(statementType)){
                //创建Statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //设置参数
                parameterize(preparedStatement,mappedStatement,boundSql,param);
                //执行Statement
                resultSet = preparedStatement.executeQuery();

            }else if("callable".equals(statementType)){
                //创建Statement
                //设置参数
                //执行Statement
            }

            //处理结果集
            results = new ArrayList<>();
            if(resultSet!=null){
                handleResultSet(mappedStatement,resultSet,results);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return results;
    }


    private void handleResultSet(MappedStatement mappedStatement, ResultSet resultSet, List<Object> results) throws Exception {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        //每遍历一次是一行数据，对应一个映射对象
        while(resultSet.next()){
            Object result = resultTypeClass.newInstance();
            //每一列，对应映射对象的一个属性
            //列的名称要和对象的属性名称一致
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result,resultSet.getObject(i+1));
            }
            results.add(result);
        }
    }

    private void parameterize(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql, Object param) throws Exception {
        //先判断入参类型
        Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
        if(parameterTypeClass == Integer.class){
            preparedStatement.setObject(1,Integer.parseInt(param.toString()));
        }else if(parameterTypeClass == String.class){
            preparedStatement.setObject(1,param.toString());
        }else if(parameterTypeClass == Map.class){

        }else{//自定义对象类型
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i=0;i<parameterMappings.size();i++) {
                //获取#{}中的属性名称
                String name = parameterMappings.get(i).getName();

                //根据属性名称，获取入参中对应的属性的值
                //要求#{}中的属性名称和入参对象中的属性名称一致
                Field field = parameterTypeClass.getDeclaredField(name);
                field.setAccessible(true);
                Object value = field.get(param);

                preparedStatement.setObject(i+1,value);
            }
        }
    }

    private Connection getConnection(Configuration configuration) {
        DataSource dataSource = configuration.getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
