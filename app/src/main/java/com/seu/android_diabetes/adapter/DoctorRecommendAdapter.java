package com.seu.android_diabetes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.vo.DiabetesUser;
import java.util.ArrayList;

public class DoctorRecommendAdapter  extends ArrayAdapter<DiabetesUser> {

    private Context mcontext;
    private int mresource;

    public DoctorRecommendAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiabetesUser> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(this.mcontext).inflate(this.mresource,parent,false);
        TextView textView = convertView.findViewById(R.id.patient_name);
        TextView textView1 = convertView.findViewById(R.id.patient_phone);
        textView.setText(getItem(position).getNickname());
        textView1.setText(getItem(position).getPhone());



        return convertView;
    }
}

