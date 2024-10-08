package com.seu.android_diabetes;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.imageview.IconImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 登录UI
 * */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialEditText username_editText ;
    MaterialEditText password_editText;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    String character;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        SharedPreferences sharedPreferences =this.getSharedPreferences("logininfo", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("logininfo",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("pssword","");


        IconImageView iconImageView = findViewById(R.id.login_icon);
        iconImageView.setImageResource(R.mipmap.app_icon);





        username_editText=findViewById(R.id.et_phone_number);
        password_editText=findViewById(R.id.et_verify_code);

        username_editText.setText(username);
        password_editText.setText(password);

        Button login_button=(Button)findViewById(R.id.login);
        radioButton1 = findViewById(R.id.patient);
        radioButton2 = findViewById(R.id.doctor);
        radioButton3 = findViewById(R.id.sports_doctor);
        radioButton4 = findViewById(R.id.manager);
        radioGroup = findViewById(R.id.rg_login);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioButton1.isChecked()) {
                    character = "1";
                }
                if(radioButton2.isChecked()) {
                    character = "2";
                }
                if(radioButton3.isChecked()) {
                    character = "3";
                }
                if(radioButton4.isChecked()) {
                    character = "4";
                }

            }
        });
        //注册点击事件
        login_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case  R.id.login:
                 if(character==null){
                     Toast.makeText(this,"您的角色还没有选择",Toast.LENGTH_LONG).show();
                     break;
                 }
                 //获取用户名
                 String username=username_editText.getText().toString();
                 //获取密码
                 String password=password_editText.getText().toString();
                 //保存用户名密码下次登录免输入
                 SharedPreferences sharedPreferences =this.getSharedPreferences("logininfo", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.clear().commit();
                 editor.putString("username",username);
                 editor.putString("password",password);
                 editor.commit();


                 //请求接口  ->  okhttp
                 OkHttpUtils.get("http://"+serverIp+":8080/portal/user/login.do/?username="+username+"&password="+password,
                     new OkHttpCallback(){
                         @Override
                         public void onFinish(String status, String msg) {
                             super.onFinish(status, msg);
                             //解析数据
                             Gson gson=new Gson();
                             //
                             ServerResponse<UserVO> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<UserVO>>(){}.getType());
                             int status1=serverResponse.getStatus();
                             if(status1==0){//登录成功
                                 //保存用户信息
                             SharedPreferences sharedPreferences= LoginActivity.this.getSharedPreferences("userinfo",MODE_PRIVATE);
                             SharedPreferences.Editor editor= sharedPreferences.edit();
                             editor.putBoolean("isLogin",true);
                             editor.putString("user",msg);
                             editor.commit();

                             UserVO userVO = serverResponse.getData();
                             Log.d("userinfo",userVO.getUsername());

//
                            SharedPreferencesUtil util= SharedPreferencesUtil.getInstance(LoginActivity.this);
                            util.delete("isLogin");
                            util.delete("user");
//
                            util.putBoolean("isLogin",true);
                            util.putString("user",gson.toJson(serverResponse.getData()));
                            Boolean isLogin= util.readBoolean("isLogin");


                                 //Activity跳转

                                 Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                 intent.putExtra("character",character);
                                 String a = character;
                                 startActivity(intent);

                                 Looper.prepare();
                                 Toast.makeText(LoginActivity.this,isLogin+"",Toast.LENGTH_LONG).show();
                                 Looper.loop();
//

//
                             }else{
                                 Looper.prepare();
                                 Toast.makeText(LoginActivity.this,serverResponse.getMsg(),Toast.LENGTH_LONG).show();
                                 Looper.loop();
                             }

                         }
                 });
                 //解析接口返回的数据


        }
    }

}

