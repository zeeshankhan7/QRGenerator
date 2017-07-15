package com.qrgeneratorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qrgeneratorapp.custom.CustomFontButton;
import com.qrgeneratorapp.custom.CustomFontEditText;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.utils.CommonUtility;
import com.qrgeneratorapp.utils.Constants;
import com.qrgeneratorapp.utils.OnTaskCompleted;
import com.qrgeneratorapp.utils.QRCodeGeneratorTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.visitor_or_atendnt_editText)
    CustomFontEditText visitorOrAttendantName;
    @BindView(R.id.mobile_no_editText)
    CustomFontEditText visitorOrAttendantMobileNo;
    @BindView(R.id.patient_id_editText)
    CustomFontEditText patientId;
    @BindView(R.id.patient_name_editText)
    CustomFontEditText patientName;
//    @BindView(R.id.qrCode_image)
//    ImageView qrCodeImage;
    @BindView(R.id.container)
    CoordinatorLayout container;
//    @BindView(R.id.qrcodeProgress_textView)
//    TextView qrCodeProgressTxt;
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
        if(CommonUtility.isValidUserModel(hospitalUser)){
            intent.putExtra("HospitalUser", hospitalUser);
            startActivity(intent);
            finish();

//            qrCodeProgressBar.setVisibility(View.VISIBLE);
//            qrCodeProgressTxt.setVisibility(View.VISIBLE);
//            activityMainContainer.setAlpha(0f);
//            activityMainContainer.setVisibility(View.GONE);
//            new QRCodeGeneratorTask(getApplicationContext(),MainActivity.this).execute(hospitalUser);

        }else{
            CommonUtility.showSnackBar(container, Constants.REQUEST_MESSAGE);
        }
    }
//    @OnClick(R.id.qrCode_image)
//    void qrCodeImageClick(){
//        qrCodeImage.setAlpha(0f);
//        qrCodeImage.setVisibility(View.GONE);
//        qrCodeProgressBar.setAlpha(0f);
//        qrCodeProgressBar.setVisibility(View.GONE);
//        qrCodeProgressTxt.setAlpha(0f);
//        qrCodeProgressTxt.setVisibility(View.GONE);
//
//        activityMainContainer.setAlpha(1f);
//        activityMainContainer.setVisibility(View.VISIBLE);
//        patientId.setText(null);
//        patientName.setText(null);
//        visitorOrAttendantMobileNo.setText(null);
//        visitorOrAttendantName.setText(null);
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

//    @Override
//    public void onTaskCompleted(Bitmap bitmap) {
//        qrCodeImage.setAlpha(1f);
//        qrCodeImage.setVisibility(View.VISIBLE);
//        qrCodeProgressBar.setAlpha(0f);
//        qrCodeProgressBar.setVisibility(View.GONE);
//        activityMainContainer.setAlpha(0f);
//        activityMainContainer.setVisibility(View.GONE);
//        qrCodeProgressTxt.setAlpha(0f);
//        qrCodeProgressTxt.setVisibility(View.GONE);
//        qrCodeImage.setImageBitmap(bitmap);
//    }
}
