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

public class Movie {
    public static class ApiResult{
        @SerializedName("Title")
        public String title;

        @SerializedName("Year")
        public String year;

        @SerializedName("Actors")
        public String actors;

        @SerializedName("Genre")
        public String genre;

        @SerializedName("imdbRating")
        public String imdb;
    }

    public interface MovieService{
        @GET("/?apikey=a38efdf2")
        Call<Movie.ApiResult> getResult(@Query("t") String title);
    }
    public static void get(String title, final Consumer<String> callback){
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<Movie.ApiResult> call = retrofit
                .create(Movie.MovieService.class)
                .getResult(title);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ApiResult apiResult = response.body();
                String result = "Фильм: "+apiResult.title+
                        "\nЖанр: "+apiResult.genre+
                        "\nГод: "+ apiResult.year+
                        "\nСписок актёров: "+ apiResult.actors+
                        "\nРейтинг: "+apiResult.imdb;
                callback.accept(result);
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {

            }
        });

    }
}
