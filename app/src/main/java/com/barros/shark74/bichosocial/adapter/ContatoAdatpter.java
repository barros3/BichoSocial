package com.barros.shark74.bichosocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barros.shark74.bichosocial.R;
import com.barros.shark74.bichosocial.model.NavDrawerItem;
import com.barros.shark74.bichosocial.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shark74 on 22/04/16.
 */
public class ContatoAdatpter extends RecyclerView.Adapter<ContatoAdatpter.MyViewHolder> {

    private List<User> data;
    private LayoutInflater inflater;
    private Context context;

    public ContatoAdatpter(Context context, List<User> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

//    public ContatoAdatpter(User[] mDataset) {
//
//    }

    public ContatoAdatpter(List<User> userList){
        this.data = userList;
    }

//    public void delete(int position) {
//        data.remove(position);
//        notifyItemRemoved(position);
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_friends, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        listaUserView();
//        User current = data[position];
        User user = data.get(position);
        holder.userName.setText(user.getNome());
        holder.userEmail.setText(user.getEmail());
        holder.userTelefone.setText(user.getTelefone());
        holder.itemView.setTag(user);
//        holder.userName.setText(current.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView userEmail;
        TextView userTelefone;

        public MyViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userEmail = (TextView) itemView.findViewById(R.id.userEmail);
            userTelefone = (TextView) itemView.findViewById(R.id.userTelefone);
        }
    }
}