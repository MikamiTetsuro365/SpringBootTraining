package com.example.demo2.dao;

import com.example.demo2.eintity.Inquiry;

import java.util.List;

public interface InquiryDao {

    void insertInquiry(Inquiry inquiry);

    int updateInquiry(Inquiry inquiry);

    List<Inquiry> getAll();

}
