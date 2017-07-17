package com.qrgeneratorapp.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qrgeneratorapp.R;
import com.qrgeneratorapp.adapter.VisitorAdapter;
import com.qrgeneratorapp.customviews.CustomDialogFragment;
import com.qrgeneratorapp.models.Visitor;

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


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.visitor_form)
    View v;
    @BindView(R.id.allow_visitor_btn)
    Button allowVisitorBtn;

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
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        allowedVisitorCount=prepareVisitor();
        if(allowedVisitorCount==1 ){
            mRecyclerView.setVisibility(View.VISIBLE);
            allowVisitorBtn.setVisibility(View.VISIBLE);
            adapter= new VisitorAdapter(getContext(), visitorList);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }else if(allowedVisitorCount==0 ){
            mRecyclerView.setVisibility(View.GONE);
            allowVisitorBtn.setVisibility(View.VISIBLE);
        }else if(allowedVisitorCount>1){
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter= new VisitorAdapter(getContext(), visitorList);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }

        return view;
    }

    private int prepareVisitor() {
        if(visitorList==null){
            visitorList= new ArrayList<>();
        }else visitorList.clear();

//        Visitor visitor1= new Visitor("Zeeshan", "Noida", "9873799571", true);
//        Visitor visitor2= new Visitor("Ram Kumar", "Gurgaon", "8802390096", true);
//        visitorList.add(visitor1);
//        visitorList.add(visitor2);
        return visitorList.size();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showDialog(){
        // Create custom dialog object
        final Dialog dialog = new Dialog(getContext());
        // Include dialog.xml file
        dialog.setContentView(R.layout.visitor_form);
        // Set dialog title
//        dialog.setTitle(" Visitor Form ");

//        // set values for custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.textDialog);
//        text.setText("Custom dialog Android example.");
//        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
//        image.setImageResource(R.drawable.image0);

        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.cancelBtn);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
    }
}
