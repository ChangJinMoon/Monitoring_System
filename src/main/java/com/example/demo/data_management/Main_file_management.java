package com.example.demo.data_management;

import java.util.HashMap;

public class Main_file_management {
    public String main_file_path;
    HashMap<Integer,String> api_list ;

    data_converter converter = new data_converter();

    public void init(){
        main_file_path = new Check_directroy().init().getFile_path();
        //upload list this program
        api_list = new Get_api_list().init(main_file_path).getApi_list();

    }

    public String getMain_file_path() {
        return main_file_path;
    }

    public HashMap<Integer, String> getApi_list() {
        return api_list;
    }
    public void print_api_list(){
        System.out.println("===================================");
        System.out.println("--------API LIST-------------------");

        api_list.forEach((key,value)
            -> System.out.println("No:"+key+" url:"+value));

        System.out.println("===================================");

    }

    public void add_app_list(String key,String url){
        data_converter converter = new data_converter();
        converter.put_api_list(api_list, key, url);
        converter.map_to_String(api_list);
        new Save_api_list().init(main_file_path, api_list);
    }

}
