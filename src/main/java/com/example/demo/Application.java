package com.example.demo;

import com.example.demo.api_connection.Url;
import com.example.demo.process.main_process;

/**
 * //TODO List
 * 1. 비밀번호 혹은 아이디가 없을때도 오류가 없게 코드 수정 - Done
 * 2. 코드 최적화
 * 3. 멀티 thread 구현 3개로 제한
 * 4. Event listener(특정키 입력시 프로그램 종료)
 * 5. Exe 파일 빌드
 * 6. 저작권 관련 조사 -done
 * 7. 실제 데이터 받아서 문제 없이 작동 확인
 * 8. value 0 -> 헤더를 추가 할거라서 필요 없음
 * 9. 각 url 마다 다른 디렉토리에 정보 저장
 *
 *
 * 7/21
 *
 * Author: Chang_Jin_Moon
 * Date: 07/08/2021
 *
 **/

public class Application {
    Url u;
    int run_num = 0 ;
    Application(){
        new main_process().run();
    }

    public static void main(String args[]){
        new Application();
        //SpringApplication.run(Application.class,args);
    }
}
