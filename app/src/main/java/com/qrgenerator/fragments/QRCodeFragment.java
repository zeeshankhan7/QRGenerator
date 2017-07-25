package com.qrgenerator.fragments;

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

import com.qrgeneratorapp.max.R;
import com.qrgenerator.customviews.CustomFontButton;
import com.qrgenerator.models.Attendant;
import com.qrgenerator.utils.OnTaskCompleted;
import com.qrgenerator.utils.QRCodeGeneratorTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by inmkhan021 on 7/13/2017.
 */

public class QRCodeFragment  extends Fragment {
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
        Attendant attendant = (Attendant) getArguments().getSerializable("Attendant");
        Log.d("Irshad" , "HOspital user" + attendant.toString());
        qrCodeProgressBar.setVisibility(View.VISIBLE);
        qrGeneratorBtn.setVisibility(View.GONE);
        new QRCodeGeneratorTask(getActivity(),mCallback).execute(attendant);

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
}
