package com.example.demo2.app.survay;

import com.example.demo2.eintity.Survey;
import com.example.demo2.service.ServeyServiceImpl;
import com.example.demo2.service.SurveyService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/survey")
public class SurveyController {

    //surveyにアクセスされたらとりあえずデータベースを表示させる
    private final SurveyService surveyService;
    @Autowired
    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("surveyList", surveyService.getAll());
        model.addAttribute("title", "満足度調査一覧");
        return "surveyFolder/index";
    }



    //アンケートフォームが開かれたときの挙動
    //リダイレクトされた時はリダイレクト結果がmodeに渡る
    @GetMapping("/surveyform")
    public String form(SurveyForm surveyForm, Model model, @ModelAttribute("complete") String complete){
        model.addAttribute("title", "満足度回答フォーム");
        return "surveyFolder/surveyform";
    }

    //戻るボタンを押されたときの挙動
    @PostMapping("/surveyform")
    public String gpBackForm(SurveyForm surveyForm, Model model){
        model.addAttribute("tittle","満足度回答フォーム");
        return "surveyFolder/surveyform";
    }

    //送信が押されたとき，バリデーションチェックを抜けたら確認画面へ
    @PostMapping("/confirm")
    public String conf(@Validated SurveyForm surveyForm, BindingResult result, Model model){
        //バリデーションチェック
        if(result.hasErrors()){
            model.addAttribute("title", "もう一度入力してください");
            return "surveyFolder/surveyform";
        }
        model.addAttribute("title","確認しろよ");
        return "surveyFolder/surveyconfirm";
    }

    //確認画面から送信を確定させた後はアンケートフォームへリダイレクトする
    @PostMapping("/complete")
    public String completeForm(@Validated SurveyForm surveyForm,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes){
        //バリデーションチェック
        if(result.hasErrors() == true){
            model.addAttribute("title","不正な処理が行われました．");
            return "surveyFolder/surveyform";
        }

        //登録
        Survey survey = new Survey();
        survey.setAge(surveyForm.getAge());
        survey.setSatisfaction(surveyForm.getSatisfaction());
        survey.setComment(surveyForm.getComment());
        survey.setCreated(LocalDateTime.now());
        surveyService.save(survey);

        redirectAttributes.addFlashAttribute("complete", "アンケート送信完了");
        return "redirect:/survey/surveyform";
    }

}
