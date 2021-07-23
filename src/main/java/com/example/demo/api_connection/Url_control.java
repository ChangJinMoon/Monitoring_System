package com.example.demo.api_connection;


/*
* //TODO
*    1.Activate url method -> check api connection
*    2.add Url info
*   3.
*
* */

import com.example.demo.data_management.Api_info;
import com.example.demo.data_management.data_converter;
import com.example.demo.hash_key;

import java.util.ArrayList;
import java.util.List;

public class Url_control {
    int url_maxSize = 100;
    String main_directory_path;

    hash_key key = new hash_key();

    public List<Url> getUrl_list() {
        return url_list;
    }

    List<Url> url_list = new ArrayList<>();

    public String getMain_directory_path() {
        return main_directory_path;
    }

    public void setMain_directory_path(String main_directory_path) {
        this.main_directory_path = main_directory_path;
    }

    public void url_register(){
        //make new api url
        Url api_url = new Url();

        while(true) {
            api_url.set_url();
            if(test_url_available(api_url) == true)
                break;
        }
        //get hash key
         api_url.init(main_directory_path,key.make_hash_key());
        //url list txt upload format -> haskey,activate

        //list upload
        url_list.add(api_url);
        //save url info -> url, author(txt)
        save_api_info(api_url);
    }

    boolean test_url_available(Url test_url){
        main_connection test = new main_connection();

        if(test.try_connect(test_url)){
            System.out.println("this api is available");
            return true;
            }
        else {
            System.out.println("this api can't use");
            return true;
        }
    }

    public void load_url_list(int key){
        //set Url
        if(url_list.size() > 3){
            //is full
            return;
        }
        else{
            //add Url_list
            String url_info = new Api_info().init(main_directory_path,key).find_info_text_file();
            url_list.add(new data_converter().String_to_url(url_info));
            if(url_list.size() != 0)
                url_list.get(url_list.size()-1).init(main_directory_path,key);
        }
    }

    void save_api_info(Url api_url){
        String sentence = api_url.getHost_url()+" "+api_url.getAuthorization();
        new Api_info().init(main_directory_path,api_url.getUrl_num()).save_api_info(sentence);
    }

    public int get_list_last(){
        return this.url_list.size();
    }

    public Url find_url_using_key(int activate_key){
        for(Url s:url_list) {
            if (s.getUrl_num() == activate_key)
                return s;
        }
        return null;
    }
}
