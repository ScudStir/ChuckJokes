package ru.scudstir.chucknorrisjokes;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button searchButton;
    private Button webButton;
    private String count;
    private RecyclerView jokesList;
    private JokeAdapter jokeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webButton = findViewById(R.id.b_web);
        webButton.setOnClickListener(webListener);

        searchButton = findViewById(R.id.b_search);
        searchButton.setOnClickListener(searchListener);

        jokesList = findViewById(R.id.rv_jokes);

        jokeAdapter = new JokeAdapter(this, Collections.<Value>emptyList());
        jokesList.setAdapter(jokeAdapter);
        jokesList.setLayoutManager(new LinearLayoutManager(this));

    }

    View.OnClickListener webListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, WebActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText = findViewById(R.id.et_search_field);
            count = editText.getText().toString();
            fetchJSON();
        }
    };

    private void fetchJSON() {
        NetworkService.getInstance()
                .getJSONApi()
                .getJoke(count)
                .enqueue(new Callback<Pojo>() {
                             @Override
                             public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                                 if (response.isSuccessful()) {
                                     if (response.body() != null) {
                                         Pojo jsonresponse = response.body();
                                         writeRecycler(jsonresponse);
                                     }
                                 }

                             }

                             @Override
                             public void onFailure(Call<Pojo> call, Throwable t) {

                                 t.printStackTrace();

                             }
                         }
                );

    }

    private void writeRecycler(Pojo pojo) {
        jokeAdapter.setJokesList(pojo.getValue());
        jokeAdapter.notifyDataSetChanged();
    }
}

