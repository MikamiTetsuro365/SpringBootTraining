package com.example.demo2.config;

import com.example.demo2.service.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;

//全てのコントローラに対する共通処理
@ControllerAdvice
public class WEBMvcControllerAdvice {

    //空文字をNullに変更
    @InitBinder
    public void initBinder(WebDataBinder dateBinder){
        dateBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    //例外処理
    //特定の例外に対応した処理
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleException(EmptyResultDataAccessException e, Model model){
        model.addAttribute("message", e);
        return "error/CustamPage";
    }

    //全てのコントローラ対象のエラー処理
    @ExceptionHandler(InquiryNotFoundException.class)
    public String handleException(InquiryNotFoundException e, Model model){
        model.addAttribute("message", e);
        return "error/CustamPage";
    }


}
