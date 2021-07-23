package com.example.demo.api_connection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Api_file_management {
    String file_path;
    String save_file = new String("/save/save_point.txt");
    String time_stamp;

    Scanner scan;

    public Api_file_management(String main_file_path, int url_num){
        this.file_path = setFile_path(main_file_path,url_num);
        this.time_stamp = load_last_point();
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public synchronized String setFile_path(String main_file_path,int url_num) {
        String directory_name = main_file_path +"/"+Integer.toString(url_num);
        File folder = new File(directory_name);

        if(!folder.exists()) {
            folder.mkdir();
            System.out.println("make directory name:" + directory_name);
        }
        return directory_name;
    }

    public synchronized String load_last_point(){
        //System.out.println("--Check save point--");
        File folder = new File(file_path+"/save");

        if(!folder.exists()){
            try{
                folder.mkdir();
                System.out.println("make directory name (save)!!");
            }catch(Exception e){System.err.println("make directory failed:"+e);}
        }
        else{//find folder and check save_point file
            try {
                FileInputStream r_m = new FileInputStream(file_path + save_file);// already exist
                StringBuilder time_stamp = new StringBuilder();

                int read;
                while((read =r_m.read()) != -1){
                    time_stamp.append((char)read);
                }

                //System.out.println("Load last point:"+time_stamp+"\n");
                return new String(time_stamp);
            }catch(FileNotFoundException e){
                try{//success finding directory but save file not exist
                    System.out.println("You don't have save file.. now making new save file!!");
                    FileOutputStream w_f =new FileOutputStream(file_path+save_file);
                    w_f.close();
                }catch(FileNotFoundException e1){
                    System.err.println(e);
                }catch(IOException e1){
                    System.err.println("Make save file failed!!"+e);
                }
            }catch(IOException e){//read save_file failed

            }
            System.out.println();
        }
        return "";
    }

    synchronized boolean save_last_point(String date){
        System.out.println("--Save last point--");
        try {
            FileOutputStream w_f = new FileOutputStream(file_path +save_file);
            w_f.write(date.getBytes(StandardCharsets.UTF_8));
            w_f.close();
            System.out.println("Save last point Success!!\n");
            return true;
        }catch(FileNotFoundException e){
            System.out.println("Search save file failed!!:"+e);
        }catch(IOException e){
            System.out.println("Save last point failed!!:"+e);
        }
        System.out.println();
        return false;
    }

    public synchronized boolean write_txt(String jsonString,String date,String time_stamp){
        try {
            System.out.println("--Start making new txt File--");
            String file_name = file_path+"/"+date+".txt";
            FileOutputStream write_f = new FileOutputStream(file_name);
            write_f.write(jsonString.getBytes(StandardCharsets.UTF_8));
            write_f.close();
            System.out.println("Making new txt File success!!\n");
            save_last_point(time_stamp);
            return true;

        } catch (FileNotFoundException e) {
            System.err.println("Make txt file failed:");
            System.out.println("Error:Please check file_path!!"+"\n");
        } catch (IOException e) {
            System.err.println("Error:Write data failed:"+e+"\n");
            e.printStackTrace();
        }
        return false;
    }
}
