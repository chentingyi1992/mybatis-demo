package io;

import java.io.InputStream;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/3/30 15:29
 * @description：
 * @modified By：
 * @version: $
 */
public class Resources {

    public InputStream getResourceAsStream(String location){
        return this.getClass().getClassLoader().getResourceAsStream(location);
//        return Resources.getResourceAsStream(location);
    }
}
