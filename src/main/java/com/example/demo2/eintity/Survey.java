package com.example.demo2.eintity;

import java.time.LocalDateTime;

public class Survey {

    private int id;
    private int age;
    private int satisfaction;
    private String comment;
    private LocalDateTime created;

    public Survey(){

    }

    public int getId(){
        return id;
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

    public LocalDateTime getCreated(){
        return created;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
