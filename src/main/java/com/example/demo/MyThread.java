package com.example.demo;

import com.example.demo.api_connection.Url;
import com.example.demo.api_connection.main_connection;

public class MyThread extends Thread{

    private Url url;
    private int key_num;

    public MyThread(Url url){
        this.url = url;
        key_num = url.getUrl_num();
    }
    @Override
    public void run(){
        System.out.println("-Thread:"+this.key_num+" has been started-");
        try {
            while(true) {
                management_process_start();
                Thread.sleep(10000);
            }
        }catch (InterruptedException e){

        }
    }

    void management_process_start(){
        String threadName = Thread.currentThread().getName();
        new main_connection().connection_start(this.url);

    }

    public int getKey_num() {
        return key_num;
    }

}
