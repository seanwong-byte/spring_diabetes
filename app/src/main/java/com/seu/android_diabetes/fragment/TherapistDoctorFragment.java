package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.adapter.DoctorTherapistAdapter;
import com.seu.android_diabetes.adapter.TherapistDoctorAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class TherapistDoctorFragment extends Fragment {

    private ListView listView;
    private TherapistDoctorAdapter doctorTherapistAdapter;
    private static final int Doctor_Therapist = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case Doctor_Therapist:
                    ArrayList<DiabetesUser> diabetePatientList =(ArrayList<DiabetesUser>)msg.obj;
                    doctorTherapistAdapter = new TherapistDoctorAdapter(getActivity(),R.layout.doctor_listview_item3,diabetePatientList);
                    listView.setAdapter(doctorTherapistAdapter);
                    break;

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.therapist_doctor_fragment,container,false);
        listView = view.findViewById(R.id.therapist_doctor_listview);


        //拿userid
        SharedPreferencesUtil util= new SharedPreferencesUtil(getActivity());
        String json = util.readString("user");
        Gson gson = new Gson();
        UserVO userVO = gson.fromJson(json,new TypeToken<UserVO>(){}.getType());
        Integer userId = userVO.getId();

        OkHttpUtils.get("http://"+serverIp+":8080/portal/therapist/doctor.do?id="+userId,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<DiabetesUser>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<DiabetesUser>>>(){}.getType());
                List<DiabetesUser> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Doctor_Therapist;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });






        return view;

    }
}
