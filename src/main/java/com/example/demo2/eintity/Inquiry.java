package com.example.demo2.eintity;

import java.time.LocalDateTime;

//テータベースのテーブルと紐付ける
public class Inquiry {
    private int id;
    private String name;
    private String email;
    private String contents;
    private LocalDateTime created;

    public Inquiry(){
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreated() {
        return created;
    }


}
