package com.fiqryq.learnretrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fiqryq.learnretrofit2.adapter.PostAdapter;
import com.fiqryq.learnretrofit2.model.Post;
import com.fiqryq.learnretrofit2.network.ApiInterface;
import com.fiqryq.learnretrofit2.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> arrayList;
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvPost);
        arrayList = new ArrayList<>();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        getData();
    }

    private void getData(){
        Call<List<Post>> call = apiInterface.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts){
                    arrayList.add(post);
                }
                setuprecyclerView(arrayList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
    private void setuprecyclerView(ArrayList<Post> arrayList){
        PostAdapter postAdapter = new PostAdapter(getApplicationContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(postAdapter);
    }
}