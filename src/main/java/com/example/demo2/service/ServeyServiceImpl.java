package com.example.demo2.service;

import com.example.demo2.dao.SurveyDao;
import com.example.demo2.eintity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


//ここにデータベースから得た情報を使って何らかの具体的な処理をかく
@Service
public class ServeyServiceImpl implements SurveyService{

    private final SurveyDao surveyDao;
    @Autowired
    public ServeyServiceImpl(SurveyDao surveyDao){
        this.surveyDao = surveyDao;
    }

    @Override
    public void save(Survey survey) {
        surveyDao.insertServey(survey);
    }

    @Override
    public List<Survey> getAll() {
        return surveyDao.getAll();
    }
}
