package com.example.laxmikant.databaseexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class ShowInformationActivity extends AppCompatActivity {
    Context context;
    RecyclerView recyclerview;
    List<EmployeeInfoModel> listModel;
    DatabaseService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        context = ShowInformationActivity.this;
        dbService = new DatabaseService(context);
        listModel =  dbService.getAllInfo();

        init();
    }

    private void init() {
        recyclerview = findViewById(R.id.infoRecycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(new InformationAdapter(context, listModel));
    }
}
