package com.example.demo.api_connection;

import org.apache.commons.codec.binary.Base64;

import java.util.Scanner;
public class Url {
    private String host_url;
    private String response;
    private String authorization;

    String main_file_path;
    int url_num;
    private String json_header = "accept:application/json";
    private String null_prevention_header = "1";

    Scanner scan;

    public String getNull_prevention_header() {
        return null_prevention_header;
    }

    void init(String main_file_path,int url_num){
        this.main_file_path = main_file_path;
        this.url_num = url_num;
    }

    public String getMain_file_path() {
        return main_file_path;
    }

    public int getUrl_num() {
        return url_num;
    }

    public String getJson_header() {
        return json_header;
    }

    public void setJson_header(String json_header) {
        this.json_header = json_header;
    }
    public String getHost_url() {
        return host_url;
    }

    public void setHost_url() {
        String temp;
        System.out.println("Please put Api url:");
        scan = new Scanner(System.in);
        temp = scan.next();
        this.host_url = temp;
    }
    public void setHost_url(String temp) {
        this.host_url = temp;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int n,String author) {
        //Create Authorization String
        if(n == 1) {
            String encodeValue = Base64.encodeBase64String(author.getBytes());
            this.authorization = "Basic " + new String(encodeValue);
        }
        else
            this.authorization = author;
    }

    public void set_url(){
        setHost_url();
        scan_id_passwd();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public void print_response(){
        if(response.isEmpty()){
            System.out.println("response is empty");
        }
        else {
            System.out.println("=============================");
            System.out.println("Request Api url:"+getHost_url());
            System.out.println("Result:");
            System.out.print(getResponse());
            System.out.println("=============================");
        }
    }

    void scan_id_passwd(){
        String id,passwd;
        scan = new Scanner(System.in);
        System.out.println("Please put id:");
        id = scan.nextLine();
        System.out.println("Please put password:");
        passwd = scan.nextLine();

        setAuthorization(1,id+":"+passwd);
    }
}
