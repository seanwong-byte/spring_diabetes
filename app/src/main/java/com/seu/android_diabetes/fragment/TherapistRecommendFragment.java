package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.HomeActivity.DOCTORRECOMMENDRESULTFRAGMENT_TAG;
import static com.seu.android_diabetes.HomeActivity.THERAPISTRECOMMENDRESULTFRAGMENT_TAG;
import static com.seu.android_diabetes.utils.common.serverIp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.HomeActivity;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.adapter.DoctorRecommendAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class TherapistRecommendFragment extends Fragment {
    private ListView listView;
    private DoctorRecommendAdapter doctorRecommendAdapter;
    private static final int Doctor_Patient = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case Doctor_Patient:
                    ArrayList<DiabetesUser> diabetePatientList =(ArrayList<DiabetesUser>)msg.obj;
                    doctorRecommendAdapter = new DoctorRecommendAdapter(getActivity(), R.layout.doctor_listview_item,diabetePatientList);
                    listView.setAdapter(doctorRecommendAdapter);
                    //ListView item 的的点击事件
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            //获取当前item的patient id
                            Integer id = diabetePatientList.get(position).getId();
                            //fragment中获取当前活动类，再调用切换fragment的方法
                            HomeActivity homeActivity = (HomeActivity) getActivity();
                            Bundle bundle = new Bundle();
                            bundle.putString("patient id", id.toString());
                            homeActivity.attachFragment(THERAPISTRECOMMENDRESULTFRAGMENT_TAG,bundle);

                        }
                    });
                    break;

            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.doctor_recommend_preview_fragment,container,false);
        listView = view.findViewById(R.id.doctor_recommend_preview_listview);


        SharedPreferencesUtil util= new SharedPreferencesUtil(getActivity());
        String json = util.readString("user");
        Gson gson = new Gson();
        UserVO userVO = gson.fromJson(json,new TypeToken<UserVO>(){}.getType());
        Integer userId = userVO.getId();

        OkHttpUtils.get("http://"+serverIp+":8080/portal/therapist/patient.do?id="+userId,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<DiabetesUser>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<DiabetesUser>>>(){}.getType());
                List<DiabetesUser> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Doctor_Patient;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });






        return view;

    }
}


