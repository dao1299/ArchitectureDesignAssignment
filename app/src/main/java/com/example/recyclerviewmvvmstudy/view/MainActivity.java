package com.example.recyclerviewmvvmstudy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.recyclerviewmvvmstudy.R;
import com.example.recyclerviewmvvmstudy.databinding.ActivityMainBinding;
import com.example.recyclerviewmvvmstudy.service.ServiceForeground;
import com.example.recyclerviewmvvmstudy.viewmodel.DataViewModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new DataViewModel(binding,this));
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
        Intent intent = new Intent(this, ServiceForeground.class);
        startService(intent);
    }
}