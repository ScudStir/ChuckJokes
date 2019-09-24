package ru.scudstir.chucknorrisjokes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IcndbAPI {
    @GET("/jokes/random/{number}")
    Call<Pojo> getJoke(@Path("number") String count);
}
