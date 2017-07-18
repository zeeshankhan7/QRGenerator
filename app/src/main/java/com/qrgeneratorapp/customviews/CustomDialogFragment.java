package com.qrgeneratorapp.customviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qrgeneratorapp.DashboardActivity;
import com.qrgeneratorapp.R;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.models.Visitor;
import com.qrgeneratorapp.utils.CommonUtility;
import com.qrgeneratorapp.utils.Constants;
import com.qrgeneratorapp.utils.QRCodeGeneratorTask;

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
    @BindView(R.id.cancelBtn)
    CustomFontButton cancelBtn;
    @BindView(R.id.visitor_form_container)
    CoordinatorLayout container;
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
            getDialog().dismiss();
        }else{
            CommonUtility.showSnackBar(container, Constants.REQUEST_MESSAGE);
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
