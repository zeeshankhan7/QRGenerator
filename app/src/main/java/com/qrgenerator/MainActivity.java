package com.qrgenerator;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.qrgenerator.customviews.CustomFontButton;
import com.qrgenerator.customviews.CustomFontEditText;
import com.qrgenerator.models.Attendant;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgeneratorapp.max.R;

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
        Attendant attendant = new Attendant();
        attendant.setPatientId(patientId.getText().toString());
        attendant.setPatientName(patientName.getText().toString());
        attendant.setVisitorOrAttendantMobileNo(visitorOrAttendantMobileNo.getText().toString());
        attendant.setVisitorOrAttendantName(visitorOrAttendantName.getText().toString());
        //         card_view:cardBackgroundColor="@color/colorAccent"
        if(CommonUtility.isValidUserModel(attendant)){
            AppSharedPreferenceHelper appPref = AppSharedPreferenceHelper.getInstance(getApplicationContext());
            appPref.saveAttendant(attendant);
            Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
            startActivity(intent);
            finish();

        }else{
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
