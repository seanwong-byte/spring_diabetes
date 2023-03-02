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
import com.seu.android_diabetes.adapter.PatientPrescriptionAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.Prescription;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private ListView listView;
    private PatientPrescriptionAdapter patientPrescriptionAdapter;
    private static final int Patient_Prescription = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case Patient_Prescription:
                    ArrayList<Prescription> prescriptionArrayList =(ArrayList<Prescription>)msg.obj;
                    patientPrescriptionAdapter = new PatientPrescriptionAdapter(getActivity(),R.layout.patient_prescription_listview_item,prescriptionArrayList);
                    listView.setAdapter(patientPrescriptionAdapter);
                    break;

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.cart_fragment,container,false);
        listView = view.findViewById(R.id.patient_prescription_listview);

        SharedPreferencesUtil util= new SharedPreferencesUtil(getActivity());
        String json = util.readString("user");
        Gson gson = new Gson();
        UserVO userVO = gson.fromJson(json,new TypeToken<UserVO>(){}.getType());
        Integer patient_id = userVO.getId();

        OkHttpUtils.get("http://"+serverIp+":8080/portal/prescription/patient.do?patient_id="+patient_id,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                Gson gson1 = new Gson();
                ServerResponse<List<Prescription>> serverResponse=gson1.fromJson(msg,new TypeToken<ServerResponse<List<Prescription>>>(){}.getType());
                List<Prescription> diabetesUserList = serverResponse.getData();
                Message message = new Message();
                message.what = Patient_Prescription;
                message.obj= diabetesUserList;
                handler.sendMessage(message);
            }
        });



        return view;

    }
}
