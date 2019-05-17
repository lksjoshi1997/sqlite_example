package com.example.laxmikant.databaseexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    Context context;
    EditText etName, etAge, etCompany, etRole;
    Button btn, showBtn;
    DatabaseService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        dbService = new DatabaseService(context);
        
        init();
    }

    private void init() {
        etName = findViewById(R.id.name);
        etAge = findViewById(R.id.age);
        etCompany = findViewById(R.id.company);
        etRole = findViewById(R.id.role);
        btn = findViewById(R.id.saveBtn);
        showBtn = findViewById(R.id.show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployeeInfo();
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { navigateToShowInformationActivity();
            }
        });
    }

    private void navigateToShowInformationActivity() {
        Intent intent = new Intent(context, ShowInformationActivity.class);
        startActivity(intent);
    }

    private void addEmployeeInfo() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String company = etCompany.getText().toString();
        String role = etRole.getText().toString();
        EmployeeInfoModel infoModel = new EmployeeInfoModel(name, age, company, role);
        dbService.addInformationToData(infoModel);
    }


}
