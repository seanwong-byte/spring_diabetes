package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;

import java.util.List;

public class OrderFragment extends Fragment {
    ImageView doctor_head;
    ImageView therapist_head;
    TextView doctor_name;
    TextView doctor_phone;
    TextView therapist_name;
    TextView therapist_phone;
    private static final int PATIENT_STUFF = 1;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case PATIENT_STUFF:
                    List<DiabetesUser> diabetesUserList =(List<DiabetesUser>) msg.obj;
                    if(diabetesUserList.get(0)!=null && diabetesUserList.get(0).getRole().equals("ROLE_DOCTOR")) {
                        doctor_name.setText(diabetesUserList.get(0).getNickname());
                        doctor_phone.setText(diabetesUserList.get(0).getPhone());
                        if(diabetesUserList.get(1)!=null){
                            therapist_name.setText(diabetesUserList.get(1).getNickname());
                            therapist_phone.setText(diabetesUserList.get(1).getPhone());
                        }
                    }

                    if(diabetesUserList.get(0)!=null && diabetesUserList.get(0).getRole().equals("ROLE_THERAPIST")) {
                        therapist_name.setText(diabetesUserList.get(0).getNickname());
                        therapist_phone.setText(diabetesUserList.get(0).getPhone());
                        if(diabetesUserList.get(1)!=null){
                            doctor_name.setText(diabetesUserList.get(1).getNickname());
                            doctor_phone.setText(diabetesUserList.get(1).getPhone());
                        }
                    }
                    break;


            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment,container,false);
        doctor_head = view.findViewById(R.id.patient_doctor_head);
        therapist_head = view.findViewById(R.id.patient_therapist_head);
        doctor_name = view.findViewById(R.id.patient_doctor_name);
        doctor_phone=view.findViewById(R.id.patient_doctor_phone);
        therapist_name = view.findViewById(R.id.patient_therapist_name);
        therapist_phone=view.findViewById(R.id.patient_therapist_phone);

        SharedPreferencesUtil util= new SharedPreferencesUtil(getActivity());
        String json = util.readString("user");
        Gson gson = new Gson();
        UserVO userVO = gson.fromJson(json,new TypeToken<UserVO>(){}.getType());
        Integer userId = userVO.getId();
        OkHttpUtils.get("http://"+serverIp+":8080/portal/user/stuff.do?id="+userId,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<DiabetesUser>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<DiabetesUser>>>(){}.getType());
                List<DiabetesUser> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = PATIENT_STUFF;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });
        return view;

    }
}
