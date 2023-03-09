package com.seu.android_diabetes.fragment;

import static com.seu.android_diabetes.utils.common.serverIp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seu.android_diabetes.R;
import com.seu.android_diabetes.adapter.DoctorPatientAdapter;
import com.seu.android_diabetes.utils.OkHttpCallback;
import com.seu.android_diabetes.utils.OkHttpUtils;
import com.seu.android_diabetes.vo.DiabetesUser;
import com.seu.android_diabetes.vo.ServerResponse;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class StationFragment extends Fragment {

private TextView sugar_control_rate;
private TextView patient_number;
private TextView doctor_number;
private TextView therapist_number;

    private XUICommonListItemView xuiCommonListItemView1;
    private XUICommonListItemView xuiCommonListItemView2;
    private XUICommonListItemView xuiCommonListItemView3;
    private XUICommonListItemView xuiCommonListItemView4;
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
private Handler handler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case 1:
                List<String> list = new ArrayList<>();
                list = (List<String>) msg.obj;
                xuiCommonListItemView1.setDetailText(list.get(0));
                xuiCommonListItemView2.setDetailText(list.get(1));
                xuiCommonListItemView3.setDetailText(list.get(2));
                xuiCommonListItemView4.setDetailText(list.get(3));
                break;
        }
    }
};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.admin_station_fragment,container,false);

        xuiCommonListItemView1=view.findViewById(R.id.sugar_rate);
        xuiCommonListItemView1.setOrientation(XUICommonListItemView.HORIZONTAL); //设置布局方向
        xuiCommonListItemView1.setImageDrawable(getResources().getDrawable(R.drawable.sugar_rate)); //设置左侧图标
        xuiCommonListItemView1.setText("控糖率"); //设置主标题
        xuiCommonListItemView1.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView1.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_NONE); //设置右侧箭头类型



        xuiCommonListItemView2=view.findViewById(R.id.patient_number);
        xuiCommonListItemView2.setOrientation(XUICommonListItemView.HORIZONTAL); //设置布局方向
        xuiCommonListItemView2.setImageDrawable(getResources().getDrawable(R.drawable.patient_volume)); //设置左侧图标
        xuiCommonListItemView2.setText("患者数"); //设置主标题
        xuiCommonListItemView2.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView2.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_NONE); //设置右侧箭头类型

        xuiCommonListItemView3=view.findViewById(R.id.doctor_number);
        xuiCommonListItemView3.setOrientation(XUICommonListItemView.HORIZONTAL); //设置布局方向
        xuiCommonListItemView3.setImageDrawable(getResources().getDrawable(R.drawable.doctor_volume)); //设置左侧图标
        xuiCommonListItemView3.setText("医生数"); //设置主标题
        xuiCommonListItemView3.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView3.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_NONE); //设置右侧箭头类型

        xuiCommonListItemView4=view.findViewById(R.id.therapist_number);
        xuiCommonListItemView4.setOrientation(XUICommonListItemView.HORIZONTAL); //设置布局方向
        xuiCommonListItemView4.setImageDrawable(getResources().getDrawable(R.drawable.therapist_volume)); //设置左侧图标
        xuiCommonListItemView4.setText("运动康复师数"); //设置主标题
        xuiCommonListItemView4.setDetailText("未设置"); //设置副标题
        xuiCommonListItemView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        xuiCommonListItemView4.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_NONE); //设置右侧箭头类型




//        sugar_control_rate = view.findViewById(R.id.sugar_rate);
//        patient_number = view.findViewById(R.id.patient_number);
//        doctor_number = view.findViewById(R.id.doctor_number);
//        therapist_number = view.findViewById(R.id.therapist_number);

        // 设置数据库参数
        String dbUrl = "jdbc:mysql://"+serverIp+":3306/shopping?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String dbUsername = "root";
        String dbPassword = "123456";


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                // 加载驱动程序
                try {
                    Class.forName(JDBC_DRIVER);
                    Connection conn = null;
// 建立数据库连接
                    conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

// 定义查询语句
                    String sql = "SELECT glucose_control_level FROM station WHERE id = 1";
                    String sql2 = "SELECT count(*) as patient_number FROM sys_user WHERE role = 'ROLE_PATIENT'";
                    String sql3 = "SELECT count(*) as doctor_number FROM sys_user WHERE role = 'ROLE_DOCTOR'";
                    String sql4 = "SELECT count(*) as therapist_number FROM sys_user WHERE role = 'ROLE_THERAPIST'";

// 创建一个用于执行 SQL 语句的 PreparedStatement 对象
                    Statement statement = conn.createStatement();
                    Statement statement1 = conn.createStatement();
                    Statement statement2 = conn.createStatement();
                    Statement statement3 = conn.createStatement();
                    statement.execute(sql);
                    statement1.execute(sql2);
                    statement2.execute(sql3);
                    statement3.execute(sql4);

// 执行查询语句
                    ResultSet resultSet = statement.getResultSet();
                    ResultSet resultSet2 = statement1.getResultSet();
                    ResultSet resultSet3 = statement2.getResultSet();
                    ResultSet resultSet4 = statement3.getResultSet();

// 处理查询结果
                    String glucose_control_level="";
                    String patient_number="";
                    String doctor_number="";
                    String therapist_number="";

                    if (resultSet.next()) {
                        // 读取数据库中的列值
                       glucose_control_level = resultSet.getString("glucose_control_level");
                    }
                    if (resultSet2.next()) {
                        // 读取数据库中的列值
                        patient_number = resultSet2.getString("patient_number");
                    }
                    if (resultSet3.next()) {
                        // 读取数据库中的列值
                        doctor_number = resultSet3.getString("doctor_number");
                    }
                    if (resultSet4.next()) {
                        // 读取数据库中的列值
                       therapist_number = resultSet4.getString("therapist_number");
                    }

                    Message message = new Message();
                    message.what=1;
                    List<String> list =new ArrayList<>();
                    list.add(glucose_control_level);
                    list.add(patient_number);
                    list.add(doctor_number);
                    list.add(therapist_number);
                    message.obj= list;
                    handler.sendMessage(message);

// 关闭结果集、语句和连接
                    resultSet.close();
                    resultSet2.close();
                    resultSet3.close();
                    resultSet4.close();
                    statement.close();
                    statement1.close();
                    statement2.close();
                    statement3.close();
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();



        return view;

    }
}
