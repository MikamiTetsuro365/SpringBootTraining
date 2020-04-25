package com.example.demo2.service;

import com.example.demo2.eintity.Inquiry;
import java.util.List;

public interface InquiryService {

    void save(Inquiry inquiry);

    void update(Inquiry inquiry);

    List<Inquiry> getAll();

}
