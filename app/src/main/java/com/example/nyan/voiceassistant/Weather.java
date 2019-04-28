package com.example.nyan.voiceassistant;

import com.google.gson.annotations.SerializedName;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NYAN on 28.04.2019.
 */

public class Weather {
    public static class Condition{
        @SerializedName("text")
        public String text;
    }
    public static class Forecast{
        @SerializedName("temp_c")
        public Float temperature;

        @SerializedName("condition")
        public Condition condition;
    }
    public static class ApiResult{
        @SerializedName("current")
        public Forecast current;
    }

    public interface WeatherService{
        @GET("/v1/current.json?key=2247a69abf0347c2b30103430192804")
        Call<ApiResult> getResult(@Query("q") String city, @Query("lang") String lang);
    }

    public static void get(String city, final Consumer<String> callback){
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://api.apixu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<ApiResult> call = retrofit
                .create(WeatherService.class)
                .getResult(city,"ru");
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ApiResult apiResult = response.body();
                String result = "Там сейчас " + apiResult.current.condition.text+
                        ", где-то "+ apiResult.current.temperature.intValue() +" градусов."; //.intValue();
                callback.accept(result);
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {

            }
        });
    }
}
