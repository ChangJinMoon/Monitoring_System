package com.example.demo.api_connection;

import com.example.demo.data_management.main_data_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class main_connection{
    String response ;

    main_data_management data;
    HttpURLConnection con ;

    public main_connection(){ }

    public void connection_start(Url api_url){
        try_connect(api_url);
        //seperate  move to main process
        data = new main_data_management(api_url);
    }

    public synchronized boolean try_connect(Url api_url) {
        try {
            //main api_connection code
            URL url = new URL(api_url.getHost_url());
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(500);//연결 대기 시간 설정
            con.setReadTimeout(500);//데이터 응답 대기 시간
            con.addRequestProperty("Accept",api_url.getJson_header());
            con.setRequestProperty("Authorization",api_url.getAuthorization());
            con.addRequestProperty("emptyanddefault",api_url.getNull_prevention_header());
            con.setRequestMethod("GET");
            con.setDoOutput(false);//입력 할지 출력 받을지 선택 만약 데이터를 받고 싶으면 false 입력은 true 설정

            //get data
            StringBuilder temporary_s = new StringBuilder();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //getResponseCode => 연결 상태를 받아오는 method ex) 401 200 등 /return type int
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                String line;
                while((line = br.readLine()) != null)
                    temporary_s.append(line).append("\n");

                br.close();

                print_stat("-"+api_url.getUrl_num()+"Api Connection Success: Getting api data is done!!");
                api_url.setResponse(temporary_s.toString());
                print_stat(api_url.getResponse());
                return true;
            }
            else{
                //add return null
                print_stat(new String("-Api Connection failed:"+con.getResponseMessage()));
                return false;
            }
        }catch(Exception e){
            System.out.println("-"+api_url.getUrl_num()+"-Connection error(Please check url or id,passwd):"+e+"\n");
            return false;
        }

    }
    void print_stat(String stat){
        System.out.println("=============================");
        System.out.println(stat);
        System.out.println("=============================");
    }
}
