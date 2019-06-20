package com.SalliBot.sallirootcreator.interfaces;

import com.SalliBot.sallirootcreator.pojo.SalliStatic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiStatic {
    @GET("/ere")
    Call<List<SalliStatic>> SalliStatics();
}
