package com.example.demo.data_management;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Api_info {
    //maindirectory + api_info + key.txt
    static String full_path;
    String half_path;
    public Api_info init(String main_directory,int key){
        full_path = main_directory+"/Api_info/"+key+".txt";
        half_path = main_directory+"/"+key;
        return this;
    }

    public String find_info_text_file(){
        try {
            FileInputStream r_m = new FileInputStream(full_path);// already exist
            StringBuilder api_list = new StringBuilder();

            int read;
            while((read =r_m.read()) != -1){
                api_list.append((char)read);
            }
            //need fix
            //api list format -> key/url
            return new String(api_list);
        }catch(FileNotFoundException e){
            System.out.println("You don't have"+full_path+".. now making new Api info!!");
            first_time();
        }catch(IOException e){//read save_file failed
            System.err.println("read Api list failed:"+e);
        }
        return "";
    }

    void first_time(){
        try {
            //make api_list file
            FileOutputStream w_f = new FileOutputStream(full_path);
            w_f.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public void save_api_info(String sentence){

        try {
            FileOutputStream write_f = new FileOutputStream(full_path);
            write_f.write(sentence.getBytes(StandardCharsets.UTF_8));
            write_f.close();

            //make directory
            File file = new File(half_path);
            file.mkdir();
        } catch (FileNotFoundException e) {
            System.err.println("Make txt file failed:");
        } catch (IOException e) {
            System.err.println("Error:Write data failed:"+e+"\n");
            e.printStackTrace();
        }
    }
}
