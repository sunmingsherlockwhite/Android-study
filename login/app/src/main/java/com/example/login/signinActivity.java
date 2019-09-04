package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.Utils.MD5Utils;

public class signinActivity extends AppCompatActivity {
//    1. 获取界面上的控件
//    2. button的点击事件的监听
//    3. 处理点击事件
//    3.1 获取控件的值
//    3.2 检查数据的有效性
//    3.3 将注册信息储存
//    3.4 跳转到登录页面
    private EditText etUsername, etPassword, etPwdAgain;
    private Button btnRegister;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

//        1. 获取界面上的控件
        initToolbar();
        initView();
//        2. button的点击事件的监听
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                3.1 获取控件的值
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String pwdAgain = etPwdAgain.getText().toString();
//                3.2 检查数据的有效性
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(signinActivity.this,"用户名不能为空",
                            Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password) || TextUtils.isEmpty(pwdAgain)){
                    Toast.makeText(signinActivity.this,"密码不能为空",
                            Toast.LENGTH_SHORT).show();
                }else if(!password.equals(pwdAgain)){
                    Toast.makeText(signinActivity.this,"两次密码必须一致",
                            Toast.LENGTH_SHORT).show();
                }else{
                    savePref(username, MD5Utils.md5(password));
                    Intent intent = new Intent(signinActivity.this,loginActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("注册");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinActivity.this.finish();
            }
        });
    }

    private void savePref(String username, String password) {
        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.apply();
    }

    private void initView(){
        etUsername = findViewById(R.id.username_edit);
        etPassword = findViewById(R.id.userpassword_edit);
        etPwdAgain = findViewById(R.id.userpassword_edit1);
        btnRegister = findViewById(R.id.login_button);
    }
}
