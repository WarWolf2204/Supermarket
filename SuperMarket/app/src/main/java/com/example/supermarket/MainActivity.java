package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText password;
    Button   Login;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE);
            loadData();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onValidateForm()){
                    String username = name.getText().toString().trim();
                    String userpassword = password.getText().toString().trim();
                    if (username.equals("admin") && userpassword.equals("admin")){
                        Toast.makeText(MainActivity.this,"Login Success!",Toast.LENGTH_LONG).show();
                        if (checkBox.isChecked())
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan",username);
                            editor.putString("matkhau",userpassword);
                            editor.putBoolean("check",true);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this,ListFood.class);
                            startActivity(intent);
                        }
                        else
                        {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("check");
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this,ListFood.class);
                            startActivity(intent);
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login Fail!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private boolean onValidateForm() {
        if(name.getText().toString().length() < 1){
            name.setError("Please fill username");
            return false;
        }
        if(password.getText().toString().length() < 1){
            password.setError("Please fill password");
            return false;
        }
        return  true;
    }
    private void initialization() {
        name = findViewById(R.id.edName);
        password = findViewById(R.id.edPassword);
        Login = findViewById(R.id.btLogin);
        checkBox = findViewById(R.id.remember);
    }
    private void loadData() {
        if(sharedPreferences.getBoolean("check",false)) {
            name.setText(sharedPreferences.getString("taikhoan",""));
            password.setText(sharedPreferences.getString("matkhau", ""));
            checkBox.setChecked(true);
            startActivity(new Intent(MainActivity.this,ListFood.class));

        }
        else
           checkBox.setChecked(false);

    }
}
