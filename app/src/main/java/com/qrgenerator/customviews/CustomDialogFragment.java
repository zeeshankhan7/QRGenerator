package com.qrgenerator.customviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qrgenerator.models.Visitor;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by inmkhan021 on 7/17/2017.
 */
public class CustomDialogFragment extends DialogFragment {





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
        Visitor visitor = new Visitor();
        visitor.setPatientId(patientId.getText().toString());
        visitor.setPatientName(patientName.getText().toString());
        visitor.setVisitorMobileNo(visitorMobileNo.getText().toString());
        visitor.setVisitorName(visitorName.getText().toString());

        if(CommonUtility.isValidVisitorModel(visitor)) {
            mCallback.passData(visitor);
            AppDBHelper appDBHelper = new AppDBHelper(getContext());
            ItemTable itemTable = new ItemTable(appDBHelper);
            itemTable.insert(visitor);
            getDialog().dismiss();
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
}
