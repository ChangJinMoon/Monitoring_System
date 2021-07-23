package com.example.demo.process;

import java.util.Scanner;

public class menu_control {

    int menu_init(){
        int temp = 0;
        while(true) {
            print_menu();
            if((temp = (int)set_answer("menu"))>0)
                break;
        }
        return temp;
    }

    int activate_init(){
        long temp = 0;
        while(true) {
            if((temp = set_answer("activate"))>0)
                break;
        }
        return (int)temp;
    }

    void print_menu(){
        System.out.println("=====================================");
        System.out.println("-----Menu----------------------------");
        System.out.println("1.Program Start!!");
        System.out.println("2.Upload new API.");
        System.out.println("3.Activate API.");
        System.out.println("4.Print Api List.");
        System.out.println("5.Program Exit.");
    }

    long set_answer(String sentence){
        System.out.println("Please put "+sentence+" number(ex) 1)");
        System.out.print("=>");
        Scanner scan = new Scanner(System.in);
        if(sentence.equals("menu")) {
            int answer = 999;
            try {
                answer = scan.nextInt();//need fix
            } catch (Exception e) {
            }
            scan.nextLine();
            if (answer >= 1 && answer <= 5)
                return answer;
            else {
                System.out.println("Wrong " + sentence + "number!!!");
                return -1;
            }
        }
        else{//activate
            long answer = scan.nextInt();
            return answer;
        }
    }

    String delete_input(int num){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input key_num:");
        String answer = scan.nextLine();

        scan.close();
        return answer;
    }

    void print_logo(){
        System.out.println("=======================================");
        System.out.println("***************************************");
        System.out.println("-----SPM Condition Monitoring System-----");
        System.out.println("***************************************");
        System.out.println("=======================================");
    }
}
