package com.qrgenerator;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.customviews.CustomDialogFragment;
import com.qrgenerator.customviews.CustomFontButton;
import com.qrgenerator.customviews.CustomFontEditText;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Attendant;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.retrofit.BusProvider;
import com.qrgenerator.retrofit.Communicator;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  {

    private static final String LOG_TAG= MainActivity.class.getSimpleName();
//    @BindView(R.id.visitor_or_atendnt_editText)
//    CustomFontEditText visitorOrAttendantName;
    @BindView(R.id.mobile_no_editText)
    CustomFontEditText visitorOrAttendantMobileNo;
    @BindView(R.id.patient_id_editText)
    CustomFontEditText patientId;
//    @BindView(R.id.patient_name_editText)
//    CustomFontEditText patientName;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.activity_main)
LinearLayout activityMainContainer;
    @BindView(R.id.loginBtn)
    CustomFontButton loginBtn;
    @OnClick(R.id.loginBtn)
    public void login() {
        String patId=patientId.getText().toString();
        String attendantContactNo=visitorOrAttendantMobileNo.getText().toString();
        attendant = new Attendant(patId, attendantContactNo);
//        attendant.setPatientId(patId);
//        attendant.setPatientName(patientName.getText().toString());
//        attendant.setAttendentContactNo(visitorOrAttendantMobileNo.getText().toString());
//        attendant.setVisitorOrAttendantName(visitorOrAttendantName.getText().toString());
        //         card_view:cardBackgroundColor="@color/colorAccent"
        if(CommonUtility.isValidUserModel(attendant)){
            Communicator communicator = new Communicator();
            communicator.loginAttendant(MainActivity.this,attendant);
        }else{
            CommonUtility.showSnackBar(container, Constants.REQUEST_MESSAGE);
        }
    }
    Attendant attendant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        CommonUtility.showSnackBar(container, errorEvent.getErrorMsg());
    }
    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){


        String responseMsg="";
        if(serverEvent.getServerResponse() instanceof AddVisitorResponse){
            if(serverEvent!=null && serverEvent.getServerResponse()!=null ){
                String status=((AddVisitorResponse) serverEvent.getServerResponse()).getStatus();
                responseMsg=((AddVisitorResponse) serverEvent.getServerResponse()).getMessage();
                if(!CommonUtility.isStringEmptyOrNull(status) && status.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                    AppSharedPreferenceHelper appPref = AppSharedPreferenceHelper.getInstance(getApplicationContext());
                    appPref.saveAttendant(attendant);
                    Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    CommonUtility.showSnackBar(container, responseMsg);
                }
            }
        }

//        finish();

    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG , " onResume called ");
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG , " onPause called ");
        BusProvider.getInstance().unregister(this);
    }
}
