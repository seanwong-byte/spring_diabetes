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
import com.seu.android_diabetes.vo.Prescription;

import java.util.ArrayList;

public class PatientPrescriptionAdapter extends ArrayAdapter<Prescription> {
    private Context mcontext;
    private int mresource;

    public PatientPrescriptionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Prescription> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(this.mcontext).inflate(this.mresource,parent,false);
        TextView textView = convertView.findViewById(R.id.prescription_name);
        TextView textView1 = convertView.findViewById(R.id.prescription_url);
        TextView textView2 = convertView.findViewById(R.id.prescription_note);
        TextView textView3 = convertView.findViewById(R.id.prescription_status);
        textView.append(getItem(position).getName());
        textView1.append(getItem(position).getVideoUrl());
        textView2.append(getItem(position).getNote());
        textView3.append(getItem(position).getStatus().toString());

        return convertView;
    }
}
