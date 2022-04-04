package utils;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.math.BigDecimal;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/1 14:56
 * @description：
 * @modified By：
 * @version: $
 */
public class OgnlUtils {


    /**
     * 根据ognl表达式，获取指定对象的值
     * @param expression
     * @param paramObject
     * @return
     */
    public static Object getValue(String expression,Object paramObject){
        OgnlContext context = new OgnlContext();
        context.setRoot(paramObject);

        //mybatis中的动态标签使用的是ognl表达式
        //mybatis中的${}使用的是ognl表达式
        Object ognlExpression = null;
        Object value = null;
        try {
            ognlExpression = Ognl.parseExpression(expression);
            value = Ognl.getValue(ognlExpression, context);
        } catch (OgnlException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 通过ognl表达式，去计算boolean类型的结果
     * @param expression
     * @param paramObjcet
     * @return
     */
    public static boolean evaluateBoolean(String expression,Object paramObjcet){
        Object value = OgnlUtils.getValue(expression, paramObjcet);
        if(value instanceof Boolean){
            return (Boolean) value;
        }
        if(value instanceof Number){
            return new BigDecimal(String.valueOf(value)).compareTo(BigDecimal.ZERO)!=0;
        }
        return value!=null;
    }
}
