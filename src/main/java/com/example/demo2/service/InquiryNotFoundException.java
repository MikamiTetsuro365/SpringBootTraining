package com.example.demo2.service;

public class InquiryNotFoundException extends RuntimeException {

    private static  final long serialVersionUID = 1L;

    //継承してるのでメッセージをオーバーライド
    //ビジネスロジック場でThrow
    public InquiryNotFoundException(String message){
        super(message);
    }

}
