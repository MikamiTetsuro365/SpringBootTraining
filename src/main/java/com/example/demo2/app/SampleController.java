package com.example.demo2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

@Controller
@RequestMapping("/sample")
public class SampleController {

    //コントラクタインジェクション？
    //外にあるライブラリを指定するっていうのが下
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    //データベースをいじるために必要
    //自動でオプジェクトがインスタンス化されるので勝手に使える
    public  SampleController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // sample->testに送る
    @GetMapping("/test")
    public String test(Model model){
        //modelは勝手に送られてくる
        String sql = "SELECT id, name, email FROM inquiry WHERE id = 1";
        //Stringはカラム名前，sqlを飛ばして結果を受け取れる
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        //これでデータを送る key, data
        //返り血はオブジェクトだが，Stringに勝手に変換される
        model.addAttribute("title", "hello");

        //sqlの内容を飛ばす
        model.addAttribute("name", map.get("name"));
        model.addAttribute("email",map.get("email"));

        //testをレンダリング
        return "test";
    }


}
