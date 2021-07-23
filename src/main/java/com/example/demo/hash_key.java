package com.example.demo;

import java.time.LocalDateTime;

public class hash_key {

    public int make_hash_key(){
        String month,day,hour,minute,second;
        String total;

        LocalDateTime now = LocalDateTime.now();
        month = Integer.toString(now.getMonthValue());
        day = Integer.toString(now.getDayOfMonth());
        hour = Integer.toString(now.getHour());
        minute = Integer.toString(now.getMinute());
        second = Integer.toString(now.getSecond());

        total = month+day+hour+minute+second;
        return Integer.parseInt(total);
    }

}
