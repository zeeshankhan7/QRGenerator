package com.qrgeneratorapp;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.qrgeneratorapp.customviews.CustomFontButton;
import com.qrgeneratorapp.customviews.CustomFontEditText;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.utils.CommonUtility;
import com.qrgeneratorapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.visitor_or_atendnt_editText)
    CustomFontEditText visitorOrAttendantName;
    @BindView(R.id.mobile_no_editText)
    CustomFontEditText visitorOrAttendantMobileNo;
    @BindView(R.id.patient_id_editText)
    CustomFontEditText patientId;
    @BindView(R.id.patient_name_editText)
    CustomFontEditText patientName;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.activity_main)
LinearLayout activityMainContainer;
    @BindView(R.id.loginBtn)
    CustomFontButton loginBtn;
    @OnClick(R.id.loginBtn)
    public void login() {
        Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
        HospitalUser hospitalUser = new HospitalUser();
        hospitalUser.setPatientId(patientId.getText().toString());
        hospitalUser.setPatientName(patientName.getText().toString());
        hospitalUser.setVisitorOrAttendantMobileNo(visitorOrAttendantMobileNo.getText().toString());
        hospitalUser.setVisitorOrAttendantName(visitorOrAttendantName.getText().toString());
        //         card_view:cardBackgroundColor="@color/colorAccent"
        if(CommonUtility.isValidUserModel(hospitalUser)){
            intent.putExtra("HospitalUser", hospitalUser);
            startActivity(intent);
            finish();

        }else{
            intent.putExtra("HospitalUser", hospitalUser);
            startActivity(intent);
            CommonUtility.showSnackBar(container, Constants.REQUEST_MESSAGE);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
