package com.example.kindful;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserTrasactionAdapter extends RecyclerView.Adapter<UserTrasactionAdapter.MyViewHolder>{

        Context context;
        ArrayList<TransactionModel> list;
        public UserTrasactionAdapter(UserTransaction context, ArrayList<TransactionModel> list) {
            this.context=context;
            this.list=list;
        }

    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View v= LayoutInflater.from(context).inflate(R.layout.usertransaction,parent,false);
                return new MyViewHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
                TransactionModel user=list.get(position);
                System.out.println(position+"pos----------------------------------------");
                holder.firstName.setText(user.getRecieverName());
                holder.amount.setText(user.getAmount());
                holder.date.setText(user.getDate());

        }
        @Override
        public int getItemCount(){
                System.out.println(list.size()+"size-------------------------");
                return list.size();
                }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView firstName, amount, date;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                firstName = itemView.findViewById(R.id.name);
                    amount = itemView.findViewById(R.id.amount);
                    date = itemView.findViewById(R.id.date);

            }
        }
}
