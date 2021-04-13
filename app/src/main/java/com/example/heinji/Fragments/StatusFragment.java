package com.example.heinji.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heinji.Adapters.UserAdapter;
import com.example.heinji.Models.Users;
import com.example.heinji.R;
import com.example.heinji.databinding.FragmentChatsBinding;
import com.example.heinji.databinding.FragmentStatusBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StatusFragment extends Fragment {



    public StatusFragment() {
        // Required empty public constructor
    }

    FragmentStatusBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(inflater , container, false);



        return binding.getRoot();
    }
}