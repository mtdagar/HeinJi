package com.example.heinji.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heinji.Models.Messages;
import com.example.heinji.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<Messages> messagesModel;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Messages> messagesModel, Context context) {
        this.messagesModel = messagesModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender , parent , false);
            return new SenderViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever , parent , false);
            return new RecieverViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(messagesModel.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }else{
            return RECIEVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Messages messageModel = messagesModel.get(position);

        if (holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMssg.setText(messageModel.getMessage());
        }else{
            ((RecieverViewHolder)holder).recieverMssg.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messagesModel.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView recieverMssg , recieverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieverMssg = itemView.findViewById(R.id.recieverText);
            recieverTime = itemView.findViewById(R.id.recieverTime);

        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView senderMssg , senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMssg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);

        }
    }

}
