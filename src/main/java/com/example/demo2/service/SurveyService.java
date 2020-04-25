package com.example.demo2.service;

import com.example.demo2.eintity.Survey;
import java.util.List;

public interface SurveyService {
    void save(Survey survey);
    List<Survey> getAll();
}
