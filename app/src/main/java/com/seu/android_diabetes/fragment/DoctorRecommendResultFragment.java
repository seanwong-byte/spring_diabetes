package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.adapter.DoctorPatientAdapter;
import com.seu.android_diabetes.adapter.PatientPrescriptionAdapter;
import com.seu.android_diabetes.adapter.RecommendPrescriptionAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ItemDTO;
import com.seu.android_diabetes.vo.ServerResponse;

import java.util.ArrayList;
import java.util.List;

public class DoctorRecommendResultFragment extends Fragment {
    private ListView listView;
    private RecommendPrescriptionAdapter doctorPatientAdapter;
    private static final int Doctor_Patient = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case Doctor_Patient:
                    ArrayList<ItemDTO> diabetePatientList =(ArrayList<ItemDTO>)msg.obj;
                    doctorPatientAdapter = new RecommendPrescriptionAdapter(getActivity(),R.layout.patient_prescription_listview_item,diabetePatientList);
                    listView.setAdapter(doctorPatientAdapter);
                    break;

            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.doctor_recommend_result_fragment,container,false);
        listView = view.findViewById(R.id.doctor_recommend_result_listview);
        String string = getArguments().getString("patient id");
        Integer id = Integer.parseInt(string);

        OkHttpUtils.get("http://"+serverIp+":8080/portal/recommend/receipt?id="+id,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<ItemDTO>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<ItemDTO>>>(){}.getType());
                List<ItemDTO> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Doctor_Patient;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });


        return  view;
    }
}
