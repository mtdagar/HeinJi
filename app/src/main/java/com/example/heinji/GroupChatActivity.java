package com.example.heinji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.heinji.databinding.ActivityChatDetailBinding;

public class GroupChatActivity extends AppCompatActivity {


    ActivityChatDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}