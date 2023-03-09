package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

//import com.seu.android_diabetes.ProductDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.LoginActivity;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.adapter.MyViewPagerAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.utils.SharedPreferencesUtil;
import com.seu.android_diabetes.vo.PIVO;
import com.seu.android_diabetes.vo.ServerResponse;
import com.seu.android_diabetes.vo.UserVO;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
//import com.seu.android_diabetes.adapter.MyViewPagerAdapter;
//import com.seu.android_diabetes.utils.OkHttpCallbackFile;
//import com.seu.android_diabetes.vo.ProductListVO;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private List<ImageView> viewList=new ArrayList<>();
    private PagerAdapter pagerAdapter;
    private XUICommonListItemView xuiCommonListItemView1;
    private XUICommonListItemView xuiCommonListItemView2;
    private XUICommonListItemView xuiCommonListItemView3;
    private XUICommonListItemView xuiCommonListItemView4;
//    private  TextView textView1;
//    private TextView textView2;
//    private TextView textView3;
//    private TextView textView4;
//    private EditText editText1;
//    private EditText editText2;
//    private  EditText editText3;
//    private EditText editText4;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case PI_NOTIFY:
                    PIVO pivo = (PIVO) msg.obj;
                      xuiCommonListItemView1.setDetailText(pivo.getVascularAgingDegree().toString());
                      xuiCommonListItemView2.setDetailText(pivo.getExerciseAmount().toString());
                      xuiCommonListItemView3.setDetailText(pivo.getFastingBloodGlucose().toString());
                      xuiCommonListItemView4.setDetailText(pivo.getPostprandialBloodGlucose().toString());
                    break;
            }
        }
    };

    private  static final int PI_NOTIFY=1;


//    private Handler mHandler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//
//            switch (msg.what){
//                case CARSOUEL_NOTIFY:
//                    List<ProductListVO> productListVOLsit=(List<ProductListVO>)msg.obj;
//                    //创建view,渲染到viewpager上
//                    render(productListVOLsit);
//
//                    break;
//                case LOAD_PIC:
//                    ImageView imageView=viewList.get(msg.arg1);
//                    byte[] contents=(byte[])msg.obj;
//                    Bitmap bitmap=BitmapFactory.decodeByteArray(contents,0,contents.length);
//                    imageView.setImageBitmap(bitmap);
//                    break;
//            }
//
//        }
//    };

//    private  void  render(List<ProductListVO> productListVOLsit){
//
//        for(int i=0;i<productListVOLsit.size();i++){
//            ProductListVO productListVO=productListVOLsit.get(i);
//            String uri=productListVO.getMainImage();
//
//            final ImageView imageView=new ImageView(getActivity());
//            imageView.setId(productListVO.getId());
//            imageView.setOnClickListener(this);
//            viewList.add(imageView);
//            OkHttpUtils.get("http://img.cdn.imbession.top/"+uri,new OkHttpCallbackFile(i){
//                @Override
//                public void onFinish(String status, byte[] msg,int position) {
//
//                      Message message=mHandler.obtainMessage();
//                      message.what=LOAD_PIC;
//                      message.arg1=position;
//                      message.obj=msg;
//
//                      mHandler.sendMessage(message);
//                }
//            });
//            //url okhttp-->byte[] ->BitmapFactory -->Bitmap
//
//
//
//
//
//
//
//
//        }
//
//        pagerAdapter=new MyViewPagerAdapter(viewList);
//        viewPager.setAdapter(pagerAdapter);
//
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.home_fragment,container,false);
        xuiCommonListItemView1=view.findViewById(R.id.vascular_aging_degree);
        xuiCommonListItemView1.setOrientation(XUICommonListItemView.VERTICAL); //设置布局方向
        xuiCommonListItemView1.setImageDrawable(getResources().getDrawable(R.drawable.vessel)); //设置左侧图标
        xuiCommonListItemView1.setText("血管老化程度"); //设置主标题
        xuiCommonListItemView1.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView1.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON); //设置右侧箭头类型


        xuiCommonListItemView2=view.findViewById(R.id.exercise_amount);
        xuiCommonListItemView2.setOrientation(XUICommonListItemView.VERTICAL); //设置布局方向
        xuiCommonListItemView2.setImageDrawable(getResources().getDrawable(R.drawable.sprots_volume)); //设置左侧图标
        xuiCommonListItemView2.setText("运动量"); //设置主标题
        xuiCommonListItemView2.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView2.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON); //设置右侧箭头类型

        xuiCommonListItemView3=view.findViewById(R.id.fasting_blood_glucose);
        xuiCommonListItemView3.setOrientation(XUICommonListItemView.VERTICAL); //设置布局方向
        xuiCommonListItemView3.setImageDrawable(getResources().getDrawable(R.drawable.beforediet_blood_glucose)); //设置左侧图标
        xuiCommonListItemView3.setText("空腹血糖mmol/L"); //设置主标题
        xuiCommonListItemView3.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView3.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON); //设置右侧箭头类型

        xuiCommonListItemView4=view.findViewById(R.id.postprandial_blood_glucose);
        xuiCommonListItemView4.setOrientation(XUICommonListItemView.VERTICAL); //设置布局方向
        xuiCommonListItemView4.setImageDrawable(getResources().getDrawable(R.drawable.afterdiet_blood_glucose)); //设置左侧图标
        xuiCommonListItemView4.setText("餐后血糖mmol/L"); //设置主标题
        xuiCommonListItemView4.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView4.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON); //设置右侧箭头类型

























//        textView1=view.findViewById(R.id.vascular_aging_degree);
//        textView2=view.findViewById(R.id.exercise_amount);
//        textView3=view.findViewById(R.id.fasting_blood_glucose);
//        textView4=view.findViewById(R.id.postprandial_blood_glucose);
//        editText1=view.findViewById(R.id.vascular_aging_degree_edit);
//        editText2=view.findViewById(R.id.exercise_amount_edit);
//        editText3=view.findViewById(R.id.fasting_blood_glucose_edit);
//        editText4=view.findViewById(R.id.postprandial_blood_glucose_edit);
        SharedPreferencesUtil util= new SharedPreferencesUtil(getActivity());
        String json = util.readString("user");

        Gson gson = new Gson();
        UserVO userVO = gson.fromJson(json,new TypeToken<UserVO>(){}.getType());
        Integer userId = userVO.getId();
        Log.d("userId",userId.toString());
        OkHttpUtils.get("http://"+serverIp+":8080/portal/PI/pi.do?id="+userId,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);
                //解析数据
                Gson gson=new Gson();
                //
                ServerResponse<PIVO> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<PIVO>>(){}.getType());
                int status1=serverResponse.getStatus();
                if(status1==0){
                    //登录成功

                    PIVO pivo = serverResponse.getData();

                    if(pivo!=null){
                        Message message = new Message();
                        message.what=PI_NOTIFY;
                        message.obj=pivo;
                        handler.sendMessage(message);

                    }
                    else {
                        Looper.prepare();
                        Toast.makeText(getActivity(),"用户无生理数据",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }


                }


            }
        });

//        viewPager=view.findViewById(R.id.casouelviewpager);
//
//        List<View> viewList = new ArrayList<View>();
//
//        ImageView view1=new ImageView(getActivity());
//        view1.setImageResource(R.mipmap.center);
//        viewList.add(view1);
//
//        ImageView view2=new ImageView(getActivity());
//        view2.setImageResource(R.mipmap.patient);
//        viewList.add(view2);
//
//
//        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(viewList);
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


//         List<View> viewList=new ArrayList<>();
//        ImageView imageView=new ImageView(getActivity());
//        imageView.setImageResource(R.mipmap.cart);
//        viewList.add(imageView);
//
//        ImageView imageView2=new ImageView(getActivity());
//        imageView2.setImageResource(R.mipmap.order);
//        viewList.add(imageView2);
//
//         MyViewPagerAdapter viewPagerAdapter=new MyViewPagerAdapter(viewList);
//         viewPager.setAdapter(viewPagerAdapter);

         //获取接口数据

//
//        OkHttpUtils.get("http://172.24.36.1:8080/portal/product/carsouel.do",new OkHttpCallback(){
//            @Override
//            public void onFinish(String status, String msg) {
//
//                //解析数据
//                Gson gson=new Gson();
//                ServerResponse<List<ProductListVO>> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<List<ProductListVO>>>(){}.getType());
//                int status1=serverResponse.getStatus();
//                if(status1==0){
//                    //获取数据
//                    List<ProductListVO> productListVOS=serverResponse.getData();
//
//                    Message message=new Message();
//                    message.what=CARSOUEL_NOTIFY;
//                    message.obj=productListVOS;
//                    mHandler.sendMessage(message);
//                }
//
//
//            }
//        });
//
        return  view;
    }


//    @Override
//    public void onClick(View v) {
//
//
//        Intent intent=new Intent(getActivity(), ProductDetailActivity.class);
//        intent.putExtra("productId",v.getId());
//        startActivity(intent);
//
//    }
}
