package com.qrgenerator.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.adapter.VisitorAdapter;
import com.qrgenerator.customviews.CustomDialogFragment;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.models.VisitorListResponse;
import com.qrgenerator.retrofit.Communicator;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by inmkhan021 on 7/13/2017.
 */

public class VisitorListFragment extends Fragment {

    private static final String LOG_TAG= VisitorListFragment.class.getSimpleName();
    private AppDBHelper appDBHelper;
    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.visitor_form)
    View v;
    @BindView(R.id.allow_visitor_btn)
    FloatingActionButton allowVisitorBtn;

    @OnClick(R.id.allow_visitor_btn)
    public void buttonClick() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        CustomDialogFragment dFragment = new CustomDialogFragment();
        // Show DialogFragment
        dFragment.show(fm, "Dialog Fragment");
//        showDialog();
//        v.setVisibility(View.VISIBLE);
//        mRecyclerView.setVisibility(View.GONE);
//        allowVisitorBtn.setVisibility(View.GONE);

    }
    private LinearLayoutManager mLinearLayoutManager;
    private Unbinder unbinder;
    private View view;
    private VisitorAdapter adapter;
    private List<Visitor> visitorList;
    int allowedVisitorCount;
    public VisitorListFragment() {
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
        view=inflater.inflate(R.layout.fragment_visitor, container, false);
        unbinder = ButterKnife.bind(this, view);

        initLayoutComponent();
        return view;
    }

    private int prepareVisitor() {
        if(!CommonUtility.isNetworkAvailable(getContext())){
            ItemTable itemTable = new ItemTable(appDBHelper);
            visitorList= itemTable.getAllData();
        }else {
            // network call will be here to get the list of visitor from server
            Communicator communicator = new Communicator();
            AppSharedPreferenceHelper appPref = AppSharedPreferenceHelper.getInstance(getContext());
            String patientID= appPref.getPatientIDFromSP();
            communicator.getVisitorListFromServer(patientID);
        }

         return visitorList.size();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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

    private void initLayoutComponent() {
        appDBHelper= new AppDBHelper(getContext());
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        allowedVisitorCount=prepareVisitor();
        if(allowedVisitorCount==1 ){
            mRecyclerView.setVisibility(View.VISIBLE);
          //  allowVisitorBtn.setVisibility(View.VISIBLE);
            adapter= new VisitorAdapter(getContext(), visitorList);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }else if(allowedVisitorCount==0 ){
            mRecyclerView.setVisibility(View.GONE);
            allowVisitorBtn.setVisibility(View.VISIBLE);
        }else if(allowedVisitorCount>1){
            allowVisitorBtn.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter= new VisitorAdapter(getContext(), visitorList);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
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

    public void addNewVisitor(Visitor data){

        if(adapter==null){
            visitorList.add(data);
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter= new VisitorAdapter(getContext(), visitorList);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }else{
            adapter.addItem(data);
        }
        if(adapter.getItemCount()==2){
                allowVisitorBtn.setVisibility(View.GONE);
            }

    }
    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        String responseMsg="";
        if(serverEvent.getServerResponse() instanceof VisitorListResponse){
            if(serverEvent!=null && serverEvent.getServerResponse()!=null ){
                String status=((VisitorListResponse) serverEvent.getServerResponse()).getStatus();
                responseMsg=((VisitorListResponse) serverEvent.getServerResponse()).getMessage();
                // get the visitor list
            }
        }

    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        CommonUtility.showSnackBar(rootLayout, errorEvent.getErrorMsg());
    }

}
