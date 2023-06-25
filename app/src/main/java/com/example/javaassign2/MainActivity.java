package com.example.javaassign2;

//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
////public class MainActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////    }
////}
//
//
//
//public class MainActivity extends AppCompatActivity {
//    private EditText etName;
//    private EditText etEmail;
//    private Button btnSave;
//    private RecyclerView recyclerView;
//    private FormDataAdapter adapter;
//    private List<FormData> formDataList;
//
//    private static final String PREFS_NAME = "MyPrefsFile";
//    private static final String PREFS_KEY = "form_data";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        etName = findViewById(R.id.etName);
//        etEmail = findViewById(R.id.etEmail);
//        btnSave = findViewById(R.id.btnSave);
//        recyclerView = findViewById(R.id.recyclerView);
//
//        formDataList = new ArrayList<>();
//        adapter = new FormDataAdapter(formDataList);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//
//        loadData();
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = etName.getText().toString();
//                String email = etEmail.getText().toString();
//
//                FormData formData = new FormData(name, email);
//                formDataList.add(formData);
//                adapter.notifyDataSetChanged();
//
//                saveData();
//
//                etName.setText("");
//                etEmail.setText("");
//            }
//        });
//    }
//
//    private void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(PREFS_KEY, FormDataUtils.convertToJson(formDataList));
//        editor.apply();
//    }
//
//    private void loadData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        String jsonData = sharedPreferences.getString(PREFS_KEY, null);
//
//        if (jsonData != null) {
//            formDataList.addAll(FormDataUtils.convertFromJson(jsonData));
//            adapter.notifyDataSetChanged();
//        }
//    }
//}




import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private Button btnSave;
    private RecyclerView recyclerView;
    private FormDataAdapter adapter;
    private List<FormData> formDataList;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREFS_KEY = "form_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        recyclerView = findViewById(R.id.recyclerView);

        formDataList = new ArrayList<>();
        adapter = new FormDataAdapter(formDataList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();

                if (isValidName(name) && isValidEmail(email)) {
                    FormData formData = new FormData(name, email);
                    formDataList.add(formData);
                    adapter.notifyDataSetChanged();

                    saveData();

                    etName.setText("");
                    etEmail.setText("");
                } else {
                    // Invalid name or email
                    // Show error message or handle accordingly
                }
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_KEY, FormDataUtils.convertToJson(formDataList));
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonData = sharedPreferences.getString(PREFS_KEY, null);

        if (jsonData != null) {
            formDataList.addAll(FormDataUtils.convertFromJson(jsonData));
            adapter.notifyDataSetChanged();
        }
    }

    private boolean isValidName(String name) {
        // Modify the regular expression for name validation according to your requirements
        String regex = "^[A-Za-z\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        // Modify the regular expression for email validation according to your requirements
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

