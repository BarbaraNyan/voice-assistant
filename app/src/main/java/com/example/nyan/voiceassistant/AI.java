package com.example.nyan.voiceassistant;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NYAN on 22.04.2019.
 */

public class AI {
    @TargetApi(Build.VERSION_CODES.O)
    public static void getAnswer(String user_question, final Consumer<String> callback){

        Map <String,String> database = new HashMap<String, String>(){{
            put("привет", "Приветики!");
            put("как дела", "Прекрасно.");
            put("что делаешь", "Думаю о всяком...");
            put("как тебя зовут", "Я - голосовой помощник Китти.");
            put("кто тебя создал", "Мне нельзя называть её имя.");
            put("есть ли жизнь на Марсе", "Я долго думала над этим, но так ничего и не придумала.");
            put("почему", "Потому.");
            put("о чём","О всяком.");
            put("когда","Давай не сейчас.");
        }};
        user_question = user_question.toLowerCase();

        final ArrayList<String> answers = new ArrayList<>();
        int max_score = 0;
        String max_score_answer="Okay,nice";
        String [] split_user = user_question.split("\\s+");

        for(String database_question: database.keySet()){
            String [] split_db = database_question.split("\\s+");

            int score= 0;
            for(String word_user: split_user){
                for(String word_db: split_db){
                    int min_length = Math.min(word_db.length(), word_user.length());
                    int cut_length = (int) (min_length*0.7);
                    String word_user_cut = word_user.substring(0,cut_length);
                    String word_db_cut = word_db.substring(0,cut_length);
                    if(word_user_cut.equals(word_db_cut))
                        score++;
                }
            }
            if(score>max_score){
                max_score = score;
                max_score_answer = database.get(database_question);
            }
        }
        if(max_score>0){
            answers.add(max_score_answer);
        }

        Pattern cityPattern = Pattern.compile("какая погода в городе (\\p{L}+)",Pattern.CASE_INSENSITIVE);
        Matcher matcher = cityPattern.matcher(user_question);
        if(matcher.find()){
            String cityName = matcher.group(1);
            Weather.get(cityName, new Consumer<String>() {
                @Override
                public void accept(String s) {
                    answers.clear();
                    answers.add(s);
                    callback.accept(String.join(", ",answers));
                }
            });
        }
        else{
            if(answers.isEmpty()){
                callback.accept("Хорошо.");
                return;
            }
            callback.accept(String.join(", ",answers));
        }

    }
}
