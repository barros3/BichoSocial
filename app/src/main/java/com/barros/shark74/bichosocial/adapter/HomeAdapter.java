package com.barros.shark74.bichosocial.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barros.shark74.bichosocial.R;
import com.barros.shark74.bichosocial.model.FeedNoticia;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by shark74 on 03/05/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<FeedNoticia> item;
    private Activity context;


    public HomeAdapter(Activity context, List<FeedNoticia> items) {
        this.item = items;
        this.context = context;
    }

    public HomeAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viwViewHolder, int position) {
        FeedNoticia object = item.get(position);
        viwViewHolder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView imagem;
        private TextView descricao;

        public ViewHolder(View itemViem) {
            super(itemViem);
            imagem = (TextView) itemView.findViewById(R.id.feedImg);
            descricao = (TextView) itemView.findViewById(R.id.feedText);
        }

    }
}
