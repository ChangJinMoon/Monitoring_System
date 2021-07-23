package com.example.demo.data_management;

import java.io.File;
import java.util.Scanner;

public class Check_directroy {

    static String file_path;

    public Check_directroy init(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Put main File path:");
            if(setFile_path(scan.nextLine()))
                break;
        }
        return this;
    }

    boolean setFile_path(String main_file_path) {
        File folder = new File(main_file_path);

        if(!folder.exists()) {
            System.out.println("Wrong file_path:" + main_file_path);
            return false;
        }
        else {
            System.out.println("Find directory:" + main_file_path);
            this.file_path = main_file_path;
            return true;
        }
    }

    public static String getFile_path() {
        return file_path;
    }
}
