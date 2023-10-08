package com.jr.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * className MyDateConverter
 * packageName com.jr.util
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/08 15:00
 */
public class MyDateConverter implements Converter<String, Date> {///把 S---转成T

    @Override
    public Date convert(String str) {
        Date dt=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt=sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
}
