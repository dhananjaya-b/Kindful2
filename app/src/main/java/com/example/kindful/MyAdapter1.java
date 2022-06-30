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

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    Context context;
    ArrayList<model> list;
    public MyAdapter1(Context context, ArrayList<model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        model user = list.get(position);
        System.out.println(position+"pos");
        holder.firstName.setText(user.getFullname());
        holder.lastName.setText(user.getDetails());
        holder.age.setText(user.getAmount());
        holder.firstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 System.out.println(user.getFullname().toString());
                System.out.println(user.getDetails().toString());
                System.out.println(user.getAmount().toString());

// creating a intent
                Intent intent = new Intent(view.getContext(),Donate.class);

// creating a bundle object
                Bundle bundle = new Bundle();
                bundle.putString("name", user.getFullname());
                bundle.putString("details", user.getDetails());
                bundle.putString("amount", user.getAmount());
                intent.putExtras(bundle);
                context.startActivity(intent);
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.list_item,new descfragment(user.getFullname(),user.getDetails(),user.getAmount())).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        System.out.println(list.size()+"size");
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, lastName, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.fullname);
            lastName = itemView.findViewById(R.id.deatils);
            age = itemView.findViewById(R.id.amount);

        }
    }

}