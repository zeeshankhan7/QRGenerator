package com.qrgeneratorapp.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.qrgeneratorapp.R;
import com.qrgeneratorapp.customviews.CustomFontButton;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.utils.OnTaskCompleted;
import com.qrgeneratorapp.utils.QRCodeGeneratorTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by inmkhan021 on 7/13/2017.
 */

public class QRCodeFragment  extends Fragment {

    private static final String LOG_TAG= QRCodeFragment.class.getSimpleName();
    private Unbinder unbinder;
    OnTaskCompleted mCallback;

    @BindView(R.id.qrCode_image)
    ImageView qrCodeImageView;
    @BindView(R.id.qrcode_progressbar)
    ProgressBar qrCodeProgressBar;
    @BindView(R.id.qrCodebutton)
    CustomFontButton qrGeneratorBtn;
    public QRCodeFragment() {
        // Required empty public constructor
    }
    @OnClick(R.id.qrCodebutton)
    void buttonClick() {
        HospitalUser hospitalUser = (HospitalUser) getArguments().getSerializable("HospitalUser");
        Log.d(LOG_TAG , "HOspital user" + hospitalUser.toString());
        qrCodeProgressBar.setVisibility(View.VISIBLE);
        qrGeneratorBtn.setVisibility(View.GONE);
        new QRCodeGeneratorTask(getActivity(),mCallback).execute(hospitalUser);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTaskCompleted ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MyInterface ");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_qrcode, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void setQRCodeBitmap(Bitmap mQrCodeBitmap){
        qrGeneratorBtn.setAlpha(0f);
        qrGeneratorBtn.setVisibility(View.GONE);
        qrCodeProgressBar.setVisibility(View.GONE);
        qrCodeImageView.setAlpha(1f);
        qrCodeImageView.setVisibility(View.VISIBLE);
        qrCodeImageView.setImageBitmap(mQrCodeBitmap);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG ," inside "+ LOG_TAG + " onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG ," inside "+ LOG_TAG + " onResume ");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG ," inside "+ LOG_TAG + " onPause ");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG ," inside "+ LOG_TAG + " onStop ");
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
