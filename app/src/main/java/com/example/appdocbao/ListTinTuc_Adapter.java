package com.example.appdocbao;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListTinTuc_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ItemTinTuc> itemTinTucs;
    SQLHelperTinTuc sqlHelperTinTuc;

    public ListTinTuc_Adapter(Context context, ArrayList<ItemTinTuc> itemTinTucs) {
        this.context = context;
        this.itemTinTucs = itemTinTucs;
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

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v;
        if(i == 0){
            v = inflater.inflate(R.layout.item_tintuc_big, viewGroup, false);
        }else {
            v = inflater.inflate(R.layout.item_tintuc, viewGroup, false);
        }

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtPubDate = v.findViewById(R.id.txtPubDate);
        ImageView imageView = v.findViewById(R.id.imgTinTuc);

        ItemTinTuc itemTinTuc = itemTinTucs.get(i);
        txtTitle.setText(itemTinTuc.getTitle());
        txtPubDate.setText(itemTinTuc.getPubDate());
        Glide.with(context).load(itemTinTuc.getImg()).into(imageView);

        sqlHelperTinTuc = new SQLHelperTinTuc(context);
        return v;
    }
}
