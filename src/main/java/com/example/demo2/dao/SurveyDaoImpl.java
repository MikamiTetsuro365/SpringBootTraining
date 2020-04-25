package com.example.demo2.dao;

import com.example.demo2.eintity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SurveyDaoImpl implements SurveyDao {

    //データベース処理のフレームワーク
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SurveyDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertServey(Survey survey) {
        jdbcTemplate.update("INSERT INTO survey(age, satisfaction, comment, created) VALUES(?, ?, ?, ?)",
                survey.getAge(),survey.getSatisfaction(),survey.getComment(), survey.getCreated());
    }

    @Override
    public List<Survey> getAll() {
        String sql = "SELECT id, age, satisfaction, comment, created FROM survey";
        List<Map<String, Object>> surveyList = jdbcTemplate.queryForList(sql);
        List<Survey> list = new ArrayList<>();
        //抽出
        for(Map<String, Object> result : surveyList){
            Survey survey = new Survey();
            survey.setId((int)result.get("id"));
            survey.setAge((int)result.get("age"));
            survey.setSatisfaction((int)result.get("satisfaction"));
            survey.setComment((String)result.get("comment"));
            survey.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(survey);
        }
        return list;
    }
}
