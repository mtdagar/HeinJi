package com.example.heinji.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.heinji.Adapters.UserAdapter;
import com.example.heinji.Models.Users;
import com.example.heinji.R;
import com.example.heinji.databinding.FragmentChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {


    public ChatsFragment() {
        // Required empty public constructor
    }

    FragmentChatsBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChatsBinding.inflate(inflater, container, false);


        database = FirebaseDatabase.getInstance();
        UserAdapter adapter = new UserAdapter(list, getContext());
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    users.getUserID(dataSnapshot.getKey());
                    list.add(users);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();

    }
}