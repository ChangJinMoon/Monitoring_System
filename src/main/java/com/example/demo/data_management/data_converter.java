package com.example.demo.data_management;

import com.example.demo.api_connection.Url;

import java.util.HashMap;

public class data_converter {
    //use hash map
    //load list form text
    //add list on program
    //save process -> hash map data convert string -> save txt file => when api list upload

    //1.load list form text
    public HashMap<Integer,String> String_to_map(String sentence){
        if(sentence.length() <= 1)
            return null;
        HashMap<Integer,String> api_list = new HashMap<>(3);
        String[] temp = sentence.split("\n");

        for(String s : temp) {
            String[] temp1 = s.split(" ");
            api_list.put(Integer.parseInt(temp1[0]),temp1[1]);
        }
        return api_list;
    }

    //2.add list on program
    public void put_api_list(HashMap<Integer,String> map,String key, String url){

        map.put(Integer.parseInt(key),url);
    }
    //2-1.remove list on program
    public void pop_api_list(HashMap<Integer,String> map,String key){

        map.remove(Integer.parseInt(key));
    }

    //3.save recent api_list to txt files
    public String map_to_String(HashMap<Integer,String> map){
        StringBuilder temp = new StringBuilder();
        map.forEach((key,value)
            ->temp.append(Integer.toString(key)+" "+ value + "\n"));

        return new String(temp);
    }

    public Url String_to_url(String sentence){
        Url url = new Url();
        String[] result = sentence.split(" ");
        if(result.length == 0)
            return null;
        url.setHost_url(result[0]);
        url.setAuthorization(0,result[1]+" "+result[2]);
        return url;
    }

}
