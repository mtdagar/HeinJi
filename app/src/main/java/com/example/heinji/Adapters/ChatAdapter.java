package com.example.heinji.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heinji.Models.Messages;
import com.example.heinji.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<Messages> messagesModel;
    Context context;
    String recId;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Messages> messagesModel, Context context) {
        this.messagesModel = messagesModel;
        this.context = context;
    }

    public ChatAdapter(ArrayList<Messages> messagesModel, Context context, String recId) {
        this.messagesModel = messagesModel;
        this.context = context;
        this.recId = recId;
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to unsend this message?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //delete message from firebase
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid() + recId;
                                database.getReference().child("chats")
                                        .child(senderRoom)
                                        .child(messageModel.getMessageId())
                                        .setValue(null);
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return false;
            }
        });


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
