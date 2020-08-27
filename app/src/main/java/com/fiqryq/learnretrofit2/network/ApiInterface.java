package com.fiqryq.learnretrofit2.network;

import com.fiqryq.learnretrofit2.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<Post>> getPosts();

}
