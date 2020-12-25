package com.example.appdocbao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListSearch_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ItemTinTuc> itemTinTucs;
    ArrayList<ItemTinTuc> listSearch;
    SQLHelperTinTuc sqlHelperTinTuc;


    public ListSearch_Adapter(Context context, ArrayList<ItemTinTuc> itemTinTucs) {
        this.context = context;
        this.itemTinTucs = itemTinTucs;
        this.listSearch = new ArrayList<>();
        listSearch.addAll(itemTinTucs);
    }

    @Override
    public int getCount() {
        return itemTinTucs.size();
    }

    @Override
    public Object getItem(int i) {
        return itemTinTucs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void filter(String query){
        itemTinTucs.clear();
        if(query.length() == 0){
            itemTinTucs.addAll(listSearch);
        }else {
            for (ItemTinTuc itemTinTuc: listSearch){
                if(itemTinTuc.getTitle().toLowerCase().contains(query.toLowerCase())){
                    itemTinTucs.add(itemTinTuc);
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.item_tintuc, viewGroup, false);

        RelativeLayout rt = v.findViewById(R.id.rt_lt);
        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtPubDate = v.findViewById(R.id.txtPubDate);
        ImageView imageView = v.findViewById(R.id.imgTinTuc);

        final ItemTinTuc itemTinTuc = itemTinTucs.get(i);
        txtTitle.setText(itemTinTuc.getTitle());
        txtPubDate.setText(itemTinTuc.getPubDate());
        Glide.with(context).load(itemTinTuc.getImg()).into(imageView);
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(),ChiTietTinTuc.class);
                intent.putExtra("link", itemTinTuc.getCmt());
                v.getContext().startActivity(intent);
            }
        });

        return v;
    }
}
