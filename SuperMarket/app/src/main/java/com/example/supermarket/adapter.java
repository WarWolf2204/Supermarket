package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class adapter extends ArrayAdapter<Model> {
    private Context mContext;
    private int mResourse;
    List<Model> modelList;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = sdf.format(calendar.getTime());
    public adapter(Context context, int resource, List<Model> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourse = resource;
        this.modelList = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(mResourse,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.ThoiGian = convertView.findViewById(R.id.Time);
            viewHolder.Monan = convertView.findViewById(R.id.monan);
            viewHolder.Giatien = convertView.findViewById(R.id.menhgia);
            viewHolder.IVadd = convertView.findViewById(R.id.ivadd);
            viewHolder.btnDelete = convertView.findViewById(R.id.delete);
            viewHolder.kiemtra = convertView.findViewById(R.id.check);

        }
        else
        {
           viewHolder = (ViewHolder)convertView.getTag();
        }
        final Model themodel = modelList.get(position);
        viewHolder.Monan.setText(String.valueOf(themodel.getTenmon()));
        viewHolder.ThoiGian.setText(String.valueOf(currentDate));
        viewHolder.Giatien.setText(String.valueOf(themodel.getGiatien()));
        viewHolder.kiemtra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                Toast.makeText(buttonView.getContext(), buttonView.getText()+"",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(themodel);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public class ViewHolder
    {
        TextView ThoiGian;
        TextView Monan;
        TextView Giatien;
        Button btnDelete;
        Button btnUpdate;
        ImageView IVadd;
        CheckBox kiemtra;
    }
}
