package com.example.demo.data_management;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Save_api_list {
    data_converter converter = new data_converter();
    HashMap<Integer,String> api_list ;

    private String file_name = "/Api_list.txt";
    static String main_file_path;

    public void init(String main_file_path,HashMap<Integer,String> api_list){
        this.main_file_path = main_file_path;
        this.api_list = api_list;

        if(save_api_list(converter.map_to_String(api_list)))
            System.out.println("api_list upload success");
        else
            System.out.println("api_list upload fail");
    }

    boolean save_api_list(String sentence){
        System.out.println("--Save Api list--");
        try {
            FileOutputStream w_f = new FileOutputStream(main_file_path +file_name);
            w_f.write(sentence.getBytes(StandardCharsets.UTF_8));
            w_f.close();
            System.out.println("Save Api list Success!!\n");
            return true;
        }catch(FileNotFoundException e){
            System.out.println("Search Api list file failed!!:"+e);
        }catch(IOException e){
            System.out.println("Save Api list failed!!:"+e);
        }
        System.out.println();
        return false;
    }
}
