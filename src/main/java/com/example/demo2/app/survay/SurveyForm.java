package com.example.demo2.app.survay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SurveyForm {

    @NotNull
    //数値に対してはMin-Maxが使える
    @Min(0)
    @Max(150)
    private int age;

    @NotNull
    @Min(1)
    @Max(5)
    private int satisfaction;

    @NotNull
    //文字列に対してはSize
    @Size(min=0, max=200, message="Please input 1-200 character")
    private String comment;

    public SurveyForm(){

    }

    public int getAge() {
        return age;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public String getComment() {
        return comment;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

}
