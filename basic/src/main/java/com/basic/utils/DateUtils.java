package com.basic.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangzhilong on 2016/9/30.
 */
public class DateUtils {

    private DateUtils(){} ;

    public static long dayBetWeeb(Date date1,Date date2){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        return Math.abs(time2-time1) / (1000 * 60 * 60 * 24) ;
    }

}
