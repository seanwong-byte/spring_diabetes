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
import com.seu.android_diabetes.adapter.DoctorPatientAdapter;
import com.seu.android_diabetes.adapter.DoctorTherapistAdapter;
import com.seu.android_diabetes.adapter.TherapistDoctorAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ServerResponse;

import java.util.ArrayList;
import java.util.List;

public class AdminStuffFragment extends Fragment {

    private ListView listView;
    private ListView listView2;
    private DoctorTherapistAdapter doctorTherapistAdapter;
    private TherapistDoctorAdapter therapistDoctorAdapter;
    private static final int Admin_Doctor = 1;
    private static final int Admin_Therapist = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case Admin_Doctor:
                    ArrayList<DiabetesUser> diabeteDoctorList =(ArrayList<DiabetesUser>)msg.obj;
                    therapistDoctorAdapter = new TherapistDoctorAdapter(getActivity(),R.layout.doctor_listview_item3,diabeteDoctorList);
                    listView.setAdapter(therapistDoctorAdapter);
                    break;
                case Admin_Therapist:
                    ArrayList<DiabetesUser> diabeteTherapistList =(ArrayList<DiabetesUser>)msg.obj;
                    doctorTherapistAdapter = new DoctorTherapistAdapter(getActivity(),R.layout.doctor_listview_item2,diabeteTherapistList);
                    listView2.setAdapter(doctorTherapistAdapter);
                    break;

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.admin_stuff_fragment,container,false);
        listView = view.findViewById(R.id.admin_doctor_listview);
        listView2 = view.findViewById(R.id.admin_therapist_listview);



        OkHttpUtils.get("http://"+serverIp+":8080/portal/admin/doctor.do",new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<DiabetesUser>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<DiabetesUser>>>(){}.getType());
                List<DiabetesUser> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Admin_Doctor;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });

        OkHttpUtils.get("http://"+serverIp+":8080/portal/admin/therapist.do",new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<DiabetesUser>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<DiabetesUser>>>(){}.getType());
                List<DiabetesUser> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Admin_Therapist;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });






        return view;

    }
}
