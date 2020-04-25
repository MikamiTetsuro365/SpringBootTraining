package com.example.demo2.app.inquiry;

import com.example.demo2.eintity.Inquiry;
import com.example.demo2.service.InquiryNotFoundException;
import com.example.demo2.service.InquiryService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    //inquiryServiceの機能を使う
    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    //問い合わせ処理をする
    @GetMapping
    public String index(Model model){
        List<Inquiry> list = inquiryService.getAll();

        //例外チェック用
//        Inquiry inquiry = new Inquiry();
//        inquiry.setId(4);
//        inquiry.setName("七海");
//        inquiry.setEmail("sample@gmail.com");
//        inquiry.setContents("へろー");
//        inquiryService.update(inquiry);

        //検索に失敗すると例外がthrowされるはず
//        try{
//            inquiryService.update(inquiry);
//        }catch (InquiryNotFoundException e){
//            //更新に失敗
//            model.addAttribute("message", e);
//            return "error/CustamPage";
//        }

        model.addAttribute("inquiryList", list);
        model.addAttribute("title","お問い合わせ内容");

        return "inquiryFolder/newindex";
    }


    //フラッシュスコープの結果を受け取る
    //フラッシュスコープの結果をレンダリングできる
    //@ModelAttribute("complete") String complete
    @GetMapping("/form")
    public String form(InquiryForm inquiryForm, Model model,
                       @ModelAttribute("complete") String complete ){
        model.addAttribute("title", "お問い合わせフォーム");
        return "inquiryFolder/form";
    }

    //戻るボタンを押したらポストが帰るのでこれが実行される
    @PostMapping("/form")
    public String formGoBack(InquiryForm inquiryForm, Model model){
        model.addAttribute("title", "お問い合わせフォーム");
        return "inquiryFolder/form";
    }

    //バリデーション
    @PostMapping("/confirm")
    //ValidatedでNotNullなどのチェックをする，その結果がBindingResultで帰ってくる
    public String confirm(@Validated InquiryForm inquiryForm, BindingResult result, Model model){
        if(result.hasErrors()==true){
            //Nullやemailの形式を満たしていなかったら
            model.addAttribute("title", "お問い合わせフォーム");
            return "inquiryFolder/form";
        }
        model.addAttribute("title","ちゃんと確認しろよ");
        return "inquiryFolder/confirm";
    }

    //リダイレクト処理
    @PostMapping("/complete")
    public String complete(@Validated InquiryForm inquiryForm,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes){
        //クローム使うとデベロッパーツールで書き換えできる
        if(result.hasErrors()){
            model.addAttribute("title", "お問い合わせフォーム");
            return "inquiry/form";
        }

        //送信されたらデータベスに保存する
        //飛んできたデータをいったん保存する
        //テーブルが複数あったり開発中でテーブルが変わってしまうとくに有効らしい
        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setContents(inquiryForm.getContents());
        inquiry.setCreated(LocalDateTime.now());

        //データベースに登録
        inquiryService.save(inquiry);

        redirectAttributes.addFlashAttribute("complete", "送信完了");
        //URLを指している
        //リセットされる
        return "redirect:/inquiry/form";
    }

    //このコントローラ内だけで共通のエラー処理
//    @ExceptionHandler(InquiryNotFoundException.class)
//    public String handleException(InquiryNotFoundException e, Model model){
//        model.addAttribute("message", e);
//        return "error/CustamPage";
//    }

}
