package com.seu.android_diabetes.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.seu.android_diabetes.HomeActivity;
import com.seu.android_diabetes.LoginActivity;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.UserVO;

public class UserCenterFragment extends Fragment {

    TextView info;
    Button logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.center_fragment,container,false);
         info=view.findViewById(R.id.info);
        logout=view.findViewById(R.id.logout);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //判断用户是否登录
       Boolean isLogin= SharedPreferencesUtil.getInstance(getActivity()).readBoolean("isLogin");
       if(isLogin){
           //已经登录
           //获取用户信息
           UserVO userVO=(UserVO) SharedPreferencesUtil.getInstance(getActivity()).readObject("user", UserVO.class);
           info.setText(userVO.getUsername());
       }


       //退出登录
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
