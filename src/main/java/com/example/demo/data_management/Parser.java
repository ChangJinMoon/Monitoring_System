package com.example.demo.data_management;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
    String date_time;
    String jsonString;

    JSONObject root;
    public Parser(String jsonString){
        this.jsonString = jsonString;
        try {
            root = new JSONObject(jsonString);
            setDate_time(root.getString("DateTime"));
            check_null_value();
        }catch(JSONException e){
            System.err.println(e);
        }
    }
    void check_null_value(){
        int i = 0;
        JSONArray symptoms = null;
        JSONObject temp = null;
        try {
            symptoms = root.getJSONArray("Symptoms");
            for(; i < symptoms.length(); i++){
                temp = symptoms.getJSONObject(i);
                temp.getString("Value");
            }
        }catch(JSONException e){
            try {
                temp.put("Value", 0);
            }catch(JSONException e1){}
        }
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String setDate_time_kst(String date_time_kst){
        try{
            root.put("DateTime_kst",date_time_kst);
            return root.toString();
        }catch(JSONException e) {
            System.err.println("Date changing(json) Error" + e);
            return null;
        }
    }

}
