package com.example.demo2.dao;

import com.example.demo2.eintity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//データベースを保存したり値を受け取って検索したりSQLをするクラス
//サービスクラスから操作される
//データベースを操作するクラスであることをDIコンテナに伝える
@Repository
public class InquiryDaoImpl implements InquiryDao {

    private final JdbcTemplate jdbcTemplate;

    //DIから買ってにjdbcTemplateがnewされる
    @Autowired
    public InquiryDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertInquiry(Inquiry inquiry) {
        //SQLインジェクションを防げる書き方
        jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
                inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
    }

    @Override
    public int updateInquiry(Inquiry inquiry) {
        //アップデート成功したら１が，失敗したら０が帰る
        return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?, contents = ? WHERE id = ?",
                inquiry.getName(),inquiry.getEmail(),inquiry.getContents(),inquiry.getId());
    }

    @Override
    public List<Inquiry> getAll() {
        String sql = "SELECT id, name, email, contents, created FROM inquiry";
        List<Map<String, Object> > resultList = jdbcTemplate.queryForList(sql);
        //Mapの中身を詰め直す
        List<Inquiry> list = new ArrayList<Inquiry>();
        for(Map<String, Object> result : resultList){
            Inquiry inquiry = new Inquiry();
            //object型なので良い感じにキャストが必要
            //IDはAutoIncrementされてる故．．．
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String)result.get("name"));
            inquiry.setEmail((String)result.get("email"));
            inquiry.setContents((String)result.get("contents"));
            //sqlではTimstamp型で戻ってきているのでinquiryの方と整合とるためにtoLocalDateTimeに変換
            inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(inquiry);
        }
        return list;
    }
}
