package com.example.ss7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.ss7.adapter.ItemAdapter;
import com.example.ss7.model.Item;
import com.example.ss7.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListNews;
    List<Item> listData;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //B1: Data source
        CallApi();
        //B2: Adapter
        listData= new ArrayList<>();
        itemAdapter = new ItemAdapter(MainActivity.this,listData);
        //B3: Layout Manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //B4: RecyclerView
        rvListNews = findViewById(R.id.rvListNews);
        rvListNews.setLayoutManager(layoutManager);
        rvListNews.setAdapter(itemAdapter);
    }

    public void CallApi(){
        Retrofit retrofit = new  Retrofit.Builder().baseUrl(ApiManager.DOMAIN).addConverterFactory(GsonConverterFactory.create()).build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.getListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.body() !=null){
                    listData = response.body();
                    itemAdapter.reloadData(listData);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }
}