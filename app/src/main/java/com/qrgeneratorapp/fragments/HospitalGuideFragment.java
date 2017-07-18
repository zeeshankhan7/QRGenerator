package com.qrgeneratorapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qrgeneratorapp.R;

/**
 * Created by inmkhan021 on 7/13/2017.
 */

public class HospitalGuideFragment extends Fragment {

    private static final String LOG_TAG=HospitalGuideFragment.class.getSimpleName();

    public HospitalGuideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospitalguide, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG ," inside onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG ," inside onResume ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG ," inside  onActivityCreated");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG ," inside  onPause ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG ," inside  onStop ");
    }
}
