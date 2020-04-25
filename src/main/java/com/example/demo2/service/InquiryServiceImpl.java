package com.example.demo2.service;

import com.example.demo2.dao.InquiryDao;
import com.example.demo2.eintity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//DIでシングルトンとしてインスタンスとなる
@Service
public class InquiryServiceImpl implements InquiryService{

    //詳しい機能はDaoに任せている
    //これをコントローラが使う
    private final InquiryDao dao;

    @Autowired
    public InquiryServiceImpl(InquiryDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(Inquiry inquiry) {
        dao.insertInquiry(inquiry);
    }

    @Override
    public void update(Inquiry inquiry) {
        //更新できなければ
        if(dao.updateInquiry(inquiry) == 0){
            throw new InquiryNotFoundException("can't find the same ID");
        }
    }

    @Override
    public List<Inquiry> getAll() {
        return dao.getAll();
    }
}
