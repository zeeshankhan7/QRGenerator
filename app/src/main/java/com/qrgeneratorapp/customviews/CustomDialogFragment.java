package com.qrgeneratorapp.customviews;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qrgeneratorapp.R;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.utils.QRCodeGeneratorTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by inmkhan021 on 7/17/2017.
 */
public class CustomDialogFragment extends DialogFragment {
    private Unbinder unbinder;
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
        getDialog().dismiss();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visitor_form, container,false);
        unbinder = ButterKnife.bind(this, rootView);
        // Do something else
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
