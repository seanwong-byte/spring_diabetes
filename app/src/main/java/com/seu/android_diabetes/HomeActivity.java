package com.seu.android_diabetes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.seu.android_diabetes.fragment.AdminPatientFragment;
import com.seu.android_diabetes.fragment.AdminStuffFragment;
import com.seu.android_diabetes.fragment.CartFragment;
import com.seu.android_diabetes.fragment.DoctorPatientFragment;
import com.seu.android_diabetes.fragment.DoctorRecommendFragment;
import com.seu.android_diabetes.fragment.DoctorRecommendResultFragment;
import com.seu.android_diabetes.fragment.DoctorTherapistFragment;
import com.seu.android_diabetes.fragment.HomeFragment;
import com.seu.android_diabetes.fragment.OrderFragment;
import com.seu.android_diabetes.fragment.StationFragment;
import com.seu.android_diabetes.fragment.TherapistDoctorFragment;
import com.seu.android_diabetes.fragment.TherapistPatientFragment;
import com.seu.android_diabetes.fragment.TherapistRecommendFragment;
import com.seu.android_diabetes.fragment.TherapistRecommendResultFragment;
import com.seu.android_diabetes.fragment.UserCenterFragment;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout home_LinearLayout;
    LinearLayout cart_LinearLayout;
    LinearLayout order_LinearLayout;
    LinearLayout center_LinearLayout;

    //TAG用于切换
    public static final String HOMEFRAGMENT_TAG="HOME";
    public static final String CARTFRAGMENT_TAG="CART";
    public static final String ORDERFRAGMENT_TAG="ORDER";
    public static final String CENTERFRAGMENT_TAG="CENTER";

    public static final String DOCTORPATIENTFRAGMENT_TAG="DOCTOR_PATIENT";
    public static final String DOCTORTHERAPISTFRAGMENT_TAG="DOCTOR_THERAPIST";
    public static final String DOCTORRECOMMENDFRAGMENT_TAG="DOCTOR_RECOMMEND";
    public static final String DOCTORRECOMMENDRESULTFRAGMENT_TAG="DOCTOR_RECOMMEND_RESULT";

    public static final String THERAPISTPATIENTFRAGMENT_TAG="THERAPIST_PATIENT";
    public static final String THERAPISTDOCTORFRAGMENT_TAG="THERAPIST_DOCTOR";
    public static final String THERAPISTRECOMMENDFRAGMENT_TAG="THERAPIST_RECOMMEND";
    public static final String THERAPISTRECOMMENDRESULTFRAGMENT_TAG="THERAPIST_RECOMMEND_RESULT";



    public static final String ADMINPATIENT_TAG="ADMIN_PATIENT";
    public static final String ADMINSTUFF_TAG="ADMIN_STUFF";
    public static final String ADMINSTATION_TAG="ADMIN_STATION";


    ImageView first_icon;
    ImageView second_icon;
    ImageView third_icon;
    ImageView fourth_icon;
    String character;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        character = intent.getStringExtra("character");
        if(character.equals("1")){
            setContentView(R.layout.activity_home_patient);
            home_LinearLayout=(LinearLayout)findViewById(R.id.home);
            cart_LinearLayout=(LinearLayout)findViewById(R.id.cart);
            order_LinearLayout=(LinearLayout)findViewById(R.id.order);
            center_LinearLayout=(LinearLayout)findViewById(R.id.center);

            first_icon = findViewById(R.id.First_icon);
            second_icon = findViewById(R.id.Second_icon);
            third_icon = findViewById(R.id.Third_icon);
            fourth_icon = findViewById(R.id.Fourth_icon);


            home_LinearLayout.setOnClickListener(this);
            cart_LinearLayout.setOnClickListener(this);
            order_LinearLayout.setOnClickListener(this);
            center_LinearLayout.setOnClickListener(this);
        }
        else if(character.equals("2")){
            setContentView(R.layout.activity_home_doctor);
            home_LinearLayout=(LinearLayout)findViewById(R.id.home1);
            cart_LinearLayout=(LinearLayout)findViewById(R.id.cart1);
            order_LinearLayout=(LinearLayout)findViewById(R.id.order1);
            center_LinearLayout=(LinearLayout)findViewById(R.id.center1);

            first_icon = findViewById(R.id.First_icon1);
            second_icon = findViewById(R.id.Second_icon1);
            third_icon = findViewById(R.id.Third_icon1);
            fourth_icon = findViewById(R.id.Fourth_icon1);


            home_LinearLayout.setOnClickListener(this);
            cart_LinearLayout.setOnClickListener(this);
            order_LinearLayout.setOnClickListener(this);
            center_LinearLayout.setOnClickListener(this);
        }
        else if(character.equals("3")){
            setContentView(R.layout.activity_home_sportsdoctor);
            home_LinearLayout=(LinearLayout)findViewById(R.id.home2);
            cart_LinearLayout=(LinearLayout)findViewById(R.id.cart2);
            order_LinearLayout=(LinearLayout)findViewById(R.id.order2);
            center_LinearLayout=(LinearLayout)findViewById(R.id.center2);

            first_icon = findViewById(R.id.First_icon2);
            second_icon = findViewById(R.id.Second_icon2);
            third_icon = findViewById(R.id.Third_icon2);
            fourth_icon = findViewById(R.id.Fourth_icon2);


            home_LinearLayout.setOnClickListener(this);
            cart_LinearLayout.setOnClickListener(this);
            order_LinearLayout.setOnClickListener(this);
            center_LinearLayout.setOnClickListener(this);
        }
        else if(character.equals("4")){
            setContentView(R.layout.activity_home_manager);
            home_LinearLayout=(LinearLayout)findViewById(R.id.home3);
            cart_LinearLayout=(LinearLayout)findViewById(R.id.cart3);
            order_LinearLayout=(LinearLayout)findViewById(R.id.order3);
            center_LinearLayout=(LinearLayout)findViewById(R.id.center3);

            first_icon = findViewById(R.id.First_icon3);
            second_icon = findViewById(R.id.Second_icon3);
            third_icon = findViewById(R.id.Third_icon3);
            fourth_icon = findViewById(R.id.Fourth_icon3);


            home_LinearLayout.setOnClickListener(this);
            cart_LinearLayout.setOnClickListener(this);
            order_LinearLayout.setOnClickListener(this);
            center_LinearLayout.setOnClickListener(this);
        }
        else{
            Toast.makeText(this,"系统角色出错",Toast.LENGTH_LONG);
        }


        Log.d("info","=========oncreate=====");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("info","=========onstart=====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("info","=========onrusume=====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("info","=========onpause=====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("info","=========ondestory=====");
    }


    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.home:
                 attachFragment(HOMEFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient_selected);
                 second_icon.setImageResource(R.mipmap.prescription);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.home1:
                 attachFragment(DOCTORPATIENTFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient_selected);
                 second_icon.setImageResource(R.mipmap.sportsman);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.home2:
                 attachFragment(THERAPISTPATIENTFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient_selected);
                 second_icon.setImageResource(R.mipmap.doctor);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.home3:
                 attachFragment(ADMINSTATION_TAG,null);
                 first_icon.setImageResource(R.mipmap.station_selected);
                 second_icon.setImageResource(R.mipmap.patient);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.sportsman);
                 break;

             case R.id.cart:
                 attachFragment(CARTFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.prescription_selected);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.cart1:
                 attachFragment(DOCTORTHERAPISTFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.sportsman_selected);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.cart2:
                 attachFragment(THERAPISTDOCTORFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.doctor_selected);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.cart3:
                 attachFragment(ADMINPATIENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.station);
                 second_icon.setImageResource(R.mipmap.patient_selected);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.sportsman);
                 break;

             case R.id.order:
                 attachFragment(ORDERFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.prescription);
                 third_icon.setImageResource(R.mipmap.doctor_selected);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.order1:
                 attachFragment(DOCTORRECOMMENDFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.sportsman);
                 third_icon.setImageResource(R.mipmap.recommendation_selected);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.order2:
                 attachFragment(THERAPISTRECOMMENDFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.doctor);
                 third_icon.setImageResource(R.mipmap.recommendation_selected);
                 fourth_icon.setImageResource(R.mipmap.center);
                 break;

             case R.id.order3:
                 attachFragment(ADMINSTUFF_TAG,null);
                 first_icon.setImageResource(R.mipmap.station);
                 second_icon.setImageResource(R.mipmap.patient);
                 third_icon.setImageResource(R.mipmap.doctor_selected);
                 fourth_icon.setImageResource(R.mipmap.sportsman);
                 break;

             case R.id.center:
                 attachFragment(CENTERFRAGMENT_TAG,null);

                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.prescription);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.center_selected);
                 break;

             case R.id.center1:
                 attachFragment(CENTERFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.sportsman);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center_selected);
                 break;

             case R.id.center2:
                 attachFragment(CENTERFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.patient);
                 second_icon.setImageResource(R.mipmap.doctor);
                 third_icon.setImageResource(R.mipmap.recommendation);
                 fourth_icon.setImageResource(R.mipmap.center_selected);
                 break;

             case R.id.center3:
                 attachFragment(CENTERFRAGMENT_TAG,null);
                 first_icon.setImageResource(R.mipmap.station);
                 second_icon.setImageResource(R.mipmap.patient);
                 third_icon.setImageResource(R.mipmap.doctor);
                 fourth_icon.setImageResource(R.mipmap.sportsman_selected);
                 break;

         }

    }


    public void  attachFragment(String fragmentTag,Bundle bundle){

        //step1;获取Fragement管理器
        FragmentManager fragmentManager=getSupportFragmentManager();
        //开启事务
       FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

       Fragment fragment=fragmentManager.findFragmentByTag(fragmentTag);
       if(fragment==null){
           //管理器没有这个fragment
           if(fragmentTag.equals(HOMEFRAGMENT_TAG)){
               fragment=new HomeFragment();
               fragmentTransaction.add(fragment,HOMEFRAGMENT_TAG);
           }else if(fragmentTag.equals(CARTFRAGMENT_TAG)){
               fragment=new CartFragment();
               fragmentTransaction.add(fragment,CARTFRAGMENT_TAG);
           }else if(fragmentTag.equals(ORDERFRAGMENT_TAG)){
               fragment=new OrderFragment();
               fragmentTransaction.add(fragment,ORDERFRAGMENT_TAG);
           }else if(fragmentTag.equals(CENTERFRAGMENT_TAG)){
               fragment=new UserCenterFragment();
               fragmentTransaction.add(fragment,CENTERFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(DOCTORPATIENTFRAGMENT_TAG)){
               fragment=new DoctorPatientFragment();
               fragmentTransaction.add(fragment,DOCTORPATIENTFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(DOCTORTHERAPISTFRAGMENT_TAG)){
               fragment=new DoctorTherapistFragment();
               fragmentTransaction.add(fragment,DOCTORTHERAPISTFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(THERAPISTPATIENTFRAGMENT_TAG)){
               fragment=new TherapistPatientFragment();
               fragmentTransaction.add(fragment,THERAPISTPATIENTFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(THERAPISTDOCTORFRAGMENT_TAG)){
               fragment=new TherapistDoctorFragment();
               fragmentTransaction.add(fragment,THERAPISTDOCTORFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(ADMINPATIENT_TAG)){
               fragment=new AdminPatientFragment();
               fragmentTransaction.add(fragment,ADMINPATIENT_TAG);
           }
           else if(fragmentTag.equals(ADMINSTUFF_TAG)){
               fragment=new AdminStuffFragment();
               fragmentTransaction.add(fragment,ADMINSTUFF_TAG);
           }

           else if(fragmentTag.equals(ADMINSTATION_TAG)){
               fragment=new StationFragment();
               fragmentTransaction.add(fragment,ADMINSTATION_TAG);
           }
           else if(fragmentTag.equals(DOCTORRECOMMENDFRAGMENT_TAG)){
               fragment=new DoctorRecommendFragment();
               fragmentTransaction.add(fragment,DOCTORRECOMMENDFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(DOCTORRECOMMENDRESULTFRAGMENT_TAG)){
               fragment=new DoctorRecommendResultFragment();
               fragment.setArguments(bundle);
               fragmentTransaction.add(fragment,DOCTORRECOMMENDRESULTFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(THERAPISTRECOMMENDFRAGMENT_TAG)){
               fragment=new TherapistRecommendFragment();
               fragmentTransaction.add(fragment,THERAPISTRECOMMENDFRAGMENT_TAG);
           }
           else if(fragmentTag.equals(THERAPISTRECOMMENDRESULTFRAGMENT_TAG)){
               fragment=new TherapistRecommendResultFragment();
               fragment.setArguments(bundle);
               fragmentTransaction.add(fragment,THERAPISTRECOMMENDRESULTFRAGMENT_TAG);
           }

       //添加Fragment

        fragmentTransaction.replace(R.id.content,fragment,fragmentTag);

        fragmentTransaction.commit();


    }

}
}

