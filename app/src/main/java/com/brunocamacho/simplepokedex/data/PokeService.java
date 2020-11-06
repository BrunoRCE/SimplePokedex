package com.brunocamacho.simplepokedex.data;

import com.brunocamacho.simplepokedex.data.model.PokeData;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface PokeService {

    @GET("pokemon")
    Call<PokeData> getData(@Query("limit") int limit);
}
