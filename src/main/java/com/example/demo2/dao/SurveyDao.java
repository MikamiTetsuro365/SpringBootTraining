package com.example.demo2.dao;
import com.example.demo2.eintity.Survey;
import java.util.List;

public interface SurveyDao {

    //SQLを更新する
    void insertServey(Survey survey);
    //SQLからデータを引っ張り出すてリストで返す
    List<Survey> getAll();

}
