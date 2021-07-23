package com.example.demo.process;

import com.example.demo.MyThread;
import com.example.demo.api_connection.Url;
import com.example.demo.api_connection.Url_control;
import com.example.demo.data_management.Main_file_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class main_process {

    menu_control menu ;
    Main_file_management f_m;
    Url_control control;
    HashMap<Integer,Url> activate_api = new HashMap<>();


    public void run(){
        menu = new menu_control();
        f_m = new Main_file_management();
        control = new Url_control();
        //첫 화면
        menu.print_logo();
        //main_path 설정
        f_m.init();// api_list 로드
        //activate
        control.setMain_directory_path(f_m.getMain_file_path());
        f_m.getApi_list().forEach((key,value) -> control.load_url_list(key));
        //메뉴 화면
        while(this.init(menu.menu_init()));
        //program finish
        //수정....

    }

    boolean init(int menu){
        System.out.println("=====================================");
        switch(menu){
            case 1:
                //thread
                return start_program();
            case 2:
                f_m.print_api_list();
                //check api_list full
                //add api
                control.url_register();
                //add api to map
                f_m.add_app_list(Integer.toString(control.getUrl_list().get(control.get_list_last() - 1).getUrl_num()), control.getUrl_list().get(control.get_list_last() - 1).getHost_url());
                f_m.print_api_list();
                return true;
            case 3:
                //api_list 싹다 출력 -> 활성화 할 api key 입력 -> activate 된 api list 따로 생성 -> 해당 url이 리스트에서 몇번째 인덱스인지 저장
                f_m.print_api_list();
                int activate_key = this.menu.activate_init();
                make_activate_api_list(activate_key,control.find_url_using_key(activate_key));
                return true;
            case 4:
                f_m.print_api_list();
                return true;
            case 5:
                System.out.println("Program exit!!");
                break;
        }
        return  false;
    }

    boolean start_program(){
        if(activate_api.size() == 0) {
            System.out.println("Please Upload activate api url(go to menu 3!!)");
            return true;
        }
        List<MyThread> thread = new ArrayList<>();
        activate_api.forEach((key,value) ->
                thread.add(new MyThread(value)));

        for(MyThread s:thread) {
            s.setName(Integer.toString(s.getKey_num()));
            s.start();
        }
        return false;
    }

    void make_activate_api_list(int activate_key,Url url){
        if(activate_api.size() == 3)
            System.out.println("Activate Api list full already(max_size = 3)\n");
        else {
            if(url != null) {
                activate_api.put(activate_key, url);
                System.out.println("Successfully upload activate api");
            }
            else
                System.out.println("You put wrong api key");

        }
    }
}
