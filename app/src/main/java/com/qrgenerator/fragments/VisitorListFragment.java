package com.qrgenerator.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.adapter.VisitorAdapter;
import com.qrgenerator.customviews.CustomDialogFragment;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.GetVisitorParams;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.models.VisitorListModel;
import com.qrgenerator.models.VisitorListResponse;
import com.qrgenerator.retrofit.BusProvider;
import com.qrgenerator.retrofit.Communicator;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;
import com.squareup.otto.Subscribe;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
//    private boolean isViewShown=false;
    private boolean isStarted = false;
    private boolean isVisible = false;

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

        return view;
    }

    private int prepareVisitor() {
        if(!CommonUtility.isNetworkAvailable(getContext())){
            ItemTable itemTable = new ItemTable(appDBHelper);
            visitorList= itemTable.getAllData();
        }else {
            // network call will be here to get the list of visitor from server
            visitorList=new ArrayList<Visitor>();
            String serverURL = "http://demo-ramnath.rhcloud.com/getVisitors.do";
            new LongOperation().execute(serverURL);
            Log.d(LOG_TAG, "khkkkh");
//            Communicator communicator = new Communicator();
//            AppSharedPreferenceHelper appPref = AppSharedPreferenceHelper.getInstance(getContext());
//            String patientID= appPref.getPatientIDFromSP();
//            GetVisitorParams getVisitorParams= new GetVisitorParams(patientID);
//            communicator.getVisitorListFromServer(getVisitorParams);
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
        isStarted = true;
        if (isVisible && isStarted){
            initLayoutComponent();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG ," inside onResume ");
        BusProvider.getInstance().register(this);
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
        isStarted=false;
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        isStarted=false;
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
//    @Subscribe
//    public void onServerEvent(ServerEvent serverEvent){
//        String responseMsg="";
//        if(serverEvent.getServerResponse() instanceof VisitorListResponse){
//            Log.d(LOG_TAG, " inside onServerEvent ");
//            if(serverEvent!=null && serverEvent.getServerResponse()!=null ){
//                String status=((VisitorListResponse) serverEvent.getServerResponse()).getStatus();
//                responseMsg=((VisitorListResponse) serverEvent.getServerResponse()).getMessage();
//                List<VisitorListModel> list= ((VisitorListResponse) serverEvent.getServerResponse()).getVisiters();
//                Log.d(LOG_TAG, " inside onServerEvent "+ list.size());
//                Log.d(LOG_TAG, " inside onServerEvent "+ list.get(0).getContactNumber());
//                Log.d(LOG_TAG, " inside onServerEvent "+ list.get(0).getVisitorName());
//                // get the visitor list
//            }
//        }
//
//    }
//
//    @Subscribe
//    public void onErrorEvent(ErrorEvent errorEvent){
//        Log.d(LOG_TAG, " inside onErrorEvent ");
//        CommonUtility.showSnackBar(rootLayout, errorEvent.getErrorMsg());
//    }
    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, Void, String> {
    private final HttpClient Client = new DefaultHttpClient();
    private String content;
    private String Error = null;
    private ProgressDialog Dialog = new ProgressDialog(getContext());
    @Override
    protected String doInBackground(String... params) {
        try {

            // Call long running operations here (perform background computation)
            // NOTE: Don't call UI Element here.

            // Server url call by GET method
            String patientId= AppSharedPreferenceHelper.getInstance(getContext()).getPatientIDFromSP();
            JSONObject JSON_STRING= new JSONObject();
            JSON_STRING.put("patientId",patientId);
            HttpPost httpPost = new HttpPost(params[0]);
            StringEntity requestEntity = new StringEntity(
                    JSON_STRING.toString());
            httpPost.setHeader(HTTP.CONTENT_TYPE , "application/json");
            httpPost.setEntity(requestEntity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            content = Client.execute(httpPost, responseHandler);
            Log.d(LOG_TAG, " content string is not null "+ content);
            if (content != null) {
                JSONObject jsonObject= new JSONObject(content);
                String status =jsonObject.getString("status");
                String msg= jsonObject.getString("message");//
                if(status.equals(Constants.RESPONSE_FAILURE)){
                    Error = msg;
                    cancel(true);
                }else if(status.equals(Constants.RESPONSE_SUCCESS ) && msg.equalsIgnoreCase("Cuurently, there are no Visitors.")){
                    Log.d(LOG_TAG, "Cuurently, there are no Visitors.");
                    Error= "Cuurently, there are no Visitors.";
                    cancel(true);

//                        AppDBHelper appDBHelper = new AppDBHelper(getContext());
//                        ItemTable itemTable = new ItemTable(appDBHelper);
//                        itemTable.insert(visitor);

                }else if(status.equals(Constants.RESPONSE_SUCCESS )&& !msg.equalsIgnoreCase("Cuurently, there are no Visitors.")){
                    String patientName= jsonObject.getString("patientName");
                    JSONArray visitorArr= jsonObject.getJSONArray("visitors");
                    for(int i=0; i<visitorArr.length();i++){
                        JSONObject mJsonObject = visitorArr.getJSONObject(i);
                        String visitorName=mJsonObject.getString("visitorName");
                        String contactNumber=mJsonObject.getString("contactNumber");
                        Visitor visitor= new Visitor(visitorName,patientId,contactNumber,true);
                        visitorList.add(visitor);
                    }

                }
                Log.d(LOG_TAG, " content string is not null "+ jsonObject.toString());
            }
        } catch (ClientProtocolException e) {
            Error = e.getMessage();
            cancel(true);
        } catch (JSONException je) {
            Error = je.getMessage();
            cancel(true);
        }catch (IOException e) {
            Error = e.getMessage();
            cancel(true);
        }

        return Error;
    }

    @Override
    protected void onPreExecute() {
        // NOTE: You can call UI Element here.
        Dialog.setMessage("Downloading visitor list..");
        Dialog.setCancelable(false);
        Dialog.show();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        Dialog.dismiss();
        CommonUtility.showSnackBar(rootLayout,s);
        Log.d(LOG_TAG ," inside onCancelled " + s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Dialog.dismiss();
        Log.d(LOG_TAG ," inside onCancelled ");
    }

    @Override
    protected void onPostExecute(String msg) {
        // Close progress dialog
        Log.d(LOG_TAG ," inside onPostExecute ");
        Dialog.dismiss();

        if (Error != null) {
            CommonUtility.showSnackBar(rootLayout,msg);
            Log.d(LOG_TAG ,"Error occurred : ");

        } else {
            Log.d(LOG_TAG ,"Output : "+msg);
            allowedVisitorCount=visitorList.size();
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



}


}
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(LOG_TAG, "isVisibleToUser: "+ isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {
            initLayoutComponent();
        }else{
            Log.d(LOG_TAG, "fragment is no longer visible");
            // fragment is no longer visible
        }
//        if (isVisibleToUser) {
//            if (getView() != null) {
//                isViewShown = true;
//                initLayoutComponent();
//            } else {
//                isViewShown = false;
//            }
//
//        }else{
//            Log.d(LOG_TAG, "fragment is no longer visible");
//            // fragment is no longer visible
//        }
    }


}
