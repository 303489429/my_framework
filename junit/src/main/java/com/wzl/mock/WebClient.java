package com.wzl.mock;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangzhilong on 2016/10/14.
 */
public class WebClient {

    public String getContend(URL url){
        StringBuffer content = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            int count ;
            while (-1 != (count = is.read())){
                content.append(new String(Character.toChars(count))) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return  content.toString() ;
    }
}
