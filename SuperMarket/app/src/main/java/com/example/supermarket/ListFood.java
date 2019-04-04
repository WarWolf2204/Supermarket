package com.example.supermarket;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListFood extends AppCompatActivity {
    ListView listview;
    ImageView add;
    List<Model> modelList;
    adapter adapter;
    EditText tenmonan,giamonan,sotiendu;
    TextView Time,showtien;
    Button Add,cancel,pay;
    ImageView thoigian;
    String time,food,cost,weight;
    Spinner spinner;
    SharedPreferences sharedPreferences;
    public static  final String mycost ="mycost";
    public static  final String total_cost ="total_cost";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        onIT();

        My_SQLite my_sqLite = new My_SQLite(ListFood.this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShowDialog();
            }
        });
        getdata();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Cost = Integer.parseInt(sotiendu.getText().toString());
                savedata(Cost);
                getdata();
            }
        });

        adapter = new adapter(ListFood.this,R.layout.listfood_item,modelList);
        adapter.notifyDataSetChanged();

    }
    private void ShowDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_update_context);
        Time = dialog.findViewById(R.id.tv_Data_time);
        tenmonan =dialog.findViewById(R.id.tenmon);
        giamonan = dialog.findViewById(R.id.giatien);
        thoigian = dialog.findViewById(R.id.btn_time);
        Add = dialog.findViewById(R.id.btn_add);
        cancel = dialog.findViewById(R.id.btn_cancel);
        spinner = dialog.findViewById(R.id.khoiluong);


        thoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int Year = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DATE);

                DatePickerDialog dialogs = new DatePickerDialog(ListFood.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Time.setText(format.format(calendar.getTime()));

                    }
                },Year,Month,Day);


                time = Time.getText().toString();
                dialogs.show();


            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                food = tenmonan.getText().toString();
                cost = giamonan.getText().toString();
                My_SQLite my_sqLite = new My_SQLite(ListFood.this);
                Toast.makeText(ListFood.this, food, Toast.LENGTH_SHORT).show();

                modelList = my_sqLite.getALL();
                modelList.clear();
                Model smodel = new Model(food,cost,time);
                my_sqLite.add(smodel);
                modelList.addAll(my_sqLite.getALL());

                adapter = new adapter(ListFood.this,R.layout.listfood_item,modelList);
                listview.setAdapter(adapter);

                tenmonan.setText("");
                giamonan.setText("");
                Time.setText("");

                adapter.notifyDataSetChanged();
            }
        });

       cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
    private void onIT() {
        listview = findViewById(R.id.listfood);
        add = findViewById(R.id.ivadd);
        pay = findViewById(R.id.tinhtien);
        showtien = findViewById(R.id.hienthitien);
        sotiendu = findViewById(R.id.sodu);
    }
    private void savedata(int cost)
    {
        sharedPreferences = getSharedPreferences(mycost, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(total_cost,cost);
        editor.apply();
    }
    private void getdata()
    {
        sharedPreferences = getSharedPreferences(mycost,Context.MODE_PRIVATE);
        showtien.setText(sharedPreferences.getInt(total_cost,0)+"");
    }

}
