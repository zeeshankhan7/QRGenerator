package com.qrgenerator.customviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.retrofit.ApiClient;
import com.qrgenerator.retrofit.ApiInterface;
import com.qrgenerator.retrofit.BusProvider;
import com.qrgenerator.retrofit.Communicator;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgenerator.utils.NetworkCallUtil;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by inmkhan021 on 7/17/2017.
 */
public class CustomDialogFragment extends DialogFragment {


    private static final String LOG_TAG= CustomDialogFragment.class.getSimpleName();


    @BindView(R.id.visitor_name_editTxt)
    CustomFontEditText visitorName;
    @BindView(R.id.visitor_mobile_no_editTxt)
    CustomFontEditText visitorMobileNo;
    @BindView(R.id.patient_id_editTxt)
    CustomFontEditText patientId;
    @BindView(R.id.patient_name_editTxt)
    CustomFontEditText patientName;
    private Unbinder unbinder;
    @BindView(R.id.card_view)
    CardView cv;
    @BindView(R.id.activity_main1)
    RelativeLayout activity_main1;
    @BindView(R.id.activity_main)
    LinearLayout  ll;
    @BindView(R.id.cancelBtn)
    CustomFontButton cancelBtn;
    @BindView(R.id.SubmitBtn)
    CustomFontButton SubmitBtn;
    @OnClick(R.id.cancelBtn)
    void cancelButtonClick() {
        getDialog().dismiss();

    }
    @OnClick(R.id.SubmitBtn)
    void submitButtonClick() {
        final Visitor visitor = new Visitor();
        visitor.setPatientId(patientId.getText().toString());
        visitor.setPatientName(patientName.getText().toString());
        visitor.setVisitorMobileNo(visitorMobileNo.getText().toString());
        visitor.setVisitorName(visitorName.getText().toString());

        if(CommonUtility.isValidVisitorModel(visitor)) {
            Communicator communicator = new Communicator();
            AddVisitorParams params= new AddVisitorParams(visitor.getVisitorName(),visitor.getVisitorMobileNo(),visitor.getPatientId());
            communicator.addVisitorToServer(params,visitor);
        }else{
            CommonUtility.showSnackBar(activity_main1, Constants.REQUEST_MESSAGE);;
        }

    }
    public interface DataPassListener{
        public void passData(Visitor data);
    }
    DataPassListener mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visitor_form, container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, rootView);
        // Do something else
        return rootView;
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try
        {
            mCallback = (DataPassListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement OnImageClickListener");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        String responseMsg="";
        if(serverEvent.getServerResponse() instanceof AddVisitorResponse){
            if(serverEvent!=null && serverEvent.getServerResponse()!=null ){
                String status=((AddVisitorResponse) serverEvent.getServerResponse()).getStatus();
                responseMsg=((AddVisitorResponse) serverEvent.getServerResponse()).getMessage();
                Visitor visitor=serverEvent.getVisitor();
                if(!CommonUtility.isStringEmptyOrNull(status) && status.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                    mCallback.passData(visitor);
                    AppDBHelper appDBHelper = new AppDBHelper(getContext());
                    ItemTable itemTable = new ItemTable(appDBHelper);
                    itemTable.insert(visitor);
                    CommonUtility.showSnackBar(activity_main1, responseMsg);
                    getDialog().dismiss();
                }else{
                    CommonUtility.showSnackBar(activity_main1, responseMsg);
                }
            }
        }

    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        CommonUtility.showSnackBar(activity_main1, errorEvent.getErrorMsg());
    }
}
