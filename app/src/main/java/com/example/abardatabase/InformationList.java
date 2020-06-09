package com.example.abardatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class InformationList extends ArrayAdapter<Information> {
    private Activity context;
    private List<Information> informationList;
    public InformationList(Activity context,List<Information>informationList)
    {
        super(context,R.layout.list_layout,informationList);
        this.context=context;
        this.informationList=informationList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewitem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewname=(TextView)listViewitem.findViewById(R.id.textviewname);
        TextView textViewemail=(TextView)listViewitem.findViewById(R.id.textviewemail);
        TextView textViewaddress=(TextView)listViewitem.findViewById(R.id.textviewaddress);
        Information information=informationList.get(position);
        textViewname.setText(information.getName());
        textViewemail.setText(information.getEmail());
        textViewaddress.setText(information.getAddress());
        return listViewitem;
    }
}
