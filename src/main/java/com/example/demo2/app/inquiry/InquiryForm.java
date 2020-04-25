package com.example.demo2.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryForm {

    //フィールドの制約条件の設定
    @NotNull
    @Size(min=1, max=20, message="Please input 20characters")
    private String name;

    //からの文字列を緩さらない
    @NotNull
    @Email(message = "Invalid E-mail Format")
    private String email;

    @NotNull
    private String contents;

    public InquiryForm(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContents() {
        return contents ;
    }
}
