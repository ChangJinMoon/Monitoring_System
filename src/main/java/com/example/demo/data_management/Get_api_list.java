package com.example.demo.data_management;

import java.io.*;
import java.util.HashMap;

public class Get_api_list {
    data_converter converter = new data_converter();
    HashMap<Integer,String> api_list ;

    private String file_name = "/Api_list.txt";
    static String main_file_path;
    private String api_file_directory ="/Api_Info";


    public HashMap<Integer, String> getApi_list() {
        return api_list;
    }

    public Get_api_list init(String main_file_path){
        this.main_file_path = main_file_path;
        //upload list this program
        if((api_list =converter.String_to_map(load_api_list())) == null)
            api_list = new HashMap<>();
        return this;
    }

    public String load_api_list(){
        System.out.println("--Check Api list file--");

        try {
            FileInputStream r_m = new FileInputStream(main_file_path + file_name);// already exist
            StringBuilder api_list = new StringBuilder();

            int read;
            while((read =r_m.read()) != -1){
                api_list.append((char)read);
            }
            System.out.println("Load last Api list:\n"+api_list);
            return new String(api_list);
        }catch(FileNotFoundException e){
            System.out.println("You don't have Api list.. now making new Api list!!");
            first_time();
        }catch(IOException e){//read save_file failed
            System.err.println("read Api list failed:"+e);
        }
        return "";
    }

    void first_time(){
        try {
            //make api_list file
            FileOutputStream w_f = new FileOutputStream(main_file_path + file_name);
            w_f.close();

            //make directory
            File file = new File(main_file_path + api_file_directory);
            file.mkdir();
        }catch(IOException e){
            System.err.println(e);
        }
    }
}
