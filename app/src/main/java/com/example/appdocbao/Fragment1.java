package com.example.appdocbao;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    SQLHelperTinTuc sqlHelperTinTuc;
    ListView listView;
    ListTinTuc_Adapter listTinTuc_adapter;
    ArrayList<ItemTinTuc> itemTinTucs;
    Data data;

    public interface Data{
        void senData(String link);
    }

    private static final String TAG = "Fragment1";

    public Fragment1(ArrayList<ItemTinTuc> itemTinTucs) {
        this.itemTinTucs = itemTinTucs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        listView = view.findViewById(R.id.lvTinTuc);
        sqlHelperTinTuc = new SQLHelperTinTuc(getContext());

        listTinTuc_adapter = new ListTinTuc_Adapter(getContext(), itemTinTucs);
        listView.setAdapter(listTinTuc_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sqlHelperTinTuc.insertNewHistory(itemTinTucs.get(i).getTitle(), itemTinTucs.get(i).getImg(), itemTinTucs.get(i).getCmt(), itemTinTucs.get(i).getPubDate());
                data.senData(itemTinTucs.get(i).getCmt());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Data){
            data = (Data) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        data = null;
    }
}
