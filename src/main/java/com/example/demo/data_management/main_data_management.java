package com.example.demo.data_management;
/*
* TODO
* 9.event_listner
* 궁금한거
* 1. txt에 저장될 시간 어떤 형식?? 마지막 옵션 선택
* */

import com.example.demo.api_connection.Api_file_management;
import com.example.demo.api_connection.Url;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class main_data_management {
    private String json_response;
    private String time_kst;

    Parser parsing;
    Api_file_management f_m;
    Url url;
    public main_data_management(Url api_url){
        url =api_url;
        init(api_url.getResponse(),api_url.getHost_url(),api_url.getMain_file_path(),api_url.getUrl_num());
    }

    void init(String response,String url,String main_file_path, int url_num){
        //System.out.println("--Check json data--");
        this.json_response = response;

        if(json_response == null){
            //System.out.println("Empty data received from api!! pleas check api....");
            System.out.println("-"+this.url.getUrl_num()+"-Empty data received from api!! pleas check api....");
        }
        else {
            parsing = new Parser(json_response);
            f_m = new Api_file_management(main_file_path,url_num);
            String temp_time = f_m.getTime_stamp();

            if(detect_changing(parsing.getDate_time(),temp_time)){
                //데이터 변화 감지
                ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
                time_kst = convert_utc_to_kst(parsing.getDate_time());
                f_m.write_txt(parsing.setDate_time_kst(time_kst),new String(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),parsing.getDate_time());
            }
            else{
                convert_utc_to_kst(parsing.getDate_time());
            }

        }
    }

    String convert_utc_to_kst(String utc_time){
        //System.out.println("--Start converting utc to kst--");

        ZonedDateTime utc = ZonedDateTime.parse(utc_time);
        LocalDateTime kst_date= utc.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        ZonedDateTime kst = kst_date.atZone(ZoneId.of("Asia/Seoul"));
        //temporary code
        /*
        System.out.println("zone time format date(utc):"+utc);
        System.out.println("zone time format date(kst):"+kst);
        System.out.println("local time format date:"+kst_date+"\n");
         */

        return new String(kst_date.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    boolean detect_changing(String temp_time,String now_time){
        System.out.print("-"+this.url.getUrl_num()+"-");
        if(temp_time.equals(now_time)){
            System.out.println("Same data!!\n");
            return false;
        }
        else{
            System.out.println("Detect data changing!!\n");
            return true;
        }
    }

}
